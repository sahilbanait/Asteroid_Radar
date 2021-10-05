package com.udacity.asteroidradar.api

import com.squareup.moshi.JsonClass
import com.udacity.asteroidradar.database.Asteroid
import com.udacity.asteroidradar.domain.Model

@JsonClass(generateAdapter = true)
data class NetworkAsteroidContainer(val asteroid: List<NetworkAsteroid>)

@JsonClass(generateAdapter = true)
data class NetworkAsteroid (val id: Long, val codename: String, val closeApproachDate: String,
val absoluteMagnitude: Double, val estimatedDiameter: Double,
val relativeVelocity: Double, val distanceFromEarth: Double,
val isPotentiallyHazardous: Boolean)


fun NetworkAsteroidContainer.asDomainMode(): List<Model>{
    return asteroid.map {
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
fun NetworkAsteroidContainer.asDatabaseMode(): Array<Asteroid>{
    return asteroid.map {
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