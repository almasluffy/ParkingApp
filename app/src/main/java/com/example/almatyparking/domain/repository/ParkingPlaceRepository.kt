package com.example.almatyparking.domain.repository

import androidx.lifecycle.LiveData
import com.example.almatyparking.data.ParkingPlaceDatabase.ParkingPlace

interface ParkingPlaceRepository {

    fun getAllParkingPlaces(): LiveData<List<ParkingPlace>>

    fun getParkingPlace(id: Int): LiveData<ParkingPlace>

}