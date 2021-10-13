package com.udacity.asteroidradar.api

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.BuildConfig
import com.udacity.asteroidradar.Constants

import com.udacity.asteroidradar.domain.Model

import com.udacity.asteroidradar.domain.PictureOfTheDay
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET


//enum class MarsApiFilter(val value: String) {NEXT_WEEK_ASTEROID("next"), VIEW_TODAY_ASTEROID("Today"), VIEW_SAVED_ASTEROID("saved") }


interface AsteroidService{
    @GET("neo/rest/v1/feed")
    fun getAsteroid(
        @retrofit2.http.Query("start_date") startDate: String,
        @retrofit2.http.Query("end_date") endDate: String,
        @retrofit2.http.Query("api") apiKey: String = BuildConfig.Nasa_API_Key
    ): String
}
interface PictureofTheDayApi{
    @GET("planetary")
    fun getPictureofTheDay(
        @retrofit2.http.Query("api")apiKey: String = BuildConfig.Nasa_API_Key
    ): PictureOfTheDay
}
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

object AsteroidApi {

    private val retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
    val retrofitService= retrofit.create(AsteroidService::class.java)
    val retrofitServicePictureofTheDayApi= retrofit.create(PictureofTheDayApi::class.java)
}