package com.ritvik.coronovirusstat.skeleton

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ritvik.coronovirusstat.network.*
import kotlinx.coroutines.*
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*
import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.roundToInt

enum class ApiStatus { LOADING, ERROR, DONE }

class StatViewModel : ViewModel() {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob+ Dispatchers.Main)


    private val _totalCases = MutableLiveData<String>()
     val totalCases : LiveData<String>
        get() =  _totalCases

    private val _totalDeaths = MutableLiveData<String>()
    val totalDeaths : LiveData<String>
        get() =  _totalDeaths

    private val _newCases = MutableLiveData<String>()
    val newCases: LiveData<String>
        get() =  _newCases

    private val _newDeaths = MutableLiveData<String>()
    val newDeaths: LiveData<String>
        get() =  _newDeaths

    private val _newCasesPm = MutableLiveData<String>()
    val newCasesPm: LiveData<String>
        get() =  _newCasesPm

    private val _totalCasesPm = MutableLiveData<String>()
    val totalCasesPm: LiveData<String>
        get() =  _totalCasesPm

    private val _newDeathsPm = MutableLiveData<String>()
    val newDeathsPm: LiveData<String>
        get() =  _newDeathsPm

    private val _totalDeathsPm = MutableLiveData<String>()
    val totalDeathsPm: LiveData<String>
        get() =  _totalDeathsPm

    private val _totalRecovered = MutableLiveData<String>()
    val totalRecovered: LiveData<String>
        get() =  _totalRecovered

    private val _totalCritical = MutableLiveData<String>()
    val totalCritical: LiveData<String>
        get() =  _totalCritical

    private val _newCritical = MutableLiveData<String>()
    val newCritical: LiveData<String>
        get() =  _newCritical

    private val _newRecovered = MutableLiveData<String>()
    val newRecovered: LiveData<String>
        get() =  _newRecovered

    private val _countryData = MutableLiveData<List<CountryData>>()
    val countryData: LiveData<List<CountryData>>
        get() =  _countryData

    private val _navigateToSelectedCountry = MutableLiveData<CountryData>()
    val navigateToSelectedCountry: LiveData<CountryData>
        get() = _navigateToSelectedCountry


    fun formatNumber(number: Double?): String? {
        return NumberFormat.getNumberInstance(Locale.getDefault()).format(number)
    }

    private val _status = MutableLiveData<ApiStatus>()
        val status : LiveData<ApiStatus>
            get() = _status


    init {
        getStats()
        getStatsWorld()
    }

    @SuppressLint("LogNotTimber")
    private fun getStats() {
        coroutineScope.launch {
            var getStatsDeffered = Api.retrofitService.getStats()
            try {
                var listResult = getStatsDeffered.await()

                _countryData.value = listResult


            }
            catch (e : Exception){

                _countryData.value =ArrayList()
            }
        }
    }

    private fun getStatsWorld() {
        coroutineScope.launch {
            val getStatsDeffered = ApiWorld.retrofitServiceWorld.getWorldStats()
            try {
                _status.value = ApiStatus.LOADING
                val listResult = getStatsDeffered.await()
                _status.value = ApiStatus.DONE

                    _totalCases.value = formatNumber(listResult.cases)

                    _totalDeaths.value = formatNumber(listResult.deaths)

                    _newCases.value = listResult.todayCases?.roundToInt().toString()
                     Log.i("mahajan",_newCases.value)
                    _newDeaths.value =listResult.todayDeaths?.roundToInt().toString()

                    _totalRecovered.value = formatNumber(listResult.recovered)

                    _totalCritical.value = formatNumber(listResult.active)

                    _newRecovered.value = listResult.todayRecovered.toString()

                    _newCritical.value = listResult.critical?.roundToInt().toString()



            }
            catch (e : Exception){
                _status.value = ApiStatus.ERROR
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun displayCountryDetails(countryData: CountryData) {
        _navigateToSelectedCountry.value = countryData
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedCountry.value = null
    }

}