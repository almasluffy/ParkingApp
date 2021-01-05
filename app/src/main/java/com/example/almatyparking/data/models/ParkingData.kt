package com.example.almatyparking.data.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class ParkingData (
    @SerializedName("id") val id: Long? = null,
    @SerializedName("costPerHOur") val costPerHour: Int? = null,
    @SerializedName("from") val from: Date? = null,
    @SerializedName("to") val to: Date? = null,
    @SerializedName("parkingID") val parkingID: Long? = null,
    @SerializedName("carID") val carID: Long? = null
)