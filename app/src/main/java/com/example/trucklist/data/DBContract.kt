package com.example.trucklist.data

import android.provider.BaseColumns


//class Vehicle(
//    val plate: String,
//    val mileage: Double,
//    val tires: ArrayList<Tires>)

object DBContract {
    class VehicleEntry : BaseColumns {
        companion object {
            val TABLE_NAME = "vehicle"
            val COLUMN_PLATE = "plate"
            val COLUMN_MILEAGE = "mileage"
        }
    }

//
//    val dot: String,
//    val fireNumber: Double,
//    val pressure: Double,
//    val plateVehicle: String

    class TiresEntry : BaseColumns {
        companion object {
            val TABLE_NAME = "tires"
            val COLUMN_ID = "id"
            val COLUMN_DOT = "dot"
            val COLUMN_PLATE = "plate"
            val COLUMN_FIRE_NUMBER = "fireNumber"
            val COLUMN_PRESSURE = "pressure"
        }
    }
}
