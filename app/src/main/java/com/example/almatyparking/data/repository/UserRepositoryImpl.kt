package com.example.almatyparking.data.repository

import com.example.almatyparking.data.models.PaymentData
import com.example.almatyparking.data.models.UserData
import com.example.almatyparking.data.source.local.AlmatyParkingLocalDataSource

import com.example.almatyparking.domain.repository.UserRepository
import com.example.almatyparking.presentation.payment.Handler


class UserRepositoryImpl(private val almatyParkingLocalDataSource: AlmatyParkingLocalDataSource) : UserRepository {

    companion object {
        private var INSTANCE: UserRepositoryImpl? = null

        fun getInstance(almatyParkingLocalDataSource: AlmatyParkingLocalDataSource
        ): UserRepositoryImpl{
            if(INSTANCE==null){
                INSTANCE = UserRepositoryImpl(almatyParkingLocalDataSource)
            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    override fun getUser(userID: Int, handler: Handler<UserData>) {
        almatyParkingLocalDataSource.getUser(userID, object : Handler<UserData>{
            override fun handle(user: UserData) {
                handler.handle(user)
            }

            override fun error() {
                handler.error()
            }
        })
    }

}