package com.example.testaplication

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class ToDatePickerFragment: DialogFragment(), DatePickerDialog.OnDateSetListener {
    interface Callbacks {
        fun onDataToSelected(data: Date)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        return DatePickerDialog(
            requireContext(),
            this,
            year,
            month,
            day
        )
    }

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        val resultData = Date(p1, p2, p3)
        targetFragment?.let {
            (it as FromDatePickerFragment.Callbacks).onDataToSelected(resultData)
        }
    }

    companion object {
        fun newInstance(): ToDatePickerFragment {
            return ToDatePickerFragment()
        }
    }
}