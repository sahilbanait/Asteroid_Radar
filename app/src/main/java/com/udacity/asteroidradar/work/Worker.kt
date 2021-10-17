package com.udacity.asteroidradar.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.udacity.asteroidradar.database.getDatabase
import com.udacity.asteroidradar.respository.AsteroidRepository
import com.udacity.asteroidradar.respository.PictureOfTheDayRepository
import retrofit2.HttpException

class RefreshDataWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repositoryPictureOfTheDay = PictureOfTheDayRepository(database)
        val repositoryAsteroids = AsteroidRepository(database)
        return try {
            repositoryPictureOfTheDay.refreshPictureOfTheDay()
            repositoryAsteroids.refreshAsteroid()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}