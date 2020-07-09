package com.ritvik.coronovirusstat.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ritvik.coronovirusstat.R
import com.ritvik.coronovirusstat.databinding.NewStatLayoutBinding
import com.ritvik.coronovirusstat.skeleton.StatViewModel
import java.text.DecimalFormat
import kotlin.math.floor
import kotlin.math.log10


class StatFragment : Fragment() {

    private lateinit var statViewModel:StatViewModel
    private val update = "+"


    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<NewStatLayoutBinding>(inflater, R.layout.new_stat_layout, container, false)

        statViewModel = ViewModelProviders.of(this).get(StatViewModel::class.java)


        binding.lifecycleOwner = this



//        val yourFormattedString: String = formatter.format(100000)

        statViewModel.totalCases.observe(this, Observer { value ->
            binding.totalcasesTextview.text = value
            Log.i("loll","yes1")
        })

        statViewModel.totalDeaths.observe(this, Observer { value ->
            binding.totaldeathsTextview.text = value
            Log.i("loll","2")
        })

        statViewModel.newCases.observe(this, Observer { value ->
            binding.newcasesTextview.text = update + value
            Log.i("loll","3")
        })

        statViewModel.newDeaths.observe(this, Observer { value ->
            binding.newdeathsTextview.text = update + value
            Log.i("loll","4")
        })

        statViewModel.totalRecovered.observe(this, Observer { value ->
            binding.totalRecoveriesTextview.text = value
            Log.i("loll","yes5")
        })

        statViewModel.totalCritical.observe(this, Observer { value ->
            binding.totalcriticalTextview.text = value
            Log.i("loll","6")
        })

//        statViewModel.newCasesPm.observe(this, Observer { value ->
//            binding.newcasesPmTextview.text = update + value
//            Log.i("loll","7")
//        })
//
//        statViewModel.newDeathsPm.observe(this, Observer { value ->
//            binding.newdeathsPmTextview.text = update + value
//            Log.i("loll","8")
//        })

        statViewModel.firstCases.observe(this, Observer { value ->
            binding.firstCases.text = value.toString()
           // binding.firstFlag.setImageResource(R.drawable.usa)
            Log.i("loll","9")
        })

        statViewModel.firstDeaths.observe(this, Observer { value ->
            binding.firstDeaths.text = value.toString()
            Log.i("loll","9")
        })

        statViewModel.firstRecoveries.observe(this, Observer { value ->
            binding.firstCasesPm.text = value.toString()
            Log.i("loll","9")
        })

        statViewModel.firstCritical.observe(this, Observer { value ->
            binding.firstDeathsPm.text = value.toString()
            Log.i("loll","9")
        })

        statViewModel.secondCases.observe(this, Observer { value ->
            binding.secondCases.text = value.toString()
            // binding.firstFlag.setImageResource(R.drawable.usa)
            Log.i("loll","9")
        })

        statViewModel.secondDeaths.observe(this, Observer { value ->
            binding.secondDeaths.text = value.toString()
            Log.i("loll","9")
        })

        statViewModel.secondRecoveries.observe(this, Observer { value ->
            binding.secondCasesPm.text = value.toString()
            Log.i("loll","9")
        })

        statViewModel.secondCritical.observe(this, Observer { value ->
            binding.secondDeathsPm.text = value.toString()
            Log.i("loll","9")
        })

        statViewModel.thirdCases.observe(this, Observer { value ->
            binding.thirdCases.text = value.toString()
            // binding.firstFlag.setImageResource(R.drawable.usa)
            Log.i("loll","9")
        })

        statViewModel.thirdDeaths.observe(this, Observer { value ->
            binding.thirdDeaths.text = value.toString()
            Log.i("loll","9")
        })

        statViewModel.thirdRecoveries.observe(this, Observer { value ->
            binding.thirdCasesPm.text = value.toString()
            Log.i("loll","9")
        })

        statViewModel.thirdCritical.observe(this, Observer { value ->
            binding.thirdDeathsPm.text = value.toString()
            Log.i("loll","9")
        })

        statViewModel.newRecovered.observe(this, Observer { value ->
            binding.newrecoveriesTextview.text = update + value.toString()
            Log.i("loll","9")
        })

        statViewModel.newCritical.observe(this, Observer { value ->
            binding.newcriticalTextview.text = update + value.toString()
            Log.i("loll","9")
        })

        return binding.root
      }



    }


