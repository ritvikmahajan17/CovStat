package com.ritvik.coronovirusstat.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CountryData (
    @Json(name = "updated")
    var updated: Double? = null,

    @Json(name = "country")
    var country: String? = null,

    @Json(name = "countryInfo")
    var countryInfo: CountryInfo? = null,

    @Json(name = "cases")
    var cases: Double? = null,

    @Json(name = "todayCases")
    var todayCases: Double? = null,

    @Json(name = "deaths")
    var deaths: Double? = null,

    @Json(name = "todayDeaths")
    var todayDeaths: Double? = null,

    @Json(name = "recovered")
    var recovered: Double? = null,

    @Json(name = "todayRecovered")
    var todayRecovered: Double? = null,

    @Json(name = "active")
    var active: Double? = null,

    @Json(name = "critical")
    var critical: Double? = null,

    @Json(name = "casesPerOneMillion")
    var casesPerOneMillion: Double? = null,

    @Json(name = "deathsPerOneMillion")
    var deathsPerOneMillion: Double? = null,

    @Json(name = "tests")
    var tests: Double? = null,

    @Json(name = "testsPerOneMillion")
    var testsPerOneMillion: Double? = null,

    @Json(name = "population")
    var population: Double? = null,

    @Json(name = "continent")
    var continent: String? = null,

    @Json(name = "oneCasePerPeople")
    var oneCasePerPeople: Double? = null,

    @Json(name = "oneDeathPerPeople")
    var oneDeathPerPeople: Double? = null,

    @Json(name = "oneTestPerPeople")
    var oneTestPerPeople: Double? = null,

    @Json(name = "activePerOneMillion")
    var activePerOneMillion: Double? = null,

    @Json(name = "recoveredPerOneMillion")
    var recoveredPerOneMillion: Double? = null,

    @Json(name = "criticalPerOneMillion")
    var criticalPerOneMillion: Double? = null

) : Parcelable
