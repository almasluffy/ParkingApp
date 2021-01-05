package com.example.almatyparking.data.models

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime
import java.util.*

data class PaymentData (
    @SerializedName("id") val id: Int? = null,
    @SerializedName("cost") val cost: Int? = null,
    @SerializedName("fromDate") val fromDate: String? = null,
    @SerializedName("toDate") val toDate: String? = null,
    @SerializedName("isPaid") val isPaid: Int? = null,
    @SerializedName("parkingID") val parkingID: Int? = null,
    @SerializedName("carID") val carID: Int? = null
)
