package com.example.testaplication

import android.app.TimePickerDialog
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.sql.Time

class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {
    interface Callbacks {
        fun onTimeFromSelected(time: Time)
        fun onTimeToSelected(time: Time)
        fun onTimeTestSelected(time: Time)
    }

    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
        val resultTime = Time( p1, p2, 0)
        targetFragment?.let {
            (it as Callbacks).onTimeFromSelected(resultTime)
        }
        targetFragment?.let {
            (it as Callbacks).onTimeToSelected(resultTime)
        }
        targetFragment?.let {
            (it as Callbacks).onTimeTestSelected(resultTime)
        }
    }

}