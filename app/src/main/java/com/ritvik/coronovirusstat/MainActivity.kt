package com.ritvik.coronovirusstat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ritvik.coronovirusstat.databinding.ActivityMainBinding
import com.ritvik.coronovirusstat.skeleton.ApiStatus
import com.ritvik.coronovirusstat.skeleton.StatViewModel
import com.ritvik.coronovirusstat.ui.LoadingFragmentDirections

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)



        val statViewModel = ViewModelProviders.of(this).get(StatViewModel::class.java)

        statViewModel.status.observe(this, Observer { value ->
            if(value == ApiStatus.DONE ) {
               binding.bottomNavigationView.isVisible = true
            }

            else if (value == ApiStatus.ERROR || value == ApiStatus.LOADING){
                binding.bottomNavigationView.isVisible = false
            }

        })

        val navController = findNavController(R.id.myNavHostFragment)
        binding.bottomNavigationView.setupWithNavController(navController)

    }




}



