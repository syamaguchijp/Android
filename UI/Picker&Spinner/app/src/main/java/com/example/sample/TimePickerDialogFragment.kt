package com.example.sample

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class TimePickerDialogFragment: DialogFragment(), TimePickerDialog.OnTimeSetListener {

    var closure: ((hour: Int, minute: Int) -> Unit)? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        return TimePickerDialog(requireActivity(), this,  hour, minute,true)
    }

    override fun onTimeSet(view: TimePicker, hour: Int, minute: Int) {

        closure?.let {
            it(hour, minute)
        }
    }
}