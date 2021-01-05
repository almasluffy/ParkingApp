package com.example.almatyparking.presentation.payment.listToPay

import com.example.almatyparking.data.models.PaymentData
import com.example.almatyparking.domain.repository.PaymentRepository
import com.example.almatyparking.presentation.payment.Handler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.ref.WeakReference

class PaymentPayPresenter(private val repository: PaymentRepository, private val userID: Int): Handler<List<PaymentData>> {

    private lateinit var view: WeakReference<PaymentPayView>

    private var paymentList = emptyList<PaymentData>()


    fun setView(paymentPayView: PaymentPayView) {
        view = WeakReference(paymentPayView)
    }

    fun viewReady() {
        invokeGetPaymentPayList()
    }

    fun refresh() {
        invokeGetPaymentPayList()
    }

    fun invokeGetPaymentPayList(){
        repository.getPaymentsToPay(userID,this)
    }

    fun onItemClick(position: Int) {
        val payment = getMovie(position)
        view.get()?.navigateToDetailFragment(payment)
    }

    override fun handle(result: List<PaymentData>) {
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

    fun savePaymentList(paymentList: List<PaymentData>){
        this.paymentList = paymentList
    }

    fun getItemsCount(): Int{
        return paymentList.size
    }

    fun configureCell(paymentCellView: PaymentCellView, position: Int) {
        val movie = getMovie(position)
        paymentCellView.bind(movie)
    }

    private fun getMovie(position: Int): PaymentData {
        return paymentList[position]
    }

    fun moviesListIsEmpty(): Boolean {
        return paymentList.isEmpty()
    }
}