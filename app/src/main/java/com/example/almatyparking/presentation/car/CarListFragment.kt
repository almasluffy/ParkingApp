package com.example.almatyparking.presentation.car

import android.app.ActionBar
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
import com.example.almatyparking.presentation.di.Injection
import com.example.almatyparking.presentation.payment.listToPay.PaymentPayAdapter
import com.example.almatyparking.presentation.payment.listToPay.PaymentPayPresenter
import com.example.almatyparking.utils.AppPreferences
import kotlinx.android.synthetic.main.fragment_car_list.*
import kotlinx.android.synthetic.main.fragment_payment_pay.view.*


class CarListFragment : Fragment(), CarListView{

    private lateinit var presenter: CarListPresenter

    private lateinit var adapter: CarListAdapter

    private lateinit var rvCarList: RecyclerView

    private lateinit var srlCarList: SwipeRefreshLayout
    private lateinit var navController: NavController

    private lateinit var fabBtn: ActionBar

    private var userID: Int? = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_car_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userID = AppPreferences.getUserId(activity?.applicationContext!!)
        bindViews(view)
        setUpPresenter()
        setUpListView()
        setUpRefreshView()
        informPresenterViewIsReady()

        fabCar.setOnClickListener {
            val bundle = Bundle()
            navController.navigate(
                R.id.action_CarList_to_CarCreate,
                bundle
            )
        }
    }

    fun bindViews(view: View)  = with(view){
        navController = Navigation.findNavController(this)
        rvCarList = findViewById(R.id.rvCarList)
        srlCarList = findViewById(R.id.srlCarList)
        val layoutManager = LinearLayoutManager(context)
        rvCarList.layoutManager = layoutManager
    }

    fun setUpPresenter(){
        presenter = CarListPresenter(Injection.provideCarRepository(activity?.applicationContext), userID!!)
        presenter.setView(this)
    }

    fun setUpListView(){
        adapter = CarListAdapter(presenter)
        rvCarList!!.adapter = adapter
    }

    fun setUpRefreshView(){
        srlCarList.setOnRefreshListener { presenter.refresh() }
    }

    fun informPresenterViewIsReady(){
        presenter.viewReady()
    }


    override fun refreshList() {
        adapter.refreshData()
    }
    override fun showErrorMessage() {

    }

    override fun cancelRefreshDialog() {
        srlCarList.isRefreshing = false
    }

}