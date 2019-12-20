package com.example.trucklist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.trucklist.R
import com.example.trucklist.ViewModelFactory
import com.example.trucklist.model.Vehicle
import com.example.trucklist.view_model.VehicleViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class VehicleActivity : AppCompatActivity() {
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
    }
}
