package com.example.trucklist.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trucklist.R
import com.example.trucklist.ViewModelFactory
import com.example.trucklist.model.Vehicle
import com.example.trucklist.view.adapter.VehicleAdapter
import com.example.trucklist.view_model.VehicleViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class VehicleActivity : AppCompatActivity() {
    lateinit var adapter: VehicleAdapter

    @Inject
    lateinit var vehiclesVMFactory: ViewModelFactory<VehicleViewModel>

    private val vehiclesViewModel by lazy {
        ViewModelProviders.of(this, vehiclesVMFactory)[VehicleViewModel::class.java]
    }

    private val vehiclesObserver = Observer<List<Vehicle>>(::vehiclesFetched)


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vehiclesViewModel.data.observe(this, vehiclesObserver)
        vehiclesViewModel.fethVehicle()
    }


    private fun vehiclesFetched(list: List<Vehicle>?) {
        list?.forEach {
            Log.i("Helder", it.plate)
        }
        list?.let { updateAdapter(it) }
    }

    private fun updateAdapter(list: List<Vehicle>) {
        rc_trucks.layoutManager = LinearLayoutManager(this)
        rc_trucks.setHasFixedSize(true)
        adapter = VehicleAdapter({ vehicle: String -> partItemClicked(vehicle) } )
        adapter.update(list)
        rc_trucks.adapter = adapter

    }

    private fun partItemClicked(vehicle: String) {
        Toast.makeText(this, vehicle, Toast.LENGTH_SHORT).show()
    }
}
