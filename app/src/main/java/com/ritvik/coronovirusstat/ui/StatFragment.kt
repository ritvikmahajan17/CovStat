package com.ritvik.coronovirusstat.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ritvik.coronovirusstat.R
import com.ritvik.coronovirusstat.databinding.LayoutRcBinding
import com.ritvik.coronovirusstat.network.CountryData
import com.ritvik.coronovirusstat.skeleton.StatViewModel


class StatFragment : Fragment() {

    private lateinit var statViewModel: StatViewModel
    private val update = "+"


    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding =
            DataBindingUtil.inflate<LayoutRcBinding>(inflater, R.layout.layout_rc, container, false)

        statViewModel = ViewModelProviders.of(this).get(StatViewModel::class.java)

        binding.lifecycleOwner = this

        binding.statVM = statViewModel


        binding.countryRc.adapter = RecyclerVIewAdapter(RecyclerVIewAdapter.OnClickListener {
            statViewModel.displayCountryDetails(it)
        })

        statViewModel.totalCases.observe(this, Observer { value ->
            binding.totalcasesTextview.text = value
            Log.i("loll", "yes1")
        })

        statViewModel.totalDeaths.observe(this, Observer { value ->
            binding.totaldeathsTextview.text = value
            Log.i("loll", "2")
        })

        statViewModel.newCases.observe(this, Observer { value ->
            binding.newcasesTextview.text = update + value
            Log.i("loll", "3")
        })

        statViewModel.newDeaths.observe(this, Observer { value ->
            binding.newdeathsTextview.text = update + value
            Log.i("loll", "4")
        })

        statViewModel.totalRecovered.observe(this, Observer { value ->
            binding.totalRecoveriesTextview.text = value
            Log.i("loll", "yes5")
        })

        statViewModel.totalCritical.observe(this, Observer { value ->
            binding.totalcriticalTextview.text = value
            Log.i("loll", "6")
        })

        statViewModel.newRecovered.observe(this, Observer { value ->
            binding.newrecoveriesTextview.text = update + value.toString()
            Log.i("loll", "9")
        })

        statViewModel.newCritical.observe(this, Observer { value ->
            binding.newcriticalTextview.text = update + value.toString()
            Log.i("loll", "9")
        })

        statViewModel.navigateToSelectedCountry.observe(this, Observer {
            if (null != it) {
                this.findNavController()
                    .navigate(StatFragmentDirections.actionStatFragmentToCountryStatFragment(it))

                statViewModel.displayPropertyDetailsComplete()
            }
        })

        return binding.root
    }




}


