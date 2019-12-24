package com.example.trucklist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.trucklist.R
import com.example.trucklist.ViewModelFactory
import com.example.trucklist.model.Tires
import com.example.trucklist.model.Vehicle
import com.example.trucklist.view_model.TiresViewModel
import com.example.trucklist.view_model.VehicleViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class TruckDetailsActivity : AppCompatActivity() {


    @Inject
    lateinit var tiresVMFactory: ViewModelFactory<TiresViewModel>

    private val tiresViewModel by lazy {
        ViewModelProviders.of(this, tiresVMFactory)[TiresViewModel::class.java]
    }

    private val tiresObserver = Observer<List<Tires>>(::onFetched)



    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_truck_details)
        val data: Bundle? = intent.extras
        var plate = data?.get("plate_select")

        tiresViewModel.data.observe(this, tiresObserver)
        tiresViewModel.fethTiresToPlate(plate.toString(),this)

    }

    private fun onFetched(list: List<Tires>?) {

        list?.forEach { Log.i("Tiress>>>>>>>>>>>>>>", it.plateVehicle)}

    }
}
