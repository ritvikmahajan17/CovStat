package com.ritvik.coronovirusstat.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ritvik.coronovirusstat.R
import com.ritvik.coronovirusstat.skeleton.StatViewModel
import com.ritvik.coronovirusstat.databinding.StatisticsLayoutBinding

class StatFragment : Fragment() {

    private lateinit var statViewModel:StatViewModel

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<StatisticsLayoutBinding>(inflater, R.layout.statistics_layout, container, false)

        statViewModel = ViewModelProviders.of(this).get(StatViewModel::class.java)

        binding.statVM = statViewModel

        binding.lifecycleOwner = this

        statViewModel.totalCases.observe(this, Observer { value ->
            binding.tcasesText.text = value
            Log.i("loll","yes1")
        })

        statViewModel.totalDeaths.observe(this, Observer { value ->
            binding.tdeathsText.text = value
            Log.i("loll","2")
        })

        return binding.root
    }


}