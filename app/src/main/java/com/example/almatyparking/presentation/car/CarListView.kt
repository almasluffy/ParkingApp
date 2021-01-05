package com.example.almatyparking.presentation.car

import com.example.almatyparking.data.models.PaymentData

interface CarListView {

    fun refreshList()

    fun showErrorMessage()

    fun cancelRefreshDialog()

}