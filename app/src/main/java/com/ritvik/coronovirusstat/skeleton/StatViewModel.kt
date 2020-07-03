package com.ritvik.coronovirusstat.skeleton

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.ritvik.coronovirusstat.network.Api
import com.ritvik.coronovirusstat.network.Property
import com.ritvik.coronovirusstat.network.statsData

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*
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


    private val _status = MutableLiveData<String>()
        val status : LiveData<String>
            get() = _status

    init {
        getStats()
    }

    private fun getStats() {
        coroutineScope.launch {
            var getStatsDeffered = Api.retrofitService.getStats()

            try {
                var listResult = getStatsDeffered.await()
                _status.value = "Success"
                val getCurrent = listResult.world?.data?.size

                if (getCurrent != null) {
                    _totalCases.value = listResult.world?.data?.get(getCurrent.minus(1))?.totalCases?.roundToInt()
                        .toString()

                    _totalDeaths.value = listResult.world?.data?.get(getCurrent.minus(1))?.totalDeaths?.roundToInt()
                        .toString()
                }
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