package com.example.almatyparking.domain.repository

import com.example.almatyparking.data.models.PaymentData
import com.example.almatyparking.presentation.payment.Handler

interface PaymentRepository {

    fun getAllPaymentList(userID: Int, handler: Handler<List<PaymentData>>)

    fun getPaymentsToPay(userID: Int, handler: Handler<List<PaymentData>>)

    fun getPayment(paymentID: Int, handler: Handler<PaymentData>)

    fun updatePayment(paymentID: Int, balance: Int)

}