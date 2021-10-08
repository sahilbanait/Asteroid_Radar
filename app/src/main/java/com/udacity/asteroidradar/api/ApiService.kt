package com.udacity.asteroidradar.api

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.Constants

import com.udacity.asteroidradar.domain.Model

import com.udacity.asteroidradar.domain.PictureOfTheDay
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import kotlin.reflect.jvm.internal.impl.load.java.Constant

//@Database(entities = [Asteroid::class], version = 1, exportSchema = false)
//abstract class Room:RoomDatabase() {
//
//}

enum class MarsApiFilter(val value: String) {NEXT_WEEK_ASTEROID("next"), VIEW_TODAY_ASTEROID("Today"), VIEW_SAVED_ASTEROID("saved") }


interface AsteroidService{
    @GET
    fun getList(): Deferred<Model>
}
interface PictureofTheDayApi{
    @GET
    fun getPictureofTheDay(
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