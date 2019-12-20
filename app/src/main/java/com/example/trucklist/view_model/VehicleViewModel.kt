package com.example.trucklist.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trucklist.SchedulerProvider
import com.example.trucklist.api.ApiClient
import com.example.trucklist.model.Tires
import com.example.trucklist.model.Vehicle
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class VehicleViewModel @Inject constructor(val api: ApiClient, private val schedulers: SchedulerProvider) : ViewModel() {
    private val _data = MutableLiveData<List<Vehicle>>()
    val data: LiveData<List<Vehicle>> = _data


    fun fethVehicle() {
       val vehicles: ArrayList<Vehicle> = arrayListOf()
       val tires: ArrayList<Tires> = arrayListOf()
//        val dot: String,
//        val fireNumber: Double,
//        val pressure: Double,
//        val plateVehicle: String
        tires.add(Tires("", 0.1,5.5,"EE-001"))
        vehicles.add(Vehicle("EE-001", 1.0, tires))
        tires.add(Tires("", 2.1,5.5,"EE-002"))
        vehicles.add(Vehicle("EE-002", 1.0, tires))


        _data.value = vehicles



    }
}