package com.ritvik.coronovirusstat.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.ritvik.coronovirusstat.databinding.CountryStatLayoutBinding
import com.ritvik.coronovirusstat.skeleton.CountryStatViewModel
import com.ritvik.coronovirusstat.skeleton.CountryStatViewModelFactory
import java.math.RoundingMode
import kotlin.math.roundToLong

class CountryStatFragment : Fragment() {
    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

         val perc: String = "%"

        val application = requireNotNull(activity).application

        val binding = CountryStatLayoutBinding.inflate(inflater)

        binding.lifecycleOwner = this

       val countryData = CountryStatFragmentArgs.fromBundle(arguments!!).selectedCountryData
        //val countryInfo = CountryStatFragmentArgs.fromBundle(arguments!!).selectedCountryInfo

        val countryStatViewModelFactory = CountryStatViewModelFactory(countryData, application)

        val countryStatViewModel = ViewModelProviders.of(
            this, countryStatViewModelFactory).get(CountryStatViewModel::class.java)

        binding.countryStatVM = countryStatViewModel

        binding.circularProgressBarFatality.apply {
          val rate = ((countryData.deaths?.div(countryData.cases!!))?.times(100))?.toFloat()!!
            binding.fatalityRateText.text = rate.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN).toString() + perc
            setProgressWithAnimation(rate,1000)
        }

        binding.circularProgressBarRecovery.apply {
            val rate = ((countryData.recovered?.div(countryData.cases!!))?.times(100))?.toFloat()!!
            binding.recoveryRateText.text = rate.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN).toString() + perc
            setProgressWithAnimation(rate,1000)
        }

        return binding.root
    }
}