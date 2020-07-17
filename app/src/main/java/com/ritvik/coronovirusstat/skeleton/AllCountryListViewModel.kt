package com.ritvik.coronovirusstat.skeleton

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ritvik.coronovirusstat.network.Api
import com.ritvik.coronovirusstat.network.ApiCountryList
import com.ritvik.coronovirusstat.network.ApiCountryList.retrofitServiceCountryList
import com.ritvik.coronovirusstat.network.ApiServiceCountryList
import com.ritvik.coronovirusstat.network.CountryData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.ArrayList

class AllCountryListViewModel : ViewModel() {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _allCountryData = MutableLiveData<List<CountryData>>()
    val allCountryData: LiveData<List<CountryData>>
        get() = _allCountryData



    init {
        getCountries()
        Log.i("mahajan", "yes")
    }

    private fun getCountries() {
        coroutineScope.launch {
            val getStatsDeffered = ApiCountryList.retrofitServiceCountryList.getCountryList()
            try {
                val listResult = getStatsDeffered.await()
                _allCountryData.value = listResult
                Log.i("mahajan", listResult[0].country)
            } catch (e: Exception) {
                _allCountryData.value = ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()

    }
}