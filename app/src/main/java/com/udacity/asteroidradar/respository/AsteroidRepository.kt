package com.udacity.asteroidradar.respository

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations

import com.udacity.asteroidradar.api.AsteroidApi
import com.udacity.asteroidradar.api.getToday
import com.udacity.asteroidradar.api.getWeek
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult

import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.database.asDatabaseModel
import com.udacity.asteroidradar.database.asDomainModel
import com.udacity.asteroidradar.domain.Model
import com.udacity.asteroidradar.domain.PictureOfTheDay

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class AsteroidRepository(private val database: AsteroidDatabase) {
    val asteroid: LiveData<List<Model>> = Transformations.map(database.asteroidDao.getAsteroid()){
        it.asDomainModel()
    }


    @RequiresApi(Build.VERSION_CODES.N)
    suspend fun  refreshAsteroid(){
     withContext(Dispatchers.IO){
         val asteroidList = AsteroidApi.retrofitServiceAsteroidService.getAsteroid(getToday(),
             getWeek())
         val parseAsteroiJsonResult = parseAsteroidsJsonResult(JSONObject(asteroidList))
         database.asteroidDao.insertAll(*parseAsteroiJsonResult.asDatabaseModel())
     }
    }
}
class PictureOfTheDayRepository(private val database: AsteroidDatabase) {

    val pictureOfTheDay: LiveData<PictureOfTheDay> =
        Transformations.map(database.pictureofThedayDao.getPicture()) {
            it?.asDomainModel()
        }

    suspend fun refreshPictureOfTheDay() {
        withContext(Dispatchers.IO) {
            try {
                val pictureOfTheDay =
                    AsteroidApi.retrofitServicePictureofTheDayApi.getPictureofTheDay()
                database.pictureofThedayDao.insertPicture(pictureOfTheDay.asDatabaseModel())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}