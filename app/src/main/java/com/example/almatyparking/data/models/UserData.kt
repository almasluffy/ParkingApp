package com.example.almatyparking.data.models

import com.example.almatyparking.domain.models.Car
import com.example.almatyparking.domain.models.Parking
import com.example.almatyparking.domain.models.Payment
import com.google.gson.annotations.SerializedName

data class UserData (
    @SerializedName("id") val id: Int? = null,
    @SerializedName("phone_number") val phone_number: String? = null,
    @SerializedName("balance") val balance: Int? = null,
    @SerializedName("cars") val cars: String? = null
)