package com.example.almatyparking.presentation.di


import android.content.Context
import com.example.almatyparking.data.repository.CarRepositoryImpl
import com.example.almatyparking.data.repository.PaymentRepositoryImpl
import com.example.almatyparking.data.repository.UserRepositoryImpl
import com.example.almatyparking.data.source.local.AlmatyParkingDatabaseHelper
import com.example.almatyparking.data.source.local.AlmatyParkingLocalDataSource
import com.example.almatyparking.data.source.local.AlmatyParkingLocalDataSourceImpl
import com.example.almatyparking.data.source.remote.AlmatyParkingRemoteDataSource
import com.example.almatyparking.data.source.remote.AlmatyParkingRemoteDataSourceImpl
import com.example.almatyparking.data.source.remote.ApiClient
import com.example.almatyparking.domain.repository.CarRepository
import com.example.almatyparking.domain.repository.PaymentRepository
import com.example.almatyparking.domain.repository.UserRepository

class Injection {
    companion object{

        fun provideRepository(context: Context?): PaymentRepository{
            return PaymentRepositoryImpl.getInstance(getAlmatyParkingRemoteDataSource(), getAlmatyParkingLocalDataSource(context))
        }

        fun provideCarRepository(context: Context?): CarRepository{
            return CarRepositoryImpl.getInstance(getAlmatyParkingLocalDataSource(context))
        }

        fun provideUserRepository(context: Context?): UserRepository{
            return UserRepositoryImpl.getInstance(getAlmatyParkingLocalDataSource(context))
        }

        private fun getAlmatyParkingRemoteDataSource(): AlmatyParkingRemoteDataSource {
            return AlmatyParkingRemoteDataSourceImpl.getInstance(ApiClient.INSTANCE)
        }

        private fun getAlmatyParkingLocalDataSource(context: Context?): AlmatyParkingLocalDataSource {
            return AlmatyParkingLocalDataSourceImpl.getInstance(AlmatyParkingDatabaseHelper.getInstance(context))
        }
    }
}