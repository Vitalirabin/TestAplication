package com.example.testaplication

import android.app.Dialog
import android.app.TimePickerDialog
import android.icu.text.DateFormat
import android.os.Bundle
import android.text.format.DateFormat.is24HourFormat
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.sql.Time
import java.util.*

class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {
    interface Callbacks {
        fun onTimeFromSelected(time: Time)
        fun onTimeToSelected(time: Time)
        fun onTimeTestSelected(time: Time)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current time as the default values for the picker
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        // Create a new instance of TimePickerDialog and return it
        return TimePickerDialog(activity, this, hour, minute, is24HourFormat(activity))
    }


    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
        val resultTime = Time(p1, p2, 0)
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

    companion object {
        fun newInstance(): TimePickerFragment {
            return TimePickerFragment()
        }
    }

}