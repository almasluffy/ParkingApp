package com.example.almatyparking.presentation.payment.listToPay

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.almatyparking.R
import com.example.almatyparking.data.models.PaymentData
import com.example.almatyparking.presentation.di.Injection
import com.example.almatyparking.utils.AppPreferences


class PaymentPay : Fragment(), PaymentPayView{

    private lateinit var paymentPayPresenter: PaymentPayPresenter

    private lateinit var adapter: PaymentPayAdapter

    private lateinit var rvPayment: RecyclerView

    private lateinit var srlPayment: SwipeRefreshLayout
    private lateinit var navController: NavController

    private var userID: Int? = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment_pay, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userID = AppPreferences.getUserId(activity?.applicationContext!!)
        bindViews(view)
        setUpPresenter()
        setUpListView()
        setUpRefreshView()
        informPresenterViewIsReady()
    }

    fun bindViews(view: View)  = with(view){
        navController = Navigation.findNavController(this)
        rvPayment = findViewById(R.id.rvPayment)
        srlPayment = findViewById(R.id.srlPayment)
        val layoutManager = LinearLayoutManager(context)
        rvPayment.layoutManager = layoutManager
    }

    fun setUpPresenter(){
        paymentPayPresenter = PaymentPayPresenter(Injection.provideRepository(activity?.applicationContext), userID!!)
        paymentPayPresenter.setView(this)
    }


    override fun showErrorMessage() {

    }

    fun setUpListView(){
        adapter = PaymentPayAdapter(paymentPayPresenter)
        rvPayment!!.adapter = adapter
    }

    fun setUpRefreshView(){
        srlPayment.setOnRefreshListener { paymentPayPresenter.refresh() }
    }

    private fun informPresenterViewIsReady() {
        paymentPayPresenter.viewReady()
    }

    override fun refreshList() {
        adapter.refreshData()
    }

    override fun cancelRefreshDialog() {
        srlPayment.isRefreshing = false
    }

    override fun navigateToDetailFragment(paymentData: PaymentData){
        val bundle = Bundle()
        bundle.putInt("payment_id", paymentData.id!!)
        bundle.putString("parent_fragment", "list_fragment")
        navController.navigate(
            R.id.action_PaymentPay_to_PaymentDetailFragment,
            bundle
        )
    }


}