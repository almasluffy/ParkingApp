package com.example.almatyparking.data.repository

import com.example.almatyparking.data.models.ParkingData
import com.example.almatyparking.data.models.PaymentData
import com.example.almatyparking.data.source.local.AlmatyParkingLocalDataSource
import com.example.almatyparking.data.source.remote.AlmatyParkingRemoteDataSource
import com.example.almatyparking.domain.repository.ParkingRepository
import com.example.almatyparking.presentation.payment.Handler

class ParkingRepositoryImpl(private val almatyParkingRemoteDataSource: AlmatyParkingRemoteDataSource): ParkingRepository {

    companion object {
        private var INSTANCE: ParkingRepositoryImpl? = null

        fun getInstance(almatyParkingRemoteDataSource: AlmatyParkingRemoteDataSource
        ): ParkingRepositoryImpl{
            if(INSTANCE==null){
                INSTANCE = ParkingRepositoryImpl(almatyParkingRemoteDataSource)
            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
    override fun getParkingList(userID: Int, handler: Handler<List<ParkingData>>) {

        almatyParkingRemoteDataSource.getUserParkings(userID, object : Handler<List<ParkingData>>{
            override fun handle(result: List<ParkingData>) {
                handler.handle(result)
            }

            override fun error() {
                handler.error()
            }
        })
    }
}