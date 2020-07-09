package com.ritvik.coronovirusstat.skeleton

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

    private val _firstCases = MutableLiveData<String>()
    val firstCases: LiveData<String>
        get() =  _firstCases

    private val _firstDeaths = MutableLiveData<String>()
    val firstDeaths: LiveData<String>
        get() =  _firstDeaths

    private val _firstRecovories = MutableLiveData<String>()
    val firstRecoveries: LiveData<String>
        get() =  _firstRecovories

    private val _firstCritical = MutableLiveData<String>()
    val firstCritical: LiveData<String>
        get() =  _firstCritical

    private val _secondCases = MutableLiveData<String>()
    val secondCases: LiveData<String>
        get() =  _secondCases

    private val _secondDeaths = MutableLiveData<String>()
    val secondDeaths: LiveData<String>
        get() =  _secondDeaths

    private val _secondRecoveries = MutableLiveData<String>()
    val secondRecoveries: LiveData<String>
        get() =  _secondRecoveries

    private val _secondCritical = MutableLiveData<String>()
    val secondCritical: LiveData<String>
        get() =  _secondCritical

    private val _thirdCases = MutableLiveData<String>()
    val thirdCases: LiveData<String>
        get() =  _thirdCases

    private val _thirdDeaths = MutableLiveData<String>()
    val thirdDeaths: LiveData<String>
        get() =  _thirdDeaths

    private val _thirdRecoveries = MutableLiveData<String>()
    val thirdRecoveries: LiveData<String>
        get() =  _thirdRecoveries

    private val _thirdCritical = MutableLiveData<String>()
    val thirdCritical: LiveData<String>
        get() =  _thirdCritical

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

    fun prettyCount(number: Number): String {
        val suffix = charArrayOf(' ', 'k', 'M', 'B', 'T', 'P', 'E')
        val numValue: Long = number.toLong()
        val value = floor(log10(numValue.toDouble())).toInt()
        val base = value / 3
        return if (value >= 3 && base < suffix.size) {
            DecimalFormat("#0.0")
                .format(numValue / Math.pow(10.0, base * 3.toDouble())) + suffix[base]
        } else {
            DecimalFormat("#,##0").format(numValue)
        }
    }

    fun formatNumber(number: Double?): String? {
        return NumberFormat.getNumberInstance(Locale.getDefault()).format(number)
    }

    private val _status = MutableLiveData<String>()
        val status : LiveData<String>
            get() = _status


    init {
        getStats(Country.INDIA)
        getStats(Country.USA)
       // getStats(Country.Russia)
        getStats(Country.BRAZIL)
        getStatsWorld()
    }

    private fun getStats(Countryfilter:Country) {
        coroutineScope.launch {
            var getStatsDeffered = Api.retrofitService.getStats(Countryfilter.value)
            try {
                var listResult = getStatsDeffered.await()
                _status.value = "Success"

                if(Countryfilter.value == "USA")
                {
                    _firstCases.value = listResult.cases?.let { prettyCount(it) }
                    _firstDeaths.value = listResult.deaths?.let { prettyCount(it) }
                    _firstRecovories.value = listResult.recovered?.let { prettyCount(it) }
                    _firstCritical.value = listResult.critical?.let { prettyCount(it) }
                }

                else if(Countryfilter.value == "Brazil")
                {
                    _secondCases.value = listResult.cases?.let { prettyCount(it) }
                    _secondDeaths.value = listResult.deaths?.let { prettyCount(it) }
                    _secondRecoveries.value = listResult.recovered?.let { prettyCount(it) }
                    _secondCritical.value = listResult.critical?.let { prettyCount(it) }
                }

                else if(Countryfilter.value == "India")
                {
                    _thirdCases.value = listResult.cases?.let { prettyCount(it) }
                    _thirdDeaths.value = listResult.deaths?.let { prettyCount(it) }
                    _thirdRecoveries.value = listResult.recovered?.let { prettyCount(it) }
                    _thirdCritical.value = listResult.critical?.let { prettyCount(it) }
                }

            }
            catch (e : Exception){
                _status.value = "failure -- ${e.message}"
            }
        }
    }

    private fun getStatsWorld() {
        coroutineScope.launch {
            var getStatsDeffered = ApiWorld.retrofitServiceWorld.getWorldStats()
            try {
                var listResult = getStatsDeffered.await()
                _status.value = "Success"

                    _totalCases.value = formatNumber(listResult.cases)

                    _totalDeaths.value = formatNumber(listResult.deaths)

                    _newCases.value = listResult.todayCases.toString()

                    _newDeaths.value =listResult.todayDeaths?.roundToInt().toString()
//
                    _totalRecovered.value = formatNumber(listResult.recovered)

                    _totalCritical.value = formatNumber(listResult.active)

                    _newRecovered.value = listResult.todayRecovered.toString()

                    _newCritical.value = listResult.critical?.roundToInt().toString()



            }
            catch (e : Exception){
                _status.value = "failure -- ${e.message}"
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}