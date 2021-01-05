package com.example.almatyparking.domain.repository

import com.example.almatyparking.data.models.ParkingData
import com.example.almatyparking.presentation.payment.Handler


interface ParkingRepository {

    fun getParkingList(userID: Int, handler: Handler<List<ParkingData>>)

}