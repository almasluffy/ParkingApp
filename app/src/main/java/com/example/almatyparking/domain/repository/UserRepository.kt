package com.example.almatyparking.domain.repository

import com.example.almatyparking.data.models.PaymentData
import com.example.almatyparking.data.models.UserData
import com.example.almatyparking.presentation.payment.Handler


interface UserRepository  {

    fun getUser(userID: Int,  handler: Handler<UserData>)

}