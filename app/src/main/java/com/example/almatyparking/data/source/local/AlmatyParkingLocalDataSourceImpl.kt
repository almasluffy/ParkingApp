package com.example.almatyparking.data.source.local

import com.example.almatyparking.data.models.CarData
import com.example.almatyparking.data.models.PaymentData
import com.example.almatyparking.data.models.UserData
import com.example.almatyparking.presentation.payment.Handler


class AlmatyParkingLocalDataSourceImpl private constructor(
    private val almatyParkingDatabaseHelper: AlmatyParkingDatabaseHelper): AlmatyParkingLocalDataSource
{
    companion object {
        private var INSTANCE: AlmatyParkingLocalDataSourceImpl? = null

        fun getInstance(databaseHelper: AlmatyParkingDatabaseHelper): AlmatyParkingLocalDataSourceImpl {
            if (INSTANCE == null) {
                INSTANCE = AlmatyParkingLocalDataSourceImpl(databaseHelper)
            }
            return INSTANCE!!
        }
    }

    ///Payment
    override fun getPaymentsToPay(userID: Int, handler: Handler<List<PaymentData>>) {
        val paymentList = almatyParkingDatabaseHelper.getPaymentsToPay(userID)
        if (!paymentList.isEmpty()) {
            handler.handle(paymentList)
        } else {
            handler.error()
        }
    }

    override fun getPayment(paymentID: Int, handler: Handler<PaymentData>) {
        val payment = almatyParkingDatabaseHelper.getPayment(paymentID)
        if (payment != null) {
            handler.handle(payment)
        } else {
            handler.error()
        }
    }

    override fun getUser(userID: Int, handler: Handler<UserData>) {
        val user = almatyParkingDatabaseHelper.getUser(userID)
        if (user != null) {
            handler.handle(user)
        } else {
            handler.error()
        }
    }

    override fun updatePayment(paymentID: Int, balance: Int) {
        almatyParkingDatabaseHelper.updatePayment(paymentID, balance)
    }

    override fun deleteAllPayments() {
        almatyParkingDatabaseHelper.deleteAllPayments()
    }

    override fun getCars(userID: Int, handler: Handler<List<CarData>>) {
        val carList = almatyParkingDatabaseHelper.getCars(userID)
        if (!carList.isEmpty()) {
            handler.handle(carList)
        } else {
            handler.error()
        }
    }

    override fun getCar(carID: Int, handler: Handler<CarData>) {
        val car = almatyParkingDatabaseHelper.getCar(carID)
        if (car != null) {
            handler.handle(car)
        } else {
            handler.error()
        }
    }

    override fun insertCar(name: String, carNumber: String) {
        almatyParkingDatabaseHelper.insertCar(name, carNumber)
    }


}