package com.example.trucklist.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trucklist.R
import com.example.trucklist.model.Tires
import com.example.trucklist.model.Vehicle
import kotlinx.android.synthetic.main.item_plate.view.*


class VehicleAdapter(val clickListener: ((ArrayList<Tires>) -> Unit)?) : RecyclerView.Adapter<VehicleAdapter.ViewHolder>() {
    var vehicles: List<Vehicle>

    init {
        vehicles = listOf()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(vehicleItem: Vehicle, clickListener: ((ArrayList<Tires>) -> Unit)?) {
            itemView.tv_title.text = vehicleItem.plate
            itemView.setOnClickListener { clickListener?.let { it1 -> it1(vehicleItem.tires ) } }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_plate, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = this.vehicles.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        (holder as ViewHolder).bind(this.vehicles[position], clickListener)
    }

    fun updateList() {
        notifyDataSetChanged()
    }

    fun update(vehicles: List<Vehicle>) {
        this.vehicles = emptyList()
        this.vehicles = vehicles
        updateList()
    }
}
