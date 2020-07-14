package com.ritvik.coronovirusstat.skeleton

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ritvik.coronovirusstat.network.CountryData
import com.ritvik.coronovirusstat.network.CountryInfo

class CountryStatViewModel(countryData: CountryData, app: Application) : AndroidViewModel(app) {

    val _selectedCountryData = MutableLiveData<CountryData>()
    val selectedCountryData: LiveData<CountryData>
        get() =  _selectedCountryData


    init {
        _selectedCountryData.value = countryData

    }



}