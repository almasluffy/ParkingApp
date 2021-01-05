package com.example.almatyparking.presentation.payment.listToPay

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.almatyparking.R
import com.example.almatyparking.data.models.PaymentData
import org.w3c.dom.Text

class PaymentPayAdapter(private val presenter: PaymentPayPresenter):
     RecyclerView.Adapter<PaymentPayAdapter.PaymentPayHolder>()
 {
     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentPayHolder {
         if (parent is RecyclerView) {
             val view = LayoutInflater.from(parent.getContext())
                 .inflate(R.layout.for_payment_pay, parent, false)
             return PaymentPayHolder(view)
         } else {
             throw RuntimeException("Not bound to RecyclerView")
         }
     }

     override fun onBindViewHolder(holder: PaymentPayHolder, position: Int) {
         presenter.configureCell(holder, position)
     }

     override fun getItemCount(): Int {
         return presenter.getItemsCount()
     }

     fun refreshData(){
         notifyDataSetChanged()
     }


    inner class PaymentPayHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener, PaymentCellView {

        private val cost = itemView.findViewById(R.id.paymentCost) as TextView
        private val from_date = itemView.findViewById(R.id.paymentFrom) as TextView
        private val to_date = itemView.findViewById(R.id.paymentTO) as TextView

        init {
            itemView.setOnClickListener(this)
        }

        override fun bind(paymentData: PaymentData) {
            cost.setText(paymentData.cost.toString() + " тг")
            from_date.setText(paymentData.fromDate.toString())
            to_date.setText(paymentData.toDate.toString())
        }

        override fun onClick(v: View?) {
            presenter.onItemClick(adapterPosition)
        }

    }

}