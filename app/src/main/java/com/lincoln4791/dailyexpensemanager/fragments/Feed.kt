package com.lincoln4791.dailyexpensemanager.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.lincoln4791.dailyexpensemanager.base.BaseFragment
import com.lincoln4791.dailyexpensemanager.databinding.FragmentFeedBinding
import com.lincoln4791.dailyexpensemanager.view.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Feed : BaseFragment<FragmentFeedBinding>(FragmentFeedBinding::inflate) {

    private lateinit var navCon : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("LifeCycle","Transactions Fragment Create")

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    // Handle the back button event
                    Log.d("tag","OnBackPressCalled -> MonthlyCategoryWise")
                    //navCon.navigateUp()
                    goBack()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navCon = Navigation.findNavController(view)

        binding.cvImg.setOnClickListener { goBack() }

    }

    private fun goBack(){
        navCon.navigateUp()
         (requireActivity() as MainActivity).markChipNavigationInHomeFragment()
        this.onDestroy()
        this.onDetach()
    }

}