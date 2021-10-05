package com.udacity.asteroidradar.respository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.database.asDomainModel
import com.udacity.asteroidradar.domain.Model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AsteroidRepository(private val database: AsteroidDatabase) {
    val asteroid: LiveData<List<Model>> = Transformations.map(database.asteroidDao.getAsteroid()){
        it.asDomainModel()
    }
    suspend fun  refreshAsteroid(){
     withContext(Dispatchers.IO){
        database.asteroidDao.insertAll()
     }
    }
}