package com.udacity.asteroidradar.main

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.udacity.asteroidradar.api.AsteroidApi


import com.udacity.asteroidradar.database.getDatabase
import com.udacity.asteroidradar.domain.Model

import com.udacity.asteroidradar.respository.AsteroidRepository
import com.udacity.asteroidradar.respository.PictureOfTheDayRepository
import kotlinx.coroutines.launch
import java.lang.Exception


enum class AsteroidStatus {LOADING, ERROR, DONE}


class MainViewModel(application: Application) : AndroidViewModel(application) {
    enum class MenuOption { SHOW_TODAY, SHOW_NEXT_WEEK, SHOW_SAVED, SHOW_DEFAULT}

    private val _navigateToSelectedAsteroid = MutableLiveData<Model?>()
    val navigateToSelectedAsteroid: LiveData<Model?>
    get() = _navigateToSelectedAsteroid

    private val asteroidMenuOptions = MutableLiveData<MenuOption>()
    val list = Transformations.switchMap(asteroidMenuOptions) { menuOption ->
        asteroidRepository.getSelectedAsteroid(menuOption)

    }
    private val _status = MutableLiveData<AsteroidStatus>()
    val status:LiveData<AsteroidStatus>
        get() = _status


    private val database = getDatabase(application)
    private val asteroidRepository = AsteroidRepository(database)
    private val pictureOfTheDayRepository = PictureOfTheDayRepository(database)
    val pictureOfTheDay = pictureOfTheDayRepository.pictureOfTheDay

    init {

        viewModelScope.launch {
            asteroidMenuOptions.postValue(MenuOption.SHOW_DEFAULT)
            _status.value = AsteroidStatus.LOADING
            try {
                pictureOfTheDayRepository.refreshPictureOfTheDay()
                asteroidRepository.refreshAsteroid()
                _status.value = AsteroidStatus.DONE
            }catch (e: Exception){
                _status.value = AsteroidStatus.ERROR
            }

        }
    }

    fun displayAsteroidDetails(model: Model) {
    _navigateToSelectedAsteroid.value = model
}
    fun displayAsteroidDetailsComplete(){
        _navigateToSelectedAsteroid.value = null
    }
    fun onAsteroidClicked(model: Model){
        _navigateToSelectedAsteroid.value = model
    }
    fun updateAsteroidOptionList(option: MenuOption) {
        asteroidMenuOptions.postValue(option)
    }


}