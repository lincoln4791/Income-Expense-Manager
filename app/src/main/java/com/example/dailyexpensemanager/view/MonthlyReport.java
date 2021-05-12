package com.example.dailyexpensemanager.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.dailyexpensemanager.R;
import com.example.dailyexpensemanager.common.Constants;
import com.example.dailyexpensemanager.common.SQLiteHelper;
import com.example.dailyexpensemanager.model.MC_Posts;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MonthlyReport extends AppCompatActivity {
    private Spinner spinnerMonth,spinnerYear;
    private TextView tv_totalIncome,tv_totalExpenses,tv_balance,tv_houseRentExpenseValue,tv_houseRentExpensesPercent,tv_transportExpenseValue,tv_transportExpensesPercent,
            tv_foodExpenseValue,tv_foodExpensesPercent,tv_billsExpenseValue,tv_billsExpensesPercent,tv_businessExpenseValue,tv_businessExpensesPercent
            ,tv_medicineExpenseValue,tv_medicineExpensesPercent,tv_clothsExpenseValue,tv_clothsExpensesPercent,tv_educationExpenseValue,tv_educationExpensesPercent,
            tv_lifeStyleExpenseValue,tv_lifeStyleExpensesPercent,tv_otherExpenseValue,tv_otherExpensesPercent,tv_salaryIncomeValue,tv_salaryIncomePercent,
            tv_businessIncomeValue,tv_businessIncomePercent,tv_otherIncomeValue,tv_otherIncomePercent,
            tv_houseRentIncomeValue,tv_houseRentIncomePercent,tv_totalExpenseBot,tv_totalIncomeBot,tv_balanceBot;
    private CardView cv_home,cv_transactions, cv_daily,cv_search;

    private double totalIncome=0,totalExpense=0,balance,transportExpense=0,foodExpense=0,billsExpense=0,houseRentExpense=0,businessExpense=0,
            medicineExpense=0,clothsExpense=0,educationExpense=0,lifeStyleExpense=0,otherExpense=0,salaryIncome=0,businessIncome=0,houseRentIncome,
            otherIncome=0;
    private String  foodExpensePercent,transportExpensePercent,billsExpensePercent,businessExpensePercent,houseRentExpensePercent,
            medicineExpensePercent,clothsExpensePercent,educationExpensePercent,lifeStyleExpensePercent,otherExpensePercent,
            salaryIncomePercent,businessIncomePercent,houseRentIncomePercent,otherIncomePercent;
    private String month,year;
    private int currentMonthPosition,currentMonth,currentYear;
    private List<MC_Posts> postsList;

    private static DecimalFormat df2 = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_report);


        //**********************************************View Bindings*********************************************
        tv_totalIncome = findViewById(R.id.tv_totalIncomeValue_topBar_MonthlyReport);
        tv_totalExpenses = findViewById(R.id.tv_totalExpenseValue_topBar_MonthlyReport);
        tv_balance = findViewById(R.id.tv_totalBalanceValue_topBar_MonthlyReport);

        tv_foodExpenseValue = findViewById(R.id.tv_amount_food_MonthlyReport);
        tv_transportExpenseValue = findViewById(R.id.tv_amount_transport_MonthlyReport);
        tv_houseRentExpenseValue = findViewById(R.id.tv_amount_houseRent_MonthlyReport);
        tv_billsExpenseValue = findViewById(R.id.tv_amount_bills_MonthlyReport);
        tv_businessExpenseValue = findViewById(R.id.tv_amount_business_MonthlyReport);
        tv_medicineExpenseValue = findViewById(R.id.tv_amount_medicine_MonthlyReport);
        tv_clothsExpenseValue = findViewById(R.id.tv_amount_cloths_MonthlyReport);
        tv_educationExpenseValue = findViewById(R.id.tv_amount_education_MonthlyReport);
        tv_lifeStyleExpenseValue = findViewById(R.id.tv_amount_lifeStyle_MonthlyReport);
        tv_otherExpenseValue = findViewById(R.id.tv_amount_otherExpense_MonthlyReport);

        tv_salaryIncomeValue = findViewById(R.id.tv_amount_salaryIncome_MonthlyReport);
        tv_businessIncomeValue = findViewById(R.id.tv_amount_businessIncome_MonthlyReport);
        tv_houseRentIncomeValue = findViewById(R.id.tv_amount_houseRentIncome_MonthlyReport);
        tv_otherIncomeValue = findViewById(R.id.tv_amount_otherIncome_MonthlyReport);

        tv_foodExpensesPercent = findViewById(R.id.tv_category_foodPercentValue_MonthlyReport);
        tv_transportExpensesPercent = findViewById(R.id.tv_category_transportPercentValue_MonthlyReport);
        tv_houseRentExpensesPercent = findViewById(R.id.tv_category_HouseRentPercentValue_MonthlyReport);
        tv_billsExpensesPercent = findViewById(R.id.tv_category_billsPercentValue_MonthlyReport);
        tv_businessExpensesPercent = findViewById(R.id.tv_category_businessPercentValue_MonthlyReport);
        tv_medicineExpensesPercent = findViewById(R.id.tv_category_medicinePercentValue_MonthlyReport);
        tv_clothsExpensesPercent = findViewById(R.id.tv_category_clothsPercentValue_MonthlyReport);
        tv_educationExpensesPercent = findViewById(R.id.tv_category_educationPercentValue_MonthlyReport);
        tv_lifeStyleExpensesPercent = findViewById(R.id.tv_category_lifeStylePercentValue_MonthlyReport);
        tv_otherExpensesPercent = findViewById(R.id.tv_category_otherExpensePercentValue_MonthlyReport);

        tv_salaryIncomePercent = findViewById(R.id.tv_category_salaryIncomePercentValue_MonthlyReport);
        tv_businessIncomePercent = findViewById(R.id.tv_category_businessIncomePercentValue_MonthlyReport);
        tv_houseRentIncomePercent = findViewById(R.id.tv_category_houseRentIncomePercentValue_MonthlyReport);
        tv_otherIncomePercent = findViewById(R.id.tv_category_otherIncomePercentValue_MonthlyReport);

        tv_totalIncomeBot = findViewById(R.id.tv_totalIncome_bot__MonthlyReport);
        tv_totalExpenseBot = findViewById(R.id.tv_totalExpense_bot_MonthlyReport);
        tv_balanceBot = findViewById(R.id.tv_balance_bot_MonthlyReport);

        cv_home = findViewById(R.id.cv_Home_MonthlyReport);
        cv_daily = findViewById(R.id.cv_daily_MonthlyReport);
        cv_transactions = findViewById(R.id.cv_transactions_MonthlyReport);
        cv_search = findViewById(R.id.cv_search_MonthlyReport);



        //***********************************************Initializations******************************************
        Calendar calendar = Calendar.getInstance();
        currentMonthPosition = calendar.get(Calendar.MONTH);
        currentMonth = currentMonthPosition+1;
        currentYear = calendar.get(Calendar.YEAR);
        year = String.valueOf(currentYear);
        setMonth();
        postsList = new ArrayList<>();




        //***********************************************Click Listeners**************************************
        cv_search.setOnClickListener(v -> {
               new YearMonthTask().execute();
        });

        cv_home.setOnClickListener(v -> startActivity(new Intent(MonthlyReport.this,MainActivity.class)));

        cv_daily.setOnClickListener(v -> startActivity(new Intent(MonthlyReport.this,Daily.class)));

        cv_transactions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MonthlyReport.this,Transactions.class));
            }
        });



        //*********************************************Starting Methods*******************************************
        initMonthSpinner();
        initYearSpinner();

        new YearMonthTask().execute();

    }

    private void setMonth() {
        if(currentMonth==1){
            month="01";
        }
        else if (currentMonth==2){
            month="02";
        }
        else if (currentMonth==3){
            month="03";
        }
        else if (currentMonth==4){
            month="04";
        }
        else if (currentMonth==5){
            month="05";
        }
        else if (currentMonth==6){
            month="06";
        }
        else if (currentMonth==7){
            month="07";
        }
        else if (currentMonth==8){
            month="08";
        }
        else if (currentMonth==9){
            month="09";
        }
       else{
           month = String.valueOf(currentMonth);
        }
    }


    private void initMonthSpinner() {
        spinnerMonth = findViewById(R.id.spinner_month_MonthlyReport);
        ArrayAdapter spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.monthNames_MonthlyReport));
        spinnerMonth.setAdapter(spinnerAdapter);


        spinnerMonth.setSelection(currentMonthPosition);



        spinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    month = getString(R.string.digit01);
                }
                else if (position==1){
                    month = getString(R.string.digit02);
                }
                else if (position==2){
                    month = getString(R.string.digit03);
                }
                else if (position==3){
                    month = getString(R.string.digit04);
                }
                else if (position==4){
                    month = getString(R.string.digit05);
                }
                else if (position==5){
                    month = getString(R.string.digit06);
                }
                else if (position==6){
                    month = getString(R.string.digit07);
                }
                else if (position==7){
                    month = getString(R.string.digit08);
                }
                else if (position==8){
                    month = getString(R.string.digit09);
                }
                else if (position==9){
                    month = getString(R.string.digit10);
                }
                else if (position==10){
                    month = getString(R.string.digit11);
                }
                else if (position==11){
                    month = getString(R.string.digit12);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Calendar calendar = Calendar.getInstance();
                month=String.valueOf(calendar.get(Calendar.MONTH));
            }
        });

    }







    private void initYearSpinner() {
        spinnerYear = findViewById(R.id.spinner_year_MonthlyReport);
        ArrayAdapter spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.year));
        spinnerYear.setAdapter(spinnerAdapter);

        spinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    year = Constants.YEAR_DEFAULT;
                } else if (position == 1) {
                    year = "2022";
                } else if (position == 2) {
                    year = "2023";
                } else if (position == 3) {
                   year = "2024";
                } else if (position == 4) {
                   year = "2025";
                } else if (position == 5) {
                    year = "2026";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Calendar calendar = Calendar.getInstance();
                year=String.valueOf(calendar.get(Calendar.YEAR));
            }
        });

    }






    class YearMonthTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {

            fetchYearMonthWise();
            return null;
        }


        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            setCalculatedValue();
        }
    }









    private void fetchYearMonthWise() {
        postsList.clear();
        //tv_typeTitle.setText(getString(R.string.Category));
        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        Log.d("tag","year: :"+year+"month : "+month);
        Cursor cursor = sqLiteHelper.loadYearMonthWise(year,month);

        while (cursor.moveToNext()) {
            String postDescription = cursor.getString(1);
            String postCategory = cursor.getString(2);
            String postType = cursor.getString(3);
            String postAmount = cursor.getString(4);
            String postTime = cursor.getString(5);
            String postDay = cursor.getString(6);
            String postMonth = cursor.getString(7);
            String postYear = cursor.getString(8);
            String postDateTime = cursor.getString(9);
            String timeStamp = cursor.getString(10);

            MC_Posts post = new MC_Posts(postDescription, postCategory, postType, postAmount, postYear, postMonth, postDay, postTime, timeStamp, postDateTime);
            postsList.add(post);
        }

        cursor.close();
        calculateAll();
    }








    private void calculateAll(){
        resetPreviousCalculatedValues();
        Log.d("tag","list size : "+postsList.size());

        for(int i = 0; i< postsList.size() ; i++){
            if(postsList.get(i).getPostType().equals(Constants.TYPE_INCOME)){
                if(postsList.get(i).postCategory.equals(Constants.CATEGORY_SALARY)){
                    salaryIncome= salaryIncome+Integer.parseInt(postsList.get(i).getPostAmount());
                }

                else if(postsList.get(i).postCategory.equals(Constants.CATEGORY_BUSINESS)){
                    businessIncome= businessIncome+Integer.parseInt(postsList.get(i).getPostAmount());
                }

                else if(postsList.get(i).postCategory.equals(Constants.CATEGORY_HOUSE_RENT)){
                    houseRentIncome= houseRentIncome+Integer.parseInt(postsList.get(i).getPostAmount());
                }

                else if(postsList.get(i).postCategory.equals(Constants.CATEGORY_OTHER)){
                    otherIncome= otherIncome+Integer.parseInt(postsList.get(i).getPostAmount());
                }

                totalIncome=totalIncome+Integer.parseInt(postsList.get(i).getPostAmount());

            }
            else{
                if(postsList.get(i).postCategory.equals(Constants.CATEGORY_FOOD)){
                    foodExpense= foodExpense+ Integer.parseInt(postsList.get(i).getPostAmount());
                }
                else if(postsList.get(i).postCategory.equals(Constants.CATEGORY_TRANSPORT)){
                    transportExpense=transportExpense+ Integer.parseInt(postsList.get(i).getPostAmount());
                }
                else if(postsList.get(i).postCategory.equals(Constants.CATEGORY_BILLS)){
                    billsExpense= billsExpense+Integer.parseInt(postsList.get(i).getPostAmount());
                }
                else if(postsList.get(i).postCategory.equals(Constants.CATEGORY_HOUSE_RENT)){
                    houseRentExpense= houseRentExpense+Integer.parseInt(postsList.get(i).getPostAmount());
                }
                else if(postsList.get(i).postCategory.equals(Constants.CATEGORY_BUSINESS)){
                    businessExpense= businessExpense+Integer.parseInt(postsList.get(i).getPostAmount());
                }
                else if(postsList.get(i).postCategory.equals(Constants.CATEGORY_MEDICINE)){
                    medicineExpense= medicineExpense+Integer.parseInt(postsList.get(i).getPostAmount());
                }
                else if(postsList.get(i).postCategory.equals(Constants.CATEGORY_CLOTHS)){
                    clothsExpense= clothsExpense+Integer.parseInt(postsList.get(i).getPostAmount());
                }
                else if(postsList.get(i).postCategory.equals(Constants.CATEGORY_EDUCATION)){
                    educationExpense=educationExpense+ Integer.parseInt(postsList.get(i).getPostAmount());
                }
                else if(postsList.get(i).postCategory.equals(Constants.CATEGORY_LIFESTYLE)){
                    lifeStyleExpense=lifeStyleExpense+ Integer.parseInt(postsList.get(i).getPostAmount());
                }
                else if(postsList.get(i).postCategory.equals(Constants.CATEGORY_OTHER)){
                    otherExpense= otherExpense+Integer.parseInt(postsList.get(i).getPostAmount());
                }
                totalExpense = totalExpense+Integer.parseInt(postsList.get(i).getPostAmount());
            }
        }

        balance = totalIncome-totalExpense;

        calculatePercent();
    }








    private void resetPreviousCalculatedValues() {
        salaryIncome=0;
        businessIncome=0;
        houseRentIncome=0;
        otherIncome=0;
        totalIncome=0;

        foodExpense=0;
        transportExpense=0;
        billsExpense=0;
        houseRentExpense=0;
        businessExpense=0;
        medicineExpense=0;
        clothsExpense=0;
        educationExpense=0;
        lifeStyleExpense=0;
        otherExpense=0;
        totalExpense=0;
    }






    public void setCalculatedValue(){
        Log.d("tag","setting value");
       tv_salaryIncomeValue.setText(String.valueOf(salaryIncome));
       tv_businessIncomeValue.setText(String.valueOf(businessIncome));
       tv_houseRentIncomeValue.setText(String.valueOf(houseRentIncome));
       tv_otherIncomeValue.setText(String.valueOf(otherIncome));
       tv_totalIncome.setText(String.valueOf(totalIncome));
       tv_totalIncomeBot.setText(String.valueOf(totalIncome));

       tv_foodExpenseValue.setText(String.valueOf(foodExpense));
       tv_transportExpenseValue.setText(String.valueOf(transportExpense));
       tv_businessExpenseValue.setText(String.valueOf(businessExpense));
       tv_billsExpenseValue.setText(String.valueOf(billsExpense));
       tv_houseRentExpenseValue.setText(String.valueOf(houseRentExpense));
       tv_medicineExpenseValue.setText(String.valueOf(medicineExpense));
       tv_clothsExpenseValue.setText(String.valueOf(clothsExpense));
       tv_educationExpenseValue.setText(String.valueOf(educationExpense));
       tv_lifeStyleExpenseValue.setText(String.valueOf(lifeStyleExpense));
       tv_otherExpenseValue.setText(String.valueOf(otherExpense));
       tv_totalExpenses.setText(String.valueOf(totalExpense));
       tv_totalExpenseBot.setText(String.valueOf(totalExpense));

       tv_foodExpensesPercent.setText(String.valueOf(foodExpensePercent));
       tv_transportExpensesPercent.setText(String.valueOf(transportExpensePercent));
       tv_businessExpensesPercent.setText(String.valueOf(businessExpensePercent));
       tv_billsExpensesPercent.setText(String.valueOf(billsExpensePercent));
       tv_houseRentExpensesPercent.setText(String.valueOf(houseRentExpensePercent));
       tv_medicineExpensesPercent.setText(String.valueOf(medicineExpensePercent));
       tv_clothsExpensesPercent.setText(String.valueOf(clothsExpensePercent));
       tv_educationExpensesPercent.setText(String.valueOf(educationExpensePercent));
       tv_lifeStyleExpensesPercent.setText(String.valueOf(lifeStyleExpensePercent));
       tv_otherExpensesPercent.setText(String.valueOf(otherExpensePercent));

       tv_salaryIncomePercent.setText(String.valueOf(salaryIncomePercent));
       tv_businessIncomePercent.setText(String.valueOf(businessIncomePercent));
       tv_houseRentIncomePercent.setText(String.valueOf(houseRentIncomePercent));
       tv_otherIncomePercent.setText(String.valueOf(otherIncomePercent));

       tv_balance.setText(String.valueOf(balance));
       tv_balanceBot.setText(String.valueOf(balance));

    }








    private void calculatePercent(){

        foodExpensePercent = df2.format((foodExpense/totalExpense)*100.0);
        businessExpensePercent = df2.format((businessExpense/totalExpense)*100);
        transportExpensePercent = df2.format(((transportExpense/totalExpense)*100));
        billsExpensePercent = df2.format(((billsExpense/totalExpense)*100));
        houseRentExpensePercent = df2.format(((houseRentExpense/totalExpense)*100));
        medicineExpensePercent = df2.format(((medicineExpense/totalExpense)*100));
        clothsExpensePercent = df2.format(((clothsExpense/totalExpense)*100));
        educationExpensePercent = df2.format(((educationExpense/totalExpense)*100));
        lifeStyleExpensePercent = df2.format(((lifeStyleExpense/totalExpense)*100));
        otherExpensePercent = df2.format(((otherExpense/totalExpense)*100));

        salaryIncomePercent = df2.format(((salaryIncome/totalIncome)*100));
        businessIncomePercent = df2.format(((businessIncome/totalIncome)*100));
        houseRentIncomePercent = df2.format(((houseRentIncome/totalIncome)*100));
        otherIncomePercent = df2.format(((otherIncome/totalIncome)*100));
        Log.d("tag","food : "+foodExpense+" totat : "+totalExpense+"percent: "+foodExpensePercent);
    }





    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }




}