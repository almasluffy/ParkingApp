package com.example.almatyparking.presentation.car.car_create

import com.example.almatyparking.data.models.CarData
import com.example.almatyparking.data.models.PaymentData
import com.example.almatyparking.domain.repository.CarRepository
import com.example.almatyparking.domain.repository.PaymentRepository
import com.example.almatyparking.presentation.payment.Handler
import com.example.almatyparking.presentation.payment.detail.PaymentDetailView
import java.lang.ref.WeakReference

class CarCreatePresenter(private val repository: CarRepository): Handler<CarData> {

    private lateinit var view: WeakReference<CarCreateView>


    fun setView(carView: CarCreateView) {
        view = WeakReference(carView)
    }


    override fun handle(result: CarData) {
    }


    override fun error() {
        view.get()?.showErrorMessage()
    }

    fun createCar(new_name: String, new_number: String){
        repository.insertCar(new_name, new_number)
    }


}