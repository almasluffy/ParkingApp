package com.example.almatyparking.presentation.payment.listToPay

import com.example.almatyparking.data.models.PaymentData

interface PaymentCellView {

    fun bind(paymentData: PaymentData)
}