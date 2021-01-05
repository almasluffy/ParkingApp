package com.example.almatyparking.data.source.remote

import com.example.almatyparking.utils.AppConstants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object {
        val INSTANCE: AlmatyParkingApi by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
            retrofit.create(AlmatyParkingApi::class.java)
        }
    }
}