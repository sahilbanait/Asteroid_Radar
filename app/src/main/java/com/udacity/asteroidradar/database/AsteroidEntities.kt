package com.udacity.asteroidradar.database


import androidx.room.Entity
import androidx.room.PrimaryKey

import com.udacity.asteroidradar.domain.Model
import com.udacity.asteroidradar.domain.PictureOfTheDay



@Entity(tableName = "asteroid")
data class AsteroidEntity constructor(
    @PrimaryKey
    val id: Long, val codename: String, val closeApproachDate: String,
                    val absoluteMagnitude: Double, val estimatedDiameter: Double,
                    val relativeVelocity: Double, val distanceFromEarth: Double,
                    val isPotentiallyHazardous: Boolean)

fun List<AsteroidEntity>.asDomainModel(): List<Model>{
    return map {
        Model(
            id = it.id,
            codename = it.codename,
            closeApproachDate = it.closeApproachDate,
            absoluteMagnitude = it.absoluteMagnitude,
            estimatedDiameter = it.estimatedDiameter,
            relativeVelocity = it.relativeVelocity,
            distanceFromEarth = it.distanceFromEarth,
            isPotentiallyHazardous = it.isPotentiallyHazardous
        )
    }

}

@Entity(tableName = "pictureOfTheDay")
data class PictureEntity(
    @PrimaryKey
    val url: String,
    val mediaType: String,
    val tittle: String
)
fun PictureEntity.asDomainModel(): PictureOfTheDay {
    return PictureOfTheDay(
        url = this.url,
        mediaType = this.mediaType,
        title = this.tittle
    )
}