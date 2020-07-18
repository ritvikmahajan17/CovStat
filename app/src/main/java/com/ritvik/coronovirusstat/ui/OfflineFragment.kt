package com.ritvik.coronovirusstat.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ritvik.coronovirusstat.R
import com.ritvik.coronovirusstat.databinding.AllCountryListLayoutBinding
import com.ritvik.coronovirusstat.databinding.OfflineLayoutBinding


class OfflineFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = OfflineLayoutBinding.inflate(inflater)

        return binding.root
    }

}