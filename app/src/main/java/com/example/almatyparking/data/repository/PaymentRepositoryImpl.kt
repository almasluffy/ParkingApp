package com.example.almatyparking.data.repository

import com.example.almatyparking.data.models.PaymentData
import com.example.almatyparking.data.source.local.AlmatyParkingLocalDataSource
import com.example.almatyparking.data.source.remote.AlmatyParkingRemoteDataSource
import com.example.almatyparking.domain.repository.PaymentRepository
import com.example.almatyparking.presentation.payment.Handler


class PaymentRepositoryImpl (
    private val almatyParkingRemoteDataSource: AlmatyParkingRemoteDataSource,
    private val almatyParkingLocalDataSource: AlmatyParkingLocalDataSource
): PaymentRepository {

    companion object {
        private var INSTANCE: PaymentRepositoryImpl? = null

        fun getInstance(almatyParkingRemoteDataSource: AlmatyParkingRemoteDataSource,
                        almatyParkingLocalDataSource: AlmatyParkingLocalDataSource
        ): PaymentRepositoryImpl{
            if(INSTANCE==null){
                INSTANCE = PaymentRepositoryImpl(almatyParkingRemoteDataSource, almatyParkingLocalDataSource)
            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }


    override fun getPaymentsToPay(userID: Int, handler: Handler<List<PaymentData>>) {
        almatyParkingLocalDataSource.getPaymentsToPay(userID, object : Handler<List<PaymentData>>{
            override fun handle(result: List<PaymentData>) {
//                var list: List<PaymentData> = result.filter { s -> s.isPaid == 0 }
                handler.handle(result)
            }

            override fun error() {
                handler.error()
            }
        })
    }


    override fun getPayment(paymentID: Int, handler: Handler<PaymentData>) {
        almatyParkingLocalDataSource.getPayment(paymentID, object : Handler<PaymentData>{
            override fun handle(payment: PaymentData) {
                handler.handle(payment)
            }

            override fun error() {
                handler.error()
            }
        })
    }

    override fun updatePayment(paymentID: Int, balance: Int) {
        almatyParkingLocalDataSource.updatePayment(paymentID, balance)
    }

    override fun getAllPaymentList(userID: Int, handler: Handler<List<PaymentData>>) {
        almatyParkingRemoteDataSource.getAllPayments(userID, object : Handler<List<PaymentData>>{
            override fun handle(result: List<PaymentData>) {
                handler.handle(result)
            }

            override fun error() {
                handler.error()
            }
        })
    }
}