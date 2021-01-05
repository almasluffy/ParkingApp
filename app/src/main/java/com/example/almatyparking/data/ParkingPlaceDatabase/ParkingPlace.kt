package com.example.almatyparking.data.ParkingPlaceDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "parkingPlace_table")
data class ParkingPlace(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val address: String? = null,
    val workingHours: String? = null,
    val costPerHour: Int? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val availablePlaces: Int? = null,
    val freePlaces: Int? = null
)
