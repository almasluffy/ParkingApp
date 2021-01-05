package com.example.almatyparking.data.models

import com.example.almatyparking.domain.models.Car
import com.example.almatyparking.domain.models.Parking
import com.example.almatyparking.domain.models.Payment
import com.google.gson.annotations.SerializedName

data class ProfileData (
    @SerializedName("first_name") val first_name: String? = null,
    @SerializedName("second_name") val second_name: String? = null,
    @SerializedName("balance") val balance: Double? = null
)