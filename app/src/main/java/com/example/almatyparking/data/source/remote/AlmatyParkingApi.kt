package com.example.almatyparking.data.source.remote

import android.service.autofill.UserData
import com.example.almatyparking.data.models.*
import com.example.almatyparking.utils.AppConstants
import com.google.gson.JsonObject
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface AlmatyParkingApi {

    //profile
    @GET(AppConstants.PROFILE)
    fun getProfile(): Call<ProfileData>

    //PaymentsToPay
    @GET(AppConstants.PAYMENTS)
    fun getPaymentsToPay(): Call<List<PaymentData>>

    //Parkings

    @GET(AppConstants.PARKINGS)
    fun getParkings(): Call<List<ParkingData>>

    //ParkingPlaces
    @GET(AppConstants.PARKING_PLACES)
    fun getParkingPlaces() : Deferred<Response<List<ParkingPlaces>>>


}