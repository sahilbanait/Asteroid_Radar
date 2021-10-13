package com.udacity.asteroidradar.database

import com.squareup.moshi.JsonClass
import com.udacity.asteroidradar.domain.Model
import com.udacity.asteroidradar.domain.PictureOfTheDay



fun List<Model>.asDatabaseModel(): Array<AsteroidEntity>{
    return map {
        AsteroidEntity(
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