package com.udacity.asteroidradar.main

import android.app.Application
import android.net.Network
import androidx.lifecycle.*
import com.udacity.asteroidradar.database.Asteroid
import com.udacity.asteroidradar.network.AsteroidApi
import com.udacity.asteroidradar.network.AsteroidApiService
import com.udacity.asteroidradar.network.PictureOfDay
import kotlinx.coroutines.launch

enum class AsteroidApiStatus { LOADING, ERROR, DONE }
class MainViewModel(picture: PictureOfDay, application: Application) : AndroidViewModel(application) {

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
    get() = _response

    private val _listProperties = MutableLiveData<List<Asteroid>>()
    val listProperties:LiveData<List<Asteroid>>
        get() = _listProperties

    private val _pictureOfTheDay = MutableLiveData<PictureOfDay>()
    val pictureOfDay: LiveData<PictureOfDay>
    get() = _pictureOfTheDay

    init {
        getAsterioidProperties()
        _pictureOfTheDay.value = picture
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() = viewModelScope.launch {

    }


    private fun getAsterioidProperties() {
        viewModelScope.launch {
            _response.value = AsteroidApiStatus.LOADING.toString()
            try {
                _listProperties.value
            }
            catch (e:Exception){
                _response.value = AsteroidApiStatus.DONE.toString()
                _listProperties.value =ArrayList()

            }
        }
    }
}