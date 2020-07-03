package com.ritvik.coronovirusstat.network

import com.squareup.moshi.Json


class statsData {
    @Json(name = "OWID_WRL")
    var world:OWIDWRL ? = null

}