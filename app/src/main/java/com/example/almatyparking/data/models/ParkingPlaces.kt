package com.example.almatyparking.data.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class ParkingPlaces(
    @SerializedName("id") val id: Long? = null,
    @SerializedName("address") val address: String? = null,
    @SerializedName("workingHours") val workingHours: String? = null,
    @SerializedName("costPerHour") val costPerHour: Int? = null,
    @SerializedName("latitude") val latitude: Double? = null,
    @SerializedName("longitude") val longitude: Double? = null,
    @SerializedName("availablePlaces") val availablePlaces: Int? = null,
    @SerializedName("freePlaces") val freePlaces: Int? = null
)