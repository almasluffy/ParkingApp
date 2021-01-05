package com.example.almatyparking.presentation.car.car_create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.almatyparking.R
import com.example.almatyparking.data.models.CarData
import com.example.almatyparking.presentation.di.Injection
import com.example.almatyparking.presentation.payment.detail.PaymentDetailPresenter
import kotlinx.android.synthetic.main.fragment_car_create.*

class CarCreateFragment : Fragment(), CarCreateView {

    private lateinit var navController: NavController
    private lateinit var presenter: CarCreatePresenter

    private lateinit var carName: EditText
    private lateinit var carNumber: EditText
    private lateinit var createBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_car_create, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)

        setUpPresenter()

        createBtn.setOnClickListener(View.OnClickListener {
            val new_name  = carName.text.toString()
            val new_number = carNumber.text.toString()
            presenter.createCar(new_name, new_number)

            val bundle = Bundle()
            navController.navigate(
                R.id.action_CarCreate_to_CarList,
                bundle
            )
        })
    }

    fun bindViews(view: View)  = with(view){
        navController = Navigation.findNavController(this)
        carName = view.findViewById(R.id.editTextCarName)
        carNumber = view.findViewById(R.id.editTextCarNumber)
        createBtn = view.findViewById(R.id.buttonCreate)
    }

    fun setUpPresenter(){
        presenter = CarCreatePresenter(Injection.provideCarRepository(activity?.applicationContext))
        presenter.setView(this)
    }


    override fun showErrorMessage() {

    }

}