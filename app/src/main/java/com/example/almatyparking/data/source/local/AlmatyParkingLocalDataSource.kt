package com.example.almatyparking.data.source.local

import com.example.almatyparking.data.models.CarData
import com.example.almatyparking.data.models.PaymentData
import com.example.almatyparking.data.models.UserData
import com.example.almatyparking.presentation.payment.Handler

interface AlmatyParkingLocalDataSource {

    fun getPaymentsToPay(userID: Int, handler: Handler<List<PaymentData>>)

    fun getPayment(paymentID: Int, handler: Handler<PaymentData>)

    fun deleteAllPayments()

    fun updatePayment(paymentID: Int, balance: Int)

    fun getCars(userID: Int, handler: Handler<List<CarData>>)

    fun getCar(carID: Int, handler: Handler<CarData>)

    fun insertCar(name: String, carNumber: String)

    fun getUser(userID: Int, handler: Handler<UserData>)
}