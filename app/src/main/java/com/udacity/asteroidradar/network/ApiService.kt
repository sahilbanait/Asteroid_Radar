package com.udacity.asteroidradar.network

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.database.Asteroid
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

//@Database(entities = [Asteroid::class], version = 1, exportSchema = false)
//abstract class Room:RoomDatabase() {
//
//}
const val BASE_URL = "https://api.nasa.gov/neo/rest/v1/feed?start_date=START_DATE&end_date=END_DATE&api_key=dD2SDcgM8IF6WuZgfJu45OHfYZxhOuK0LfklOrRs"
enum class MarsApiFilter(val value: String) {NEXT_WEEK_ASTEROID("next"), VIEW_TODAY_ASTEROID("Today"), VIEW_SAVED_ASTEROID("saved") }

private val retrofit = Retrofit.Builder().baseUrl(BASE_URL).build()

@Dao
interface AsteroidApiService{

    @Query("Select * from asteroid")
    fun getAsteroid(): LiveData<List<Asteroid>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg asteroid: Asteroid)

}

/**
 * DataBase
 */
@Database(entities = [Asteroid::class],version = 1, exportSchema = false)
abstract class AsteroidDatabase: RoomDatabase(){
            abstract val asteroidDao: AsteroidApiService
}
private lateinit var INSTANCE: AsteroidDatabase

fun getDatabase(context: Context): AsteroidDatabase{
    synchronized(AsteroidDatabase::class.java){
        if (!::INSTANCE.isInitialized){
            INSTANCE = Room.databaseBuilder(context.applicationContext,AsteroidDatabase::class.java,"Asteroid").build()
        }
    }
    return INSTANCE
}
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

object AsteroidApi {

    private val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(MoshiConverterFactory.create(
        moshi)).addCallAdapterFactory(CoroutineCallAdapterFactory()).build()
    val retrofitService= retrofit.create(AsteroidApiService::class.java)
}