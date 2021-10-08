package com.udacity.asteroidradar.main

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.*

import com.udacity.asteroidradar.database.getDatabase

import com.udacity.asteroidradar.respository.AsteroidRepository
import com.udacity.asteroidradar.respository.PictureOfTheDayRepository
import kotlinx.coroutines.launch

enum class AsteroidApiStatus { LOADING, ERROR, DONE }

class MainViewModel(application: Application) : AndroidViewModel(application) {


    private val database = getDatabase(application)
    private val asteroidRepository = AsteroidRepository(database)
    private val pictureOfTheDayRepository = PictureOfTheDayRepository(database)
    init {
        viewModelScope.launch {
            asteroidRepository.refreshAsteroid()
            pictureOfTheDayRepository.refreshPictureOfTheDay()

        }
    }
    val list = asteroidRepository.asteroid
    val pictureOfTheDay = pictureOfTheDayRepository.pictureOfTheDay

}