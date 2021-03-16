package com.example.sample.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.sample.R
import com.example.sample.viewmodel.MainViewModel


// * 画面の描画処理を行う
// * Modelの参照は持たない

class MainFragment : Fragment() {

    private val vm: MainViewModel by viewModels<MainViewModel>()

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_main, container, false)

        val nameEditText = v.findViewById<EditText>(R.id.editTextTextPersonName)
        val nameEditTextObserver = Observer<String> { it ->
            nameEditText.setText(it)
        }
        vm.name.observe(this, nameEditTextObserver)

        val passwordEditText = v.findViewById<EditText>(R.id.editTextTextPassword)
        val passwordEditTextObserver = Observer<String> { it ->
            passwordEditText.setText(it)
        }
        vm.password.observe(this, passwordEditTextObserver)

        val resultTextView = v.findViewById<TextView>(R.id.resultTextView)
        val resultTextViewObserver = Observer<String> { it ->
            resultTextView.setText(it)
        }
        vm.result.observe(this, resultTextViewObserver)

        val button = v.findViewById<Button>(R.id.updateButton)
        button.setOnClickListener {
            vm.updateUser(nameEditText.text.toString(), passwordEditText.text.toString())
        }

        return v
    }

}