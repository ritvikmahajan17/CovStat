package com.ritvik.coronovirusstat.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://corona.lmao.ninja/"
    private const val BASE_URL_WORLD = "https://corona.lmao.ninja/"

enum class Country (val value :String){
    INDIA("India"),
    USA("USA"),
    BRAZIL("Brazil"),
    Russia("Russia")
}

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .build()

    private val retrofitWorld = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL_WORLD)
        .build()

    private val retrofitCountrylist = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .build()

    interface ApiService {
        @GET("v2/countries?yesterday&sort=cases")
        fun getStats(): Deferred<List<CountryData>>
    }

    interface ApiServiceCountryList {
        @GET("v2/countries?yesterday&sort=iso3")
        fun getCountryList(): Deferred<List<CountryData>>
}

    interface ApiServiceWorld {
        @GET("v2/all")
        fun getWorldStats(): Deferred<WorldData>
    }


    object Api {
        val retrofitService: ApiService by lazy {
            retrofit.create(ApiService::class.java)
        }
    }

    object ApiWorld {
        val retrofitServiceWorld: ApiServiceWorld by lazy {
            retrofitWorld.create(ApiServiceWorld::class.java)
        }
    }

    object ApiCountryList {
        val retrofitServiceCountryList: ApiServiceCountryList by lazy {
            retrofitCountrylist.create(ApiServiceCountryList::class.java)
        }
    }