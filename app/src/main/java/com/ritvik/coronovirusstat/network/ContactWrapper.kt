package com.ritvik.coronovirusstat.network

import com.squareup.moshi.Json


data class OWIDWRL (
    @Json(name = "continent")
    var continent: String? = null,

    @Json(name = "location")
    var location: String? = null,

    @Json(name = "population")
    var population: Double? = null,

    @Json(name = "population_density")
    var populationDensity: Double? = null,

    @Json(name = "median_age")
    var medianAge: Double? = null,

    @Json(name = "aged_65_older")
    var aged65Older: Double? = null,

    @Json(name = "aged_70_older")
    var aged70Older: Double? = null,

    @Json(name = "gdp_per_capita")
    var gdpPerCapita: Double? = null,

    @Json(name = "cvd_death_rate")
    var cvdDeathRate: Double? = null,

    @Json(name = "diabetes_prevalence")
    var diabetesPrevalence: Double? = null,

    @Json(name = "handwashing_facilities")
    var handwashingFacilities: Double? = null,

    @Json(name = "hospital_beds_per_thousand")
    var hospitalBedsPerThousand: Double? = null,

    @Json(name = "life_expectancy")
    var lifeExpectancy: Double? = null,

    @Json(name = "data")
    var data: List<Property>? = null

)