package com.example.almatyparking.presentation.profile

import com.example.almatyparking.data.models.UserData
import com.example.almatyparking.domain.repository.PaymentRepository
import com.example.almatyparking.domain.repository.UserRepository
import com.example.almatyparking.presentation.payment.Handler
import java.lang.ref.WeakReference

class ProfilePresenter(private val repository: UserRepository,
                       private val userID: Int) : Handler<UserData> {

    private lateinit var view: WeakReference<ProfileView>

    fun setView(profileView: ProfileView) {
        view = WeakReference(profileView)
    }

    fun viewReady() {
       updateData()
    }

    fun updateData(){
        repository.getUser(userID, this)
    }

    override fun handle(result: UserData) {
        view.get()?.let { paymentDetailView ->
            paymentDetailView.displayPhoneNumber(result.phone_number!!)
            paymentDetailView.displayBalance(result.balance!!)
        }
    }

    override fun error() {
        view.get()?.showErrorMessage()
    }

    fun refresh() {
       updateData()
    }

}