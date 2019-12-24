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
import com.example.trucklist.repository.TiresRepository
import com.example.trucklist.repository.VehicleRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class TiresViewModel @Inject constructor(val api: ApiClient, private val schedulers: SchedulerProvider) : ViewModel() {
    private val _data = MutableLiveData<List<Tires>>()
    val data: LiveData<List<Tires>> = _data

    fun fethTiresToPlate(plate: String, context: Context) {

        val tires: ArrayList<Tires> = arrayListOf()
        tires.add(Tires("01", "1.0", "1.0", "10","EE-001"))
        tires.add(Tires("02", "1.0", "1.0", "10","EE-001"))
        tires.add(Tires("03", "1.0", "1.0", "10","EE-001"))
        tires.add(Tires("04", "1.0", "1.0", "10","EE-001"))
        tires.add(Tires("05", "1.0", "1.0", "10","EE-001"))
        tires.add(Tires("06", "1.0", "1.0", "10","EE-001"))
        tires.add(Tires("07", "1.0", "1.0", "10","EE-001"))
        tires.add(Tires("08", "1.0", "1.0", "10","EE-001"))

        tires.add(Tires("10", "1.0", "1.0", "10","EE-002"))
        tires.add(Tires("11", "1.0", "1.0", "10","EE-002"))
        tires.add(Tires("12", "1.0", "1.0", "10","EE-002"))
        tires.add(Tires("13", "1.0", "1.0", "10","EE-002"))
        tires.add(Tires("14", "1.0", "1.0", "10","EE-002"))
        tires.add(Tires("15", "1.0", "1.0", "10","EE-002"))
        tires.add(Tires("16", "1.0", "1.0", "10","EE-002"))
        tires.add(Tires("17", "1.0", "1.0", "10","EE-002"))

        var repository = TiresRepository(context)

        tires.forEach { repository.addTires(it) }
        _data.value = repository.getTiresToPlate(plate)
    }


}