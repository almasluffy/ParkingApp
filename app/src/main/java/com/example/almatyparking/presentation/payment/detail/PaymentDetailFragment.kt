package com.example.almatyparking.presentation.payment.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.almatyparking.R
import com.example.almatyparking.presentation.di.Injection
import com.example.almatyparking.utils.AppPreferences


class PaymentDetailFragment : Fragment(), PaymentDetailView {


    private lateinit var navController: NavController
    private lateinit var presenter: PaymentDetailPresenter

    private lateinit var cost: TextView
    private lateinit var fromDate: TextView
    private lateinit var toDate: TextView
    private lateinit var balanceText: TextView
    private lateinit var payBtn: Button


    private var paymentID: Int? = 0
    private var curr_cost: Int? = 0
    private var curr_balance: Int? = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        curr_balance = AppPreferences.getBalance(activity?.applicationContext!!)
        bindViews(view)

        setUpPresenter()

        informPresenterViewIsReady()


        payBtn.setOnClickListener(View.OnClickListener {
            presenter.updatePayment(paymentID!!, curr_balance!! - curr_cost!!)
            AppPreferences.setBalance(activity?.applicationContext!!, curr_balance!! - curr_cost!!)
            val bundle = Bundle()
            navController.navigate(
                R.id.action_PaymentDetailFragment_to_PaymentPay,
                bundle
            )
        })
    }

    fun bindViews(view: View)  = with(view){
        navController = Navigation.findNavController(this)
        cost = view.findViewById(R.id.costToPay)
        fromDate = view.findViewById(R.id.fromTextDate)
        toDate = view.findViewById(R.id.toDateText)
        payBtn = view.findViewById(R.id.buttonPay)
        balanceText = view.findViewById(R.id.balanceText)
        paymentID = arguments?.getInt("payment_id")
    }


    fun setUpPresenter(){
        presenter = PaymentDetailPresenter(Injection.provideRepository(activity?.applicationContext), paymentID!!)
        presenter.setView(this)
    }

    fun informPresenterViewIsReady(){
        presenter.viewReady()
    }


    override fun displayCost(cost: Int) {
        curr_cost = cost
        this.cost.text = "К оплате: " + cost.toString() + " тг"
    }

    override fun displayFromDate(fromDate: String) {
        this.fromDate.text = "Начало парковки: " + fromDate
    }

    override fun displayToDate(toDate: String) {
        this.toDate.text =  "Конец парковки: " + toDate
    }

    override fun displayBalance() {
        this.balanceText.text = "Ваш текущий баланс: " + curr_balance!!.toString() + "тг"
    }

    override fun showErrorMessage() {

    }

}