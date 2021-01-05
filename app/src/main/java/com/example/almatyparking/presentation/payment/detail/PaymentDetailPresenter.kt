package com.example.almatyparking.presentation.payment.detail

import com.example.almatyparking.data.models.PaymentData
import com.example.almatyparking.domain.repository.PaymentRepository
import com.example.almatyparking.presentation.payment.Handler
import java.lang.ref.WeakReference


class PaymentDetailPresenter(private val repository: PaymentRepository,
                             private val paymentID: Int): Handler<PaymentData> {


    private lateinit var view: WeakReference<PaymentDetailView>


    fun setView(detailMovieView: PaymentDetailView) {
        view = WeakReference(detailMovieView)
    }

    fun viewReady() {
        repository.getPayment(paymentID, this)
    }

    override fun handle(result: PaymentData) {
        view.get()?.let { paymentDetailView ->
            paymentDetailView.displayCost(result.cost!!)
            paymentDetailView.displayFromDate(result.fromDate!!)
            paymentDetailView.displayToDate(result.toDate!!)
            paymentDetailView.displayBalance()
        }
    }

    fun updatePayment(paymentID: Int, balance: Int){
        repository.updatePayment(paymentID, balance)
    }

    override fun error() {
        view.get()?.showErrorMessage()
    }

}