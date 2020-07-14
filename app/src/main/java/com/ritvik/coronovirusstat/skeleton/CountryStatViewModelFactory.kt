package com.ritvik.coronovirusstat.skeleton

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ritvik.coronovirusstat.network.CountryData
import com.ritvik.coronovirusstat.network.CountryInfo

class CountryStatViewModelFactory(
    private val countryData: CountryData,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountryStatViewModel::class.java)) {
            return CountryStatViewModel(countryData,application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
