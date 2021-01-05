package com.example.almatyparking.domain.models

import com.example.almatyparking.data.ParkingPlaceDatabase.ParkingPlace
import java.util.*

data class Parking(
    val id: Long,
    val from: Date,
    val to: Date,
    val userID: Long,
    val carID: Long,
    val parkingPlaceID: Long
)