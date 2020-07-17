package com.ritvik.coronovirusstat.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ritvik.coronovirusstat.databinding.ListViewItemBinding
import com.ritvik.coronovirusstat.network.CountryData


class RecyclerVIewAdapter (private val onClickListener: OnClickListener): ListAdapter<CountryData, RecyclerVIewAdapter.CountryDataViewHolder>(DiffCallback) {


    class CountryDataViewHolder(private var binding:ListViewItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(countryData:CountryData){
            binding.listview = countryData
            binding.executePendingBindings()
        }
    }


    companion object DiffCallback : DiffUtil.ItemCallback<CountryData>() {
        override fun areItemsTheSame(oldItem: CountryData, newItem: CountryData): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: CountryData, newItem: CountryData): Boolean {
            return oldItem.country == newItem.country
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryDataViewHolder {
        return CountryDataViewHolder(ListViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CountryDataViewHolder, position: Int) {
        val countryData = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(countryData)
        }
        holder.bind(countryData)
    }

    class OnClickListener(val clickListener : ( countryData : CountryData)-> Unit) {
        fun onClick(countryData: CountryData) = clickListener(countryData)
    }



}