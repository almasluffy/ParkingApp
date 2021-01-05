package com.example.almatyparking.domain.models


data class Car(
    val id: Long,
    val name: String,
    val number: String,
    val verificationNumber: Int,
    val userID: Long
)