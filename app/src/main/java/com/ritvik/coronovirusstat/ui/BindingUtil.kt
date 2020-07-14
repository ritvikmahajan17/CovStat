package com.ritvik.coronovirusstat.ui

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ritvik.coronovirusstat.R
import com.ritvik.coronovirusstat.network.CountryData
import com.ritvik.coronovirusstat.network.CountryInfo
import com.ritvik.coronovirusstat.skeleton.ApiStatus
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*
import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.roundToInt

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

fun formatNumber(number: Int?): String? {
    return NumberFormat.getNumberInstance(Locale.getDefault()).format(number)
}

val update = "+"

@BindingAdapter("countryListData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<CountryData>?) {
    val adapter = recyclerView.adapter as RecyclerVIewAdapter
    adapter.submitList(data)

}

@BindingAdapter("countryCases")
fun TextView.setCases(data: CountryData) {
    text = data.cases?.let { prettyCount(it) }
}

@BindingAdapter("countryDeaths")
fun TextView.setDeaths(data: CountryData) {
    text = data.deaths?.let { prettyCount(it) }
}

@BindingAdapter("countryRecovered")
fun TextView.setRecovered(data: CountryData) {
    text = data.recovered?.let { prettyCount(it) }
}

@BindingAdapter("countryCritical")
fun TextView.setCritical(data: CountryData) {
    text = data.active?.let { prettyCount(it) }

}

@BindingAdapter("countryName")
fun TextView.setName(data: CountryData) {
    text = data.country
}

@BindingAdapter("newCases")
fun TextView.setNewCases(data: CountryData) {
    text =  update + data.todayCases?.roundToInt()
}

@BindingAdapter("totalCases")
fun TextView.setTotalCases(data: CountryData) {
    text = formatNumber(data.cases?.roundToInt())
}

@BindingAdapter("newDeaths")
fun TextView.setNewDeaths(data: CountryData) {
    text =  update + data.todayDeaths?.roundToInt()
}

@BindingAdapter("totalDeaths")
fun TextView.setTotalDeaths(data: CountryData) {
    text = formatNumber(data.deaths?.roundToInt())
}

@BindingAdapter("newRecovered")
fun TextView.setNewRecovered(data: CountryData) {
    text =  update + data.todayRecovered?.roundToInt()
}

@BindingAdapter("totalRecovered")
fun TextView.setTotalRecovered(data: CountryData) {
    text = formatNumber(data.recovered?.roundToInt())
}

@BindingAdapter("newCritical")
fun TextView.setNewCritical(data: CountryData) {
    text = update + data.critical?.roundToInt()
}

@BindingAdapter("totalCritical")
fun TextView.setTotalCritical(data: CountryData) {
    text = formatNumber(data.active?.roundToInt())
}



@BindingAdapter("countryFlag")
fun bindImage(imgView: ImageView, imgUrl: String? ) {
    Log.i("mahajan","${imgUrl}")
    imgUrl.let {
        val imgUri = imgUrl?.toUri()?.buildUpon()?.scheme("https")?.build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .error(R.drawable.ic_broken_image))
            .into(imgView)

    }

}

@BindingAdapter("ApiStatus")
fun bindStatus(statusImageView: ImageView, status: ApiStatus?) {
    when (status) {
       ApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        ApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        ApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}


