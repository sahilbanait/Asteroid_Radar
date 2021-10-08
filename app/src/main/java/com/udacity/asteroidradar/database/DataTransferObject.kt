package com.udacity.asteroidradar.database

import com.squareup.moshi.JsonClass
import com.udacity.asteroidradar.domain.Model
import com.udacity.asteroidradar.domain.PictureOfTheDay



//@JsonClass(generateAdapter = true)
//data class NetworkAsteroid (val id: Long, val codename: String, val closeApproachDate: String,
//val absoluteMagnitude: Double, val estimatedDiameter: Double,
//val relativeVelocity: Double, val distanceFromEarth: Double,
//val isPotentiallyHazardous: Boolean)


fun List<Model>.asDatabaseModel(): Array<Asteroid>{
    return map {
        Asteroid(
            id = it.id,
            codename = it.codename,
            closeApproachDate = it.closeApproachDate,
            absoluteMagnitude = it.absoluteMagnitude,
            estimatedDiameter = it.estimatedDiameter,
            relativeVelocity = it.relativeVelocity,
            distanceFromEarth = it.distanceFromEarth,
            isPotentiallyHazardous = it.isPotentiallyHazardous
        )
    }.toTypedArray()
}

fun PictureOfTheDay.asDatabaseModel(): PictureEntity{
    return (
            PictureEntity(
                url = this.url,
                mediaType = this.mediaType,
                tittle = this.title)
            )
}