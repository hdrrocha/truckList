package com.example.trucklist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.trucklist.R
import dagger.android.AndroidInjection

class TruckDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_truck_details)
        val data: Bundle? = intent.extras.getSerializable("tires")
        var category  = data?.getE("tires")
    }
}
