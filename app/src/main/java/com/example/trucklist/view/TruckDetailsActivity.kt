package com.example.trucklist.view

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.trucklist.R
import com.example.trucklist.ViewModelFactory
import com.example.trucklist.model.Tires
import com.example.trucklist.view.adapter.TiresAdapter
import com.example.trucklist.view.dialog.TireDialog
import com.example.trucklist.view_model.TiresViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_truck_details.*
import kotlinx.android.synthetic.main.tire_dialog.*
import javax.inject.Inject


class TruckDetailsActivity : AppCompatActivity() {
    var layoutManager = GridLayoutManager(this, 2)
    private lateinit var adapter: TiresAdapter
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
        var mPlate = plate.toString()
        tiresViewModel.data.observe(this, tiresObserver)
        tiresViewModel.fethTiresToPlate(mPlate,this)

    }

    private fun onFetched(list: List<Tires>?) {

        list?.forEach {
            rc_tires.layoutManager = layoutManager
            rc_tires.setHasFixedSize(true)
            adapter = TiresAdapter({ tire: Tires -> partItemClicked(tire) })
            adapter.update(list)
            rc_tires.adapter = adapter

        }
    }

    private fun partItemClicked(tire: Tires) {
        var tireDialog: TireDialog = TireDialog(tire)
        tireDialog.show(supportFragmentManager, "my_fragment")
    }
}