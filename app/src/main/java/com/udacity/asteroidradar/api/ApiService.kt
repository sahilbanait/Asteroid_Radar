package com.udacity.asteroidradar.api

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.BuildConfig
import com.udacity.asteroidradar.Constants


import com.udacity.asteroidradar.domain.PictureOfTheDay
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

interface AsteroidService{
    @GET("neo/rest/v1/feed")
    suspend fun getAsteroid(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("api_key") apiKey: String = BuildConfig.Nasa_API_Key
    ): String
}
interface PictureofTheDayApi{
    @GET("planetary/apod")
    suspend fun getPictureofTheDay(

        @Query("api_key")apiKey: String = BuildConfig.Nasa_API_Key
    ): PictureOfTheDay
}
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

object AsteroidApi {
    var okHttpClient = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi)).client(okHttpClient).addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
    val retrofitServiceAsteroidService= retrofit.create(AsteroidService::class.java)
    val retrofitServicePictureofTheDayApi= retrofit.create(PictureofTheDayApi::class.java)
}