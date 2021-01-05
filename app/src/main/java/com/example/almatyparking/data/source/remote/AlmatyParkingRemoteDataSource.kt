package com.example.almatyparking.data.source.remote

import com.example.almatyparking.data.models.ParkingData
import com.example.almatyparking.data.models.PaymentData
import com.example.almatyparking.presentation.payment.Handler


interface AlmatyParkingRemoteDataSource {

    fun getAllPayments(userID: Int, handler: Handler<List<PaymentData>>)

    fun getUserParkings(userID: Int, handler: Handler<List<ParkingData>>)
}