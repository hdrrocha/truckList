package com.example.trucklist.view.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.trucklist.R
import com.example.trucklist.model.Tires
import kotlinx.android.synthetic.main.tire_dialog.view.*

class TireDialog(tire: Tires) : DialogFragment() {
    lateinit var tire: Tires
    init {
       this.tire = tire
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        var _view: View = getActivity()?.getLayoutInflater()!!.inflate(R.layout.tire_dialog, null)

        _view.tv_tire_r?.text  = this.tire.id
        _view.tv_dot_r?.text  = this.tire.dot
        _view.tv_fire_number_r?.text  = this.tire.fireNumber
        _view.tv_pressure_r?.text  = this.tire.pressure
        _view.tv_plate_r?.text  = this.tire.plate

        var alert = AlertDialog.Builder(activity)
        alert.setView(_view)


        return alert.create()
    }

}