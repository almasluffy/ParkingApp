package com.example.almatyparking.data.repository

import androidx.lifecycle.LiveData
import com.example.almatyparking.data.ParkingPlaceDatabase.ParkingPlace
import com.example.almatyparking.data.ParkingPlaceDatabase.ParkingPlaceDao
import com.example.almatyparking.domain.repository.ParkingPlaceRepository

class ParkingPlaceRepositoryImpl(private val parkingPlaceDao: ParkingPlaceDao): ParkingPlaceRepository {

    override fun getAllParkingPlaces(): LiveData<List<ParkingPlace>> = parkingPlaceDao.getParkingPlaces()

    override fun getParkingPlace(id: Int): LiveData<ParkingPlace>  = parkingPlaceDao.getParkingPlace(id)
}
