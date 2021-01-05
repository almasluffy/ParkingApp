package com.example.almatyparking.presentation.car

import com.example.almatyparking.data.models.CarData
import com.example.almatyparking.data.models.PaymentData
import com.example.almatyparking.domain.repository.CarRepository
import com.example.almatyparking.presentation.payment.Handler
import com.example.almatyparking.presentation.payment.listToPay.PaymentCellView
import com.example.almatyparking.presentation.payment.listToPay.PaymentPayView
import java.lang.ref.WeakReference


class CarListPresenter(private val repository: CarRepository, private val userID: Int): Handler<List<CarData>> {

    private lateinit var view: WeakReference<CarListView>

    private var carList = emptyList<CarData>()

    fun setView(carListView: CarListView) {
        view = WeakReference(carListView)
    }

    fun viewReady() {
        invokeGetCarList()
    }

    fun refresh() {
        invokeGetCarList()
    }

    fun invokeGetCarList(){
        repository.getCars(userID,this)
    }

    fun onItemClick(position: Int) {
        val car = getCar(position)
        //view.get()?.navigateToDetailFragment(car)
    }

    override fun handle(result: List<CarData>) {
        savePaymentList(result)
        view.get()?.let {
            it.cancelRefreshDialog()
            it.refreshList()
        }
    }

    override fun error() {
        view.get()?.let {
            it.showErrorMessage()
        }
    }

    fun savePaymentList(carList: List<CarData>){
        this.carList = carList
    }

    fun getItemsCount(): Int{
        return carList.size
    }

    fun configureCell(carCellView: CarCellView, position: Int) {
        val car = getCar(position)
        carCellView.bind(car)
    }

    private fun getCar(position: Int): CarData {
        return carList[position]
    }

    fun moviesListIsEmpty(): Boolean {
        return carList.isEmpty()
    }


}