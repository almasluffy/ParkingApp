package com.example.almatyparking.presentation.payment.detail

interface PaymentDetailView {

    fun displayCost(cost: Int)

    fun displayFromDate(fromDate: String)

    fun displayToDate(toDate: String)

    fun displayBalance()

    fun showErrorMessage()

}