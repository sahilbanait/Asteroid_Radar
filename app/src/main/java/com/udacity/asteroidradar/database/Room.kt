package com.udacity.asteroidradar.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface AsteroidDao{

    @Query("Select * from asteroid ORDER BY closeApproachDate DESC")
    fun getAsteroid(): LiveData<List<AsteroidEntity>>

    @Query("SELECT * FROM asteroid WHERE closeApproachDate=:today")
    fun getTodayAsteroids(today: String): LiveData<List<AsteroidEntity>>

    @Query("SELECT * FROM asteroid WHERE closeApproachDate BETWEEN :today AND :sevenDay ORDER BY closeApproachDate ASC")
    fun getWeekAsteroids(today: String, sevenDay: String): LiveData<List<AsteroidEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg asteroid: AsteroidEntity)

}
@Dao
interface PictureOfTheDayDao{
    @Query("SELECT* from pictureOfTheDay")
    fun getPicture(): LiveData<PictureEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPicture(pictureOfTheDay: PictureEntity)
}

/**
 * DataBase
 */
@Database(entities = [AsteroidEntity::class, PictureEntity::class],version = 2, exportSchema = false)
abstract class AsteroidDatabase: RoomDatabase(){
    abstract val asteroidDao: AsteroidDao
    abstract val pictureofThedayDao:PictureOfTheDayDao
}

private lateinit var INSTANCE: AsteroidDatabase
fun getDatabase(context: Context): AsteroidDatabase{
    synchronized(AsteroidDatabase::class.java){
        if (!::INSTANCE.isInitialized){
            INSTANCE = Room
                .databaseBuilder(context.applicationContext,AsteroidDatabase::class.java,"Asteroid")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
    return INSTANCE
}