package com.example.almatyparking.presentation.car

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.almatyparking.R
import com.example.almatyparking.data.models.CarData


class CarListAdapter(private val presenter: CarListPresenter):
    RecyclerView.Adapter<CarListAdapter.CarListHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarListAdapter.CarListHolder {
        if (parent is RecyclerView) {
            val view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.for_car, parent, false)
            return CarListHolder(view)
        } else {
            throw RuntimeException("Not bound to RecyclerView")
        }
    }

    override fun onBindViewHolder(holder: CarListAdapter.CarListHolder, position: Int) {
        presenter.configureCell(holder, position)
    }

    override fun getItemCount(): Int {
        return presenter.getItemsCount()
    }

    fun refreshData(){
        notifyDataSetChanged()
    }

    inner class CarListHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener,
        CarCellView {

        private val carName = itemView.findViewById(R.id.tvName) as TextView
        private val carNumber = itemView.findViewById(R.id.tvNumber) as TextView

        init {
            itemView.setOnClickListener(this)
        }

        override fun bind(car: CarData) {
            carName.text = car.name
            carNumber.text = car.carNumber
        }

        override fun onClick(v: View?) {
            presenter.onItemClick(adapterPosition)
        }

    }
}