package com.example.trucklist.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.example.trucklist.model.Tires
import com.example.trucklist.model.Vehicle


class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    private fun Cursor.getString(column: String): String = getString(getColumnIndex(column))

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_VEHICLE)
        db.execSQL(SQL_CREATE_TIRES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_VEHICLE)
        db.execSQL(SQL_DELETE_TIRES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    @Throws(SQLiteConstraintException::class)
    fun insertVehicle(vehicle: Vehicle): Boolean = writableDatabase.use { db ->
        val values = ContentValues().apply {
            put(DBContract.VehicleEntry.COLUMN_PLATE, vehicle.plate)
            put(DBContract.VehicleEntry.COLUMN_MILEAGE,vehicle.mileage)
        }
        val newRowId = db.insert(DBContract.VehicleEntry.TABLE_NAME, null, values)

        newRowId > -1
    }

    @Throws(SQLiteConstraintException::class)
    fun insertTires(tires : Tires): Boolean = writableDatabase.use { db ->
        val values = ContentValues().apply {
            put(DBContract.TiresEntry.COLUMN_ID, tires.id)
            put(DBContract.TiresEntry.COLUMN_DOT, tires.dot)
            put(DBContract.TiresEntry.COLUMN_FIRE_NUMBER, tires.fireNumber)
            put(DBContract.TiresEntry.COLUMN_PRESSURE, tires.pressure)
            put(DBContract.TiresEntry.COLUMN_PLATE, tires.plate)
        }
        val newRowId = db.insert(DBContract.TiresEntry.TABLE_NAME, null, values)

        newRowId > -1
    }

    fun readGames(plate: String): ArrayList<Tires> =
        writableDatabase.use { db ->
            val selectQuery =
                "SELECT  * FROM ${DBContract.TiresEntry.TABLE_NAME} WHERE ${DBContract.TiresEntry.COLUMN_PLATE} = '${plate}'"
            db.rawQuery(selectQuery, null).use { cursor ->
                val mTires = ArrayList<Tires>()
                if (cursor.moveToFirst()) {
                    while (!cursor.isAfterLast) {
                        val id = cursor.getString(DBContract.TiresEntry.COLUMN_ID)
                        val dot = cursor.getString(DBContract.TiresEntry.COLUMN_DOT)
                        val fireNumber = cursor.getString(DBContract.TiresEntry.COLUMN_FIRE_NUMBER)
                        val pressure = cursor.getString(DBContract.TiresEntry.COLUMN_PRESSURE)
                        val plate =cursor.getString(DBContract.TiresEntry.COLUMN_PLATE)

                        mTires.add(
                            Tires(
                                id,
                                dot,
                                fireNumber,
                                pressure,
                                plate
                            )
                        )
                        cursor.moveToNext()
                    }
                }
                mTires
            }
        }

    fun readTiresToPlate(plate: String): ArrayList<Tires> =
        writableDatabase.use { db ->
            val selectQuery =
                "SELECT * FROM ${DBContract.TiresEntry.TABLE_NAME} WHERE ${DBContract.TiresEntry.COLUMN_PLATE} = $plate"
            db.rawQuery(selectQuery, null).use { cursor ->
                val tires = ArrayList<Tires>()
                if (cursor.moveToFirst()) {
                    while (!cursor.isAfterLast) {
                        val id = cursor.getString(DBContract.TiresEntry.COLUMN_ID)
                        val dot = cursor.getString(DBContract.TiresEntry.COLUMN_DOT)
                        val fireNumber = cursor.getString(DBContract.TiresEntry.COLUMN_FIRE_NUMBER)
                        val pressure = cursor.getString(DBContract.TiresEntry.COLUMN_PRESSURE)
                        val plate =cursor.getString(DBContract.TiresEntry.COLUMN_PLATE)

                        tires.add(
                            Tires(
                                id,
                                dot,
                                fireNumber,
                                pressure,
                                plate
                            )
                        )
                        cursor.moveToNext()
                    }
                }
                tires
            }
        }

    fun readAllVehicle(): ArrayList<Vehicle> {
        val vehicles = ArrayList<Vehicle>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from " + DBContract.VehicleEntry.TABLE_NAME, null)
        } catch (e: SQLiteException) {
            db.execSQL(SQL_CREATE_VEHICLE)
            return ArrayList()
        }

        var plate: String
        var mileage: String

        while (cursor.moveToNext()) {
            val plate = cursor.getString(DBContract.VehicleEntry.COLUMN_PLATE)
            val mileage = cursor.getString(DBContract.VehicleEntry.COLUMN_MILEAGE)
            vehicles.add(Vehicle(plate, mileage))
        }
        return vehicles
    }

    fun readAllTires(): ArrayList<Tires> {
        val tires = ArrayList<Tires>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from " + DBContract.TiresEntry.TABLE_NAME, null)
        } catch (e: SQLiteException) {
            db.execSQL(SQL_CREATE_TIRES)
            return ArrayList()
        }

        var id: String
        var dot: String
        var fireNumber: String
        var pressure: String
        var plateVehicle: String

        while (cursor.moveToNext()) {
            val id  = cursor.getString(DBContract.TiresEntry.COLUMN_ID)
            val dot = cursor.getString(DBContract.TiresEntry.COLUMN_DOT)
            val fireNumber = cursor.getString(DBContract.TiresEntry.COLUMN_FIRE_NUMBER)
            val pressure = cursor.getString(DBContract.TiresEntry.COLUMN_PRESSURE)
            val plateVehicle = cursor.getString(DBContract.TiresEntry.COLUMN_PLATE)

            tires.add(Tires(id, dot, fireNumber, pressure, plateVehicle))
        }
        return tires
    }

    fun readAllTiresToPlate(plate: String): ArrayList<Tires> {
        val tires = ArrayList<Tires>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(
                "select * from ${DBContract.TiresEntry.TABLE_NAME } " +
                        "where ${DBContract.TiresEntry.COLUMN_PLATE} = ${plate} ",null)
        } catch (e: SQLiteException) {
            db.execSQL(SQL_CREATE_TIRES)
            return ArrayList()
        }

        var id: String
        var dot: String
        var fireNumber: String
        var pressure: String
        var plateVehicle: String

        while (cursor.moveToNext()) {
            val id  = cursor.getString(DBContract.TiresEntry.COLUMN_ID)
            val dot = cursor.getString(DBContract.TiresEntry.COLUMN_DOT)
            val fireNumber = cursor.getString(DBContract.TiresEntry.COLUMN_FIRE_NUMBER)
            val pressure = cursor.getString(DBContract.TiresEntry.COLUMN_PRESSURE)
            val plateVehicle = cursor.getString(DBContract.TiresEntry.COLUMN_PLATE)

            tires.add(Tires(id, dot, fireNumber, pressure, plateVehicle))
        }
        return tires
    }

    companion object {
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "FeedReader.db"

        private val SQL_CREATE_VEHICLE =
            "CREATE TABLE " + DBContract.VehicleEntry.TABLE_NAME + " (" +
                    DBContract.VehicleEntry.COLUMN_PLATE + " TEXT PRIMARY KEY," +
                    DBContract.VehicleEntry.COLUMN_MILEAGE + " TEXT)"

        private val SQL_DELETE_VEHICLE = "DROP TABLE IF EXISTS " + DBContract.VehicleEntry.TABLE_NAME

        private val SQL_CREATE_TIRES =
            "CREATE TABLE " + DBContract.TiresEntry.TABLE_NAME + " (" +
                    DBContract.TiresEntry.COLUMN_ID + " TEXT PRIMARY KEY," +
                    DBContract.TiresEntry.COLUMN_DOT + " TEXT," +
                    DBContract.TiresEntry.COLUMN_PLATE+ " TEXT," +
                    DBContract.TiresEntry.COLUMN_FIRE_NUMBER + " TEXT," +
                    DBContract.TiresEntry.COLUMN_PRESSURE + " TEXT)"

        private val SQL_DELETE_TIRES = "DROP TABLE IF EXISTS " + DBContract.TiresEntry.TABLE_NAME
    }

}