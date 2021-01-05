package com.example.almatyparking.presentation.payment.listToPay

import com.example.almatyparking.data.models.PaymentData

interface PaymentPayView {

    fun refreshList()

    fun showErrorMessage()

    fun cancelRefreshDialog()

    fun navigateToDetailFragment(paymentData: PaymentData)

}