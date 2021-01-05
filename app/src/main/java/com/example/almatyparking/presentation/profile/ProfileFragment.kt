package com.example.almatyparking.presentation.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.almatyparking.R
import com.example.almatyparking.data.models.ProfileData
import com.example.almatyparking.domain.repository.UserRepository
import com.example.almatyparking.presentation.di.Injection
import com.example.almatyparking.presentation.payment.detail.PaymentDetailPresenter
import com.example.almatyparking.utils.AppPreferences
import kotlinx.android.synthetic.main.fragment_profile.view.*


class ProfileFragment : Fragment(), ProfileView {

    private lateinit var navController: NavController
    private lateinit var presenter: ProfilePresenter

    private lateinit var phone_number: TextView
    private lateinit var balance: TextView

    private var curr_balance: Int? = 0

    private var userID: Int? = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViews(view)

        userID = AppPreferences.getUserId(activity?.applicationContext!!)
        curr_balance = AppPreferences.getBalance(activity?.applicationContext!!)
        userID = 1

        setUpPresenter()

        informPresenterViewIsReady()
    }

    fun bindViews(view: View)  = with(view){
        navController = Navigation.findNavController(this)
        phone_number = view.findViewById(R.id.textPhoneNumber)
        balance = view.findViewById(R.id.textBalance)

    }

    fun setUpPresenter(){
        presenter = ProfilePresenter(Injection.provideUserRepository(activity?.applicationContext), userID!!)
        presenter.setView(this)
    }

    fun informPresenterViewIsReady(){
        presenter.viewReady()
    }

    override fun displayBalance(balance: Int) {
        this.balance.text = curr_balance.toString()
    }

    override fun displayPhoneNumber(phoneN: String) {
        this.phone_number.text = phoneN
    }


    override fun showErrorMessage() {

    }

}