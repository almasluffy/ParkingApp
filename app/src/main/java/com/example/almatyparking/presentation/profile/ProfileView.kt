package com.example.almatyparking.presentation.profile

interface ProfileView {

    fun displayPhoneNumber(cost: String)

    fun displayBalance(fromDate: Int)

    fun showErrorMessage()

}