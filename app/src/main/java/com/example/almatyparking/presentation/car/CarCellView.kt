package com.example.almatyparking.presentation.car

import com.example.almatyparking.data.models.CarData
import com.example.almatyparking.data.models.PaymentData

interface CarCellView {

    fun bind(carData: CarData)
}