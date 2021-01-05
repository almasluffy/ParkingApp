package com.example.almatyparking.data.models

import com.google.gson.annotations.SerializedName

data class CarData (
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("carNumber") val carNumber: String? = null
)