package com.udacity.asteroidradar.main

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.udacity.asteroidradar.api.AsteroidApiFilter

import com.udacity.asteroidradar.database.getDatabase
import com.udacity.asteroidradar.domain.Model

import com.udacity.asteroidradar.respository.AsteroidRepository
import com.udacity.asteroidradar.respository.PictureOfTheDayRepository
import kotlinx.coroutines.launch
import java.lang.Exception

enum class AsteroidApiStatus { LOADING, ERROR, DONE }

@RequiresApi(Build.VERSION_CODES.N)
class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val _navigateToSelectedAsteroid = MutableLiveData<Model>()
    val navigateToSelectedAsteroid: LiveData<Model?>
    get() = _navigateToSelectedAsteroid

    private val _status = MutableLiveData<AsteroidApiStatus>()
    val status:LiveData<AsteroidApiStatus>
    get() = _status

    private val database = getDatabase(application)
    private val asteroidRepository = AsteroidRepository(database)
    private val pictureOfTheDayRepository = PictureOfTheDayRepository(database)
    val list = asteroidRepository.asteroid
    val pictureOfTheDay = pictureOfTheDayRepository.pictureOfTheDay

    init {
        viewModelScope.launch {
          getTodayAsteroid()
          pictureOfTheDayRepository.refreshPictureOfTheDay()

        }
    }
    private fun getTodayAsteroid(){
        viewModelScope.launch {
            _status.value = AsteroidApiStatus.LOADING
            try {
                asteroidRepository.refreshAsteroid()
                _status.value = AsteroidApiStatus.DONE
            }catch (e: Exception){
                _status.value = AsteroidApiStatus.ERROR
            }
        }
    }


}