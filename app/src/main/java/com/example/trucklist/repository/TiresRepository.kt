package com.example.trucklist.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.trucklist.data.DBHelper
import com.example.trucklist.model.Tires
import com.example.trucklist.model.Vehicle
import io.reactivex.disposables.CompositeDisposable


class TiresRepository(context: Context) {
    var mDBHelper : DBHelper
    var listTires: MutableList<Tires> = mutableListOf()

    private val _data = MutableLiveData<List<Tires>>()
    val data: LiveData<List<Tires>> = _data
    private val disposable = CompositeDisposable()

    init {
        mDBHelper = DBHelper(context.applicationContext)
    }

    fun addTires(tires: Tires){
        if(!mDBHelper.readAllTires().contains(tires)) {
            mDBHelper.insertTires(tires)
        }

    }

    fun getTiresToPlate(plate: String): ArrayList<Tires> {
        return mDBHelper.readGames(plate)
    }

    fun updateVehicleItem(vehicle: Vehicle): Boolean {
        //TODO
      return false
    }

    fun countTires(vehicle: Vehicle): Int {
        return mDBHelper.readTiresToPlate(vehicle.plate).size
    }
}