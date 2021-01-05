package com.example.almatyparking.domain.models

import java.time.LocalDateTime
import java.util.*

data class Payment(
    val id: Long,
    val cost: Int,
    val from: Date,
    val to: Date,
    val isPaid: Boolean,
    val carID: Long,
    val parkingID: Long,
    val userID: Long
)