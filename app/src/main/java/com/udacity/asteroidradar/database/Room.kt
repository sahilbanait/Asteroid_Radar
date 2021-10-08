package com.udacity.asteroidradar.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface AsteroidApiService{

    @Query("Select * from asteroid")
    fun getAsteroid(): LiveData<List<Asteroid>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg asteroid: Asteroid)

}
@Dao
interface PictureOfTheDayDao{
    @Query("SELECT* from picturoftheday")
    fun getPicture(): LiveData<PictureEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPicture(pictureOfTheDay: PictureEntity)
}

/**
 * DataBase
 */
@Database(entities = [Asteroid::class, PictureEntity::class],version = 1, exportSchema = false)
abstract class AsteroidDatabase: RoomDatabase(){
    abstract val asteroidDao: AsteroidApiService
    abstract val pictureofThedayDao:PictureOfTheDayDao
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