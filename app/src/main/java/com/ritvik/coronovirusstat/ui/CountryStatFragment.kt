package com.ritvik.coronovirusstat.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.ritvik.coronovirusstat.databinding.CountryStatLayoutBinding
import com.ritvik.coronovirusstat.skeleton.CountryStatViewModel
import com.ritvik.coronovirusstat.skeleton.CountryStatViewModelFactory

class CountryStatFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val application = requireNotNull(activity).application

        val binding = CountryStatLayoutBinding.inflate(inflater)

        binding.lifecycleOwner = this

       val countryData = CountryStatFragmentArgs.fromBundle(arguments!!).selectedCountryData
        //val countryInfo = CountryStatFragmentArgs.fromBundle(arguments!!).selectedCountryInfo

        val countryStatViewModelFactory = CountryStatViewModelFactory(countryData, application)

        val countryStatViewModel = ViewModelProviders.of(
            this, countryStatViewModelFactory).get(CountryStatViewModel::class.java)

        binding.countryStatVM = countryStatViewModel

        return binding.root
    }
}