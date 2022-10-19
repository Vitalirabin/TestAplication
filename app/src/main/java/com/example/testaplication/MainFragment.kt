package com.example.testaplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.sql.Time
import java.util.*

class MainFragment : Fragment(), TimePickerFragment.Callbacks, DatePickerFragment.Callbacks {
    private lateinit var dateFrom: Date
    private lateinit var timeFrom: Time
    private lateinit var dateTo: Date
    private lateinit var timeTo: Time
    private lateinit var dateTest: Date
    private lateinit var timeTest: Time
    private lateinit var resultTextView: TextView
    private lateinit var buttonFromDate: Button
    private lateinit var buttonToDate: Button
    private lateinit var buttonTestDate: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        buttonFromDate = view.findViewById<Button>(R.id.button_from_data)
        buttonToDate = view.findViewById<Button>(R.id.button_to_data)
        buttonTestDate = view.findViewById<Button>(R.id.button_test_data)
        return view
    }

    private fun dateInRange(from: Long, to: Long, test: Long): Int {
        if (from >= to) {
            return 2
        }
        if (test in from..to) {
            return 1
        }
        return 0
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonFromDate.setOnClickListener {
            DatePickerFragment.newInstance().apply {
                setTargetFragment(this@MainFragment, 0)
                show(this@MainFragment.requireFragmentManager(), "DateFragment")
            }
            TimePickerFragment.newInstance().apply {
                setTargetFragment(this@MainFragment, 1)
                show(this@MainFragment.requireFragmentManager(), "TimeFragment")
            }
        }
        buttonToDate.setOnClickListener {
            DatePickerFragment.newInstance().apply {
                setTargetFragment(this@MainFragment, 2)
                show(this@MainFragment.requireFragmentManager(), "DateFragment")
            }
            TimePickerFragment.newInstance().apply {
                show(this@MainFragment.requireFragmentManager(), "TimeFragment")
            }
        }
        buttonTestDate.setOnClickListener {
            DatePickerFragment.newInstance().apply {
                setTargetFragment(this@MainFragment, 3)
                show(this@MainFragment.requireFragmentManager(), "DateFragment")
            }
            TimePickerFragment.newInstance().apply {
                show(this@MainFragment.requireFragmentManager(), "TimeFragment")
            }
        }
        view.findViewById<Button>(R.id.button_check).setOnClickListener {
            resultTextView = view.findViewById<TextView>(R.id.textResult)
            val from = dateFrom.time + timeFrom.time
            val to = dateTo.time + timeTo.time
            val test = dateTest.time + timeTest.time
            when (dateInRange(from, to, test)) {
                1 -> resultTextView.text = "Дата попадает в диапазон"
                0 -> resultTextView.text = "Дата не попадает в диапазон"
                2 -> resultTextView.text = "Введен неправильный диапазон"
            }
        }
    }

    override fun onTimeFromSelected(time: Time) {
        timeFrom = time
    }

    override fun onTimeToSelected(time: Time) {
        timeTo = time
    }

    override fun onTimeTestSelected(time: Time) {
        timeTest = time
    }

    override fun onDataFromSelected(data: Date) {
        dateFrom = data
    }

    override fun onDataToSelected(data: Date) {
        dateTo = data
    }

    override fun onDataTestSelected(data: Date) {
        dateTest = data
    }

}