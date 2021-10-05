package com.udacity.asteroidradar.respository

import android.net.Network
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.database.asDomainModel
import com.udacity.asteroidradar.domain.Domain
import com.udacity.asteroidradar.network.AsteroidDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AsteroidRepository(private val database: AsteroidDatabase) {
    val asteroid: LiveData<List<Domain>> = Transformations.map(database.asteroidDao.getAsteroid()){
        it.asDomainModel()
    }
    suspend fun  refreshAsteroid(){
     withContext(Dispatchers.IO){
        database.asteroidDao.insertAll()
     }
    }
}