package com.example.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Spinner
        val array = getResources().getStringArray(R.array.list)
        val adapter = ArrayAdapter<String>(applicationContext,
            android.R.layout.simple_spinner_dropdown_item, array)
        val spinner = findViewById<Spinner>(R.id.spinner)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?,
                                        view: View?, position: Int, id: Long) {
                println("onItemSelected")
                val spinnerParent = parent as Spinner
                val item = spinnerParent.selectedItem as String
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        // DialogFragment ラジオボタンで選択
        val editText3 = findViewById<EditText>(R.id.editText)
        editText3.isClickable = false
        editText3.isFocusable = false
        editText3.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View) {
                val fragment = SelectDialogFragment()
                fragment.list = array
                fragment.closure = { result: String ->
                    editText3.setText(result)
                }
                fragment.show(supportFragmentManager, "SelectDialogFragment")
            }
        })

        // Picker Date
        val editText = findViewById<EditText>(R.id.editTextDate)
        editText.isClickable = false
        editText.isFocusable = false
        editText.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View) {
                val fragment = DatePickerDialogFragment()
                fragment.closure = { year: Int, month: Int, day: Int ->
                    editText.setText("%d/%02d/%02d".format(year, month+1, day))
                }
                fragment.show(supportFragmentManager, "datePicker")
            }
        })

        // Picker Time
        val editText2 = findViewById<EditText>(R.id.editTextTime)
        editText2.isClickable = false
        editText2.isFocusable = false
        editText2.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View) {
                val fragment = TimePickerDialogFragment()
                fragment.closure = { hour: Int, minite: Int ->
                    editText2.setText("%02d:%02d".format(hour, minite))
                }
                fragment.show(supportFragmentManager, "timePicker")
            }
        })

    }
}