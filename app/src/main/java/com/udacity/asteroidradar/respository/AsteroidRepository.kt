package com.udacity.asteroidradar.respository

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.api.*

import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.database.asDatabaseModel
import com.udacity.asteroidradar.database.asDomainModel
import com.udacity.asteroidradar.domain.Model
import com.udacity.asteroidradar.domain.PictureOfTheDay
import com.udacity.asteroidradar.main.MainViewModel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class AsteroidRepository(private val database: AsteroidDatabase) {
    fun getSelectedAsteroid(filter: MainViewModel.MenuOption): LiveData<List<Model>>{
        return when(filter){
            MainViewModel.MenuOption.SHOW_DEFAULT ->
                Transformations.map(database.asteroidDao.getAsteroid()){
                    it.asDomainModel()
                }
            MainViewModel.MenuOption.SHOW_TODAY ->
                Transformations.map(database.asteroidDao.getTodayAsteroids(getToday())){
                    it.asDomainModel()
                }
            MainViewModel.MenuOption.SHOW_NEXT_WEEK ->
                Transformations.map(database.asteroidDao.getWeekAsteroids(getToday(), getWeek())){
                    it.asDomainModel()
                }
            MainViewModel.MenuOption.SHOW_SAVED ->
                Transformations.map(database.asteroidDao.getAsteroid()){
                    it.asDomainModel()
                }

        }
    }
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