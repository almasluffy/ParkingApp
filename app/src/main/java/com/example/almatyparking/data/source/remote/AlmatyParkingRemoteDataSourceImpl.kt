package com.example.almatyparking.data.source.remote

import com.example.almatyparking.data.models.ParkingData
import com.example.almatyparking.data.models.PaymentData
import com.example.almatyparking.presentation.payment.Handler
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class AlmatyParkingRemoteDataSourceImpl(private val almatyParkingApi: AlmatyParkingApi):
    AlmatyParkingRemoteDataSource {

    companion object {
        private var INSTANCE: AlmatyParkingRemoteDataSource? = null

        fun getInstance(almatyParkingApi: AlmatyParkingApi): AlmatyParkingRemoteDataSource
        {
            if(INSTANCE ==null){
                INSTANCE =
                    AlmatyParkingRemoteDataSourceImpl(
                        almatyParkingApi
                    )
            }
            return INSTANCE!!
        }
    }


    override fun getAllPayments(userID: Int, handler: Handler<List<PaymentData>>) {
        almatyParkingApi.getPaymentsToPay().enqueue(object : Callback<List<PaymentData>>{

            override fun onResponse(
                call: Call<List<PaymentData>>,
                response: Response<List<PaymentData>>
            ) {
                val list = response.body()
                if(list!=null){
                    handler.handle(list)
                }
                else{
                    handler.error()
                }
            }

            override fun onFailure(call: Call<List<PaymentData>>, t: Throwable) {
                handler.error()
            }

        })
    }

    override fun getUserParkings(userID: Int, handler: Handler<List<ParkingData>>) {
        almatyParkingApi.getParkings().enqueue(object : Callback<List<ParkingData>>{

            override fun onResponse(
                call: Call<List<ParkingData>>,
                response: Response<List<ParkingData>>
            ) {
                val list = response.body()
                if(list!=null){
                    handler.handle(list)
                }
                else{
                    handler.error()
                }
            }

            override fun onFailure(call: Call<List<ParkingData>>, t: Throwable) {
                handler.error()
            }

        })
    }

}