package com.example.trucklist.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.trucklist.data.DBHelper
import com.example.trucklist.model.Tires
import com.example.trucklist.model.Vehicle
import io.reactivex.disposables.CompositeDisposable


class VehicleRepository(context: Context) {
    var mDBHelper : DBHelper
    var listVehicles: MutableList<Vehicle> = mutableListOf()

    private val _data = MutableLiveData<List<Vehicle>>()
    val data: LiveData<List<Vehicle>> = _data
    private val disposable = CompositeDisposable()

    init {
        mDBHelper = DBHelper(context.applicationContext)
    }

    fun getItems(): ArrayList<Vehicle> {
        return mDBHelper.readAllVehicle()
    }

    fun addVehicle(vehicle: Vehicle){
        mDBHelper.insertVehicle(vehicle)
    }

    fun getTiresToPlate(vehicle: Vehicle): ArrayList<Tires> {
        return mDBHelper.readTiresToPlate(vehicle.plate)
    }

    fun updateVehicleItem(vehicle: Vehicle): Boolean {
        //TODO
      return false
    }

    fun countVehicles(): Int {
        return mDBHelper.readAllVehicle().size
    }
}