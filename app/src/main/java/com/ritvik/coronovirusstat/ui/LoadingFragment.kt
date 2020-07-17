package com.ritvik.coronovirusstat.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.ritvik.coronovirusstat.databinding.CountryStatLayoutBinding
import com.ritvik.coronovirusstat.databinding.LoadingLayoutBinding
import com.ritvik.coronovirusstat.skeleton.ApiStatus
import com.ritvik.coronovirusstat.skeleton.StatViewModel
import kotlinx.coroutines.delay
import java.util.zip.Inflater

class LoadingFragment : Fragment() {

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = LoadingLayoutBinding.inflate(inflater)

        val statViewModel = ViewModelProviders.of(this).get(StatViewModel::class.java)

        statViewModel.status.observe(this, Observer { value ->
            if(value == ApiStatus.DONE){

                findNavController().navigate(LoadingFragmentDirections.actionLoadingFragmentToStatFragment())
            }

        })

        return binding.root

    }
}
