package com.example.almatyparking.domain.repository

import com.example.almatyparking.data.models.CarData
import com.example.almatyparking.data.models.PaymentData
import com.example.almatyparking.presentation.payment.Handler

interface CarRepository {

    fun getCars(userID: Int, handler: Handler<List<CarData>>)

    fun getCar(carID: Int, handler: Handler<CarData>)

    fun insertCar(name: String, carNumber: String)

}