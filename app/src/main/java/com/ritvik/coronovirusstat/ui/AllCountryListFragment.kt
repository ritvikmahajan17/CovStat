package com.ritvik.coronovirusstat.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.ritvik.coronovirusstat.databinding.AllCountryListLayoutBinding
import com.ritvik.coronovirusstat.databinding.CountryStatLayoutBinding
import com.ritvik.coronovirusstat.skeleton.AllCountryListViewModel
import com.ritvik.coronovirusstat.skeleton.CountryStatViewModel
import com.ritvik.coronovirusstat.skeleton.CountryStatViewModelFactory
import com.ritvik.coronovirusstat.skeleton.StatViewModel

class AllCountryListFragment : Fragment() {
    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val application = requireNotNull(activity).application

        val binding = AllCountryListLayoutBinding.inflate(inflater)

        binding.lifecycleOwner = this

        val allCountryListViewModel = ViewModelProviders.of(
            this).get(AllCountryListViewModel::class.java)

       val statViewModel = ViewModelProviders.of(this).get(StatViewModel::class.java)

        binding.allCountryList.adapter = RecyclerViewAdapterCountries(RecyclerViewAdapterCountries.OnClickListener {
            statViewModel.displayCountryDetails(it)
        })

        binding.allCountryListVM = allCountryListViewModel

        statViewModel.navigateToSelectedCountry.observe(this, Observer {
            if (null != it) {
                this.findNavController()
                    .navigate(AllCountryListFragmentDirections.actionAllCountryListFragmentToCountryStatFragment(it))

                statViewModel.displayPropertyDetailsComplete()
            }
        })

        return binding.root


    }
}