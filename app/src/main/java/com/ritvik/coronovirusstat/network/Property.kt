package com.ritvik.coronovirusstat.network

import com.squareup.moshi.Json


data class Property (
    @Json(name = "date")
    var date: String? = null,

    @Json(name = "total_cases")
    var totalCases: Double? = null,

    @Json(name = "new_cases")
    var newCases: Double? = null,

    @Json(name = "total_deaths")
    var totalDeaths: Double? = null,

    @Json(name = "new_deaths")
    var newDeaths: Double? = null,

    @Json(name = "total_cases_per_million")
    var totalCasesPerMillion: Double? = null,

    @Json(name = "new_cases_per_million")
    var newCasesPerMillion: Double? = null,

    @Json(name = "total_deaths_per_million")
    var totalDeathsPerMillion: Double? = null,

    @Json(name = "new_deaths_per_million")
    var newDeathsPerMillion: Double? = null,

    @Json(name = "stringency_index")
    var stringencyIndex: Double? = null

)