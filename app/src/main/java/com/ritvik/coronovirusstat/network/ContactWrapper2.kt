package com.ritvik.coronovirusstat.network

import com.squareup.moshi.Json

data class CountryInfo (
    @Json(name = "_id")
    var id: Int? = null,

    @Json(name = "iso2")
    var iso2: String? = null,

    @Json(name = "iso3")
    var iso3: String? = null,

    @Json(name = "lat")
    var lat: Int? = null,

    @Json(name = "long")
    var long: Int? = null,

    @Json(name = "flag")
    var flag: String? = null

)
