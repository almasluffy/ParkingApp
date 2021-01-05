package com.example.almatyparking.data.repository


import com.example.almatyparking.data.source.remote.AlmatyParkingApi
import com.example.almatyparking.domain.repository.ParkingPlacesRepository

class ParkingPlacesRepositoryImpl (
    private val almatyParkingApi: AlmatyParkingApi
): ParkingPlacesRepository {

    override suspend fun getParkingPlaces() = almatyParkingApi.getParkingPlaces().await().body()

}