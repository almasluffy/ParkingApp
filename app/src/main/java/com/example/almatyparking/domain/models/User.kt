package com.example.almatyparking.domain.models

data class User(
    val id: Long,
    val phone_number: String,
    val balance: Double,
    val cars: List<Car>,
    val payments: List<Payment>,
    val parkingList: List<Parking>
)