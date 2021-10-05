package com.udacity.asteroidradar.main

import android.app.Application
import androidx.lifecycle.*
import com.udacity.asteroidradar.database.Asteroid
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.database.getDatabase

import com.udacity.asteroidradar.respository.AsteroidRepository
import kotlinx.coroutines.launch

enum class AsteroidApiStatus { LOADING, ERROR, DONE }
class MainViewModel( application: Application) : AndroidViewModel(application) {

//    private val _response = MutableLiveData<String>()
//    val response: LiveData<String>
//    get() = _response
//
//    private val _listProperties = MutableLiveData<List<Asteroid>>()
//    val listProperties:LiveData<List<Asteroid>>
//        get() = _listProperties
//
//    private val _pictureOfTheDay = MutableLiveData<PictureOfDay>()
//    val pictureOfDay: LiveData<PictureOfDay>
//    get() = _pictureOfTheDay
//
//    init {
//        getAsterioidProperties()
//        _pictureOfTheDay.value
//
//    }
    private val database = getDatabase(application)
    private val asteroidRepository = AsteroidRepository(database)
    init {
        viewModelScope.launch {
            asteroidRepository.refreshAsteroid()
        }
    }
    val list = asteroidRepository.asteroid




//    private fun getAsterioidProperties() {
//        viewModelScope.launch {
//            _response.value = AsteroidApiStatus.LOADING.toString()
//            try {
//                _listProperties.value
//            }
//            catch (e:Exception){
//                _response.value = AsteroidApiStatus.DONE.toString()
//                _listProperties.value =ArrayList()
//
//            }
//        }
//    }

}