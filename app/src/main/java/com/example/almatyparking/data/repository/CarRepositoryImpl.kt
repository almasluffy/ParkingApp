package com.example.almatyparking.data.repository

import com.example.almatyparking.data.models.CarData
import com.example.almatyparking.data.models.PaymentData
import com.example.almatyparking.data.source.local.AlmatyParkingLocalDataSource
import com.example.almatyparking.data.source.remote.AlmatyParkingRemoteDataSource
import com.example.almatyparking.domain.models.Car
import com.example.almatyparking.domain.repository.CarRepository
import com.example.almatyparking.presentation.payment.Handler

class CarRepositoryImpl(private val almatyParkingLocalDataSource: AlmatyParkingLocalDataSource): CarRepository{


    companion object {
        private var INSTANCE: CarRepositoryImpl? = null

        fun getInstance( almatyParkingLocalDataSource: AlmatyParkingLocalDataSource
        ): CarRepositoryImpl{
            if(INSTANCE==null){
                INSTANCE = CarRepositoryImpl(almatyParkingLocalDataSource)
            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    override fun getCars(userID: Int, handler: Handler<List<CarData>>) {
        almatyParkingLocalDataSource.getCars(userID, object : Handler<List<CarData>> {

            override fun handle(result: List<CarData>) {
                handler.handle(result)
            }

            override fun error() {
                handler.error()
            }
        })
    }

    override fun getCar(carID: Int, handler: Handler<CarData>) {

        almatyParkingLocalDataSource.getCar(carID, object : Handler<CarData>{
            override fun handle(car: CarData) {
                handler.handle(car)
            }

            override fun error() {
                handler.error()
            }
        })
    }

    override fun insertCar(name: String, carNumber: String) {
        almatyParkingLocalDataSource.insertCar(name, carNumber)
    }

}