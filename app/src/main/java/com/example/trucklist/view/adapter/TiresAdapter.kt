package com.example.trucklist.view.adapter

import android.R.attr.*
import android.graphics.Matrix
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.trucklist.R
import com.example.trucklist.model.Tires
import kotlinx.android.synthetic.main.item_plate.view.*
import kotlinx.android.synthetic.main.item_plate.view.tv_title
import kotlinx.android.synthetic.main.item_tire.view.*


class TiresAdapter(val clickListener: ((Tires) -> Unit)?) : RecyclerView.Adapter<TiresAdapter.ViewHolder>() {
    var tires: List<Tires>

    init {
        tires = listOf()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(tire: Tires, clickListener: ((Tires) -> Unit)?) {
            if(tire.id == "01" || tire.id == "02" || tire.id == "10" || tire.id == "11") {
                itemView.animate().rotation(45F).setDuration(3)
            }

            itemView.tv_title.text = tire.id
            itemView.setOnClickListener { clickListener?.let { it1 -> it1(tire) } }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tire, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = this.tires.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        (holder as ViewHolder).bind(this.tires[position], clickListener)
    }

    fun updateList() {
        notifyDataSetChanged()
    }

    fun update(tires: List<Tires>) {
        this.tires = emptyList()
        this.tires = tires
        updateList()
    }
}
