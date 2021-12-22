package com.lincoln4791.dailyexpensemanager.view

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.lincoln4791.dailyexpensemanager.viewModels.VM_Transactions
import com.lincoln4791.dailyexpensemanager.Adapters.Adapter_Transactions
import android.os.Bundle
import com.lincoln4791.dailyexpensemanager.R
import android.content.Intent
import com.lincoln4791.dailyexpensemanager.common.UtilDB
import com.lincoln4791.dailyexpensemanager.common.Extras
import android.os.AsyncTask
import android.util.Log
import com.lincoln4791.dailyexpensemanager.common.SQLiteHelper
import com.lincoln4791.dailyexpensemanager.model.MC_Posts
import android.widget.Toast
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lincoln4791.dailyexpensemanager.Resource
import com.lincoln4791.dailyexpensemanager.common.Constants
import com.lincoln4791.dailyexpensemanager.databinding.ActivityTransactionsBinding
import com.lincoln4791.dailyexpensemanager.roomDB.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Transactions() : AppCompatActivity() {
    private val linearLayoutManager = LinearLayoutManager(this)
    private var toolbar: Toolbar? = null
    private var vm_transactions: VM_Transactions? = null
    private var adapter_transactions: Adapter_Transactions? = null

    private lateinit var binding: ActivityTransactionsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

/*        toolbar = findViewById(R.id.toolbar_Transactions)


        supportActionBar!!.hide()
        linearLayoutManager.reverseLayout = true
        linearLayoutManager.stackFromEnd = true
        binding.rvTransactions.layoutManager = linearLayoutManager
        vm_transactions = ViewModelProvider(this)[VM_Transactions::class.java]



        vm_transactions!!.postsList.observe(this, Observer {
            Log.d("Transaction", "observed")
            when (it) {
                is Resource.Loading -> Log.d("Transaction", "Loading...")
                //is Resource.Success -> adapter_transactions = Adapter_Transactions(it.data, this)
                is Resource.Success ->  update(it.data)
                is Resource.Error -> Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            }
        })

        vm_transactions!!.totalIncome.observe(this, Observer {
            binding.tvTotalIncomeValueTopBarTransactions.text = it.toString()
        })

        vm_transactions!!.totalExpense.observe(this, Observer {
            binding.tvTotalExpenseValueTopBarTransactions.text = it.toString()
        })


        binding.cvMonthlyTransactions.setOnClickListener(View.OnClickListener { v: View? ->
            startActivity(Intent(this@Transactions,
                MonthlyReport::class.java))
        })
        binding.cvDailyTransactions.setOnClickListener(View.OnClickListener { v: View? ->
            startActivity(Intent(this@Transactions,
                Daily::class.java))
        })
        binding.cvFullReportTransactions.setOnClickListener(View.OnClickListener { v: View? ->
            startActivity(Intent(this@Transactions,
                FullReport::class.java))
        })
        binding.ivDeleteAllTransactions.setOnClickListener(View.OnClickListener { v: View? -> confirmDeleteAll() })
        binding.ivHomeToolbarTransactions.setOnClickListener(View.OnClickListener { v: View? ->
            startActivity(Intent(this@Transactions,
                MainActivity::class.java))
        })
        binding.cvTotalIncomesTopBarTransactions.setOnClickListener {
            vm_transactions!!.loadAllIncomes(application)
        }
        binding.ivReloadTransactionsTransactions.setOnClickListener {
            vm_transactions!!.loadAllTransactions(application)
        }
        binding.cvTotalExpensesTopBarTransactions.setOnClickListener {
            vm_transactions!!.loadAllExpenses(application)
        }


        binding.tvCurrentBalanceValueToolBarTransactions.text = UtilDB.currentBalance.toString()
        Log.d("tag","Current Balance is ${UtilDB.currentBalance}")
        intentData*/
    }

/*    private val intentData: Unit
        get() {
            when {
                intent.getStringExtra(Extras.TYPE) == Constants.TYPE_INCOME -> {
                    vm_transactions!!.loadAllIncomes(application)
                }
                intent.getStringExtra(Extras.TYPE) == Constants.TYPE_EXPENSE -> {
                    vm_transactions!!.loadAllExpenses(application)
                }
                else -> {
                    vm_transactions!!.loadAllTransactions(application)
                }
            }
        }*/



/*    private fun update(posts: List<MC_Posts> ){
        vm_transactions!!.fetchAllTransactions(posts)
        adapter_transactions = Adapter_Transactions(posts, this)
        binding.tvTypeTitleTransactions.text = getString(R.string.Transactions)
        toolbar!!.title = getString(R.string.Transactions)
        binding.rvTransactions.adapter = adapter_transactions
        adapter_transactions!!.notifyDataSetChanged()
    }


    private fun deleteDataAll() {
        CoroutineScope(Dispatchers.IO).launch {
            AppDatabase.getInstance(applicationContext).dbDao().deleteAll()
        }
        val intent = Intent(this@Transactions, Transactions::class.java)
        intent.putExtra(Extras.TYPE, Constants.TYPE_ALL)
        UtilDB.currentBalance = 0
        startActivity(intent)
        finish()
    }

    fun deleteData(id: Int) {
        AppDatabase.getInstance(applicationContext).dbDao().delete(id.toString())
    }

    fun confirmDelete(id: Int, amount: Int, typeOfTheFile: String) {
        val dialog = Dialog(this@Transactions)
        val view = LayoutInflater.from(this@Transactions).inflate(R.layout.dialog_delete, null)
        dialog.setContentView(view)
        dialog.setCancelable(true)
        dialog.show()
        view.findViewById<View>(R.id.btn_yes_alertImage_dialog_delete).setOnClickListener {
            if ((typeOfTheFile == Constants.TYPE_INCOME)) {
                UtilDB.currentBalance = UtilDB.currentBalance - amount
            } else if ((typeOfTheFile == Constants.TYPE_EXPENSE)) {
                UtilDB.currentBalance = UtilDB.currentBalance + amount
            }
            dialog.dismiss()
            deleteData(id)
        }
        view.findViewById<View>(R.id.btn_no_alertImage_dialog_delete)
            .setOnClickListener { dialog.dismiss() }
    }

    fun confirmDeleteAll() {
        val dialog = Dialog(this@Transactions)
        val view = LayoutInflater.from(this@Transactions).inflate(R.layout.dialog_delete_all, null)
        dialog.setContentView(view)
        dialog.setCancelable(true)
        dialog.show()
        view.findViewById<View>(R.id.btn_yes_alertImage_dialog_deleteAll).setOnClickListener(
            View.OnClickListener {
                dialog.dismiss()
                deleteDataAll()
            })
        view.findViewById<View>(R.id.btn_no_alertImage_dialog_deleteAll)
            .setOnClickListener { dialog.dismiss() }
    }*/

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }
}