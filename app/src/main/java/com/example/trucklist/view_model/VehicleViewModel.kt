package com.example.trucklist.view_model

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trucklist.SchedulerProvider
import com.example.trucklist.api.ApiClient
import com.example.trucklist.model.Tires
import com.example.trucklist.model.Vehicle
import com.example.trucklist.repository.VehicleRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class VehicleViewModel @Inject constructor(val api: ApiClient, private val schedulers: SchedulerProvider) : ViewModel() {
    private val _data = MutableLiveData<List<Vehicle>>()
    val data: LiveData<List<Vehicle>> = _data

    fun fethVehicle(context: Context) {
        val vehicles: ArrayList<Vehicle> = arrayListOf()
        vehicles.add(Vehicle("EE-001", "1.0"))
        vehicles.add(Vehicle("EE-002", "1.1"))
        var repository = VehicleRepository(context)

        vehicles.forEach { repository.addVehicle(it) }
        _data.value = repository.getItems()
    }
}