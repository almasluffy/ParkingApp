package com.example.almatyparking.domain.repository

import com.example.almatyparking.data.models.ParkingPlaces

interface ParkingPlacesRepository {

    suspend fun getParkingPlaces() : List<ParkingPlaces>?

}