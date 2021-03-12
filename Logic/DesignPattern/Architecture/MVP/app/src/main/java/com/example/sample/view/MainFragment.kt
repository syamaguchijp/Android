package com.example.sample.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.sample.R
import com.example.sample.presenter.Presenter
import com.example.sample.presenter.PresenterInput
import com.example.sample.presenter.PresenterOutput
import java.lang.ref.WeakReference


// * ユーザーの入力をPresenterに伝える
// * 画面の描画処理を行う
// * Modelの参照は持たない

class MainFragment : Fragment(), PresenterInput, PresenterOutput {

    val presenter = Presenter()
    var resultTextView: TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_main, container, false)

        resultTextView = v.findViewById<TextView>(R.id.resultTextView)
        val nameEditText = v.findViewById<EditText>(R.id.editTextTextPersonName)
        val passwordEditText = v.findViewById<EditText>(R.id.editTextTextPassword)
        val button = v.findViewById<Button>(R.id.updateButton)
        button.setOnClickListener {
            updateUser(nameEditText.text.toString(), passwordEditText.text.toString())
        }

        presenter.callbackRef =  WeakReference<PresenterOutput>(this)

        return v
    }

    override fun updateUser(userName: String, password: String) {

        presenter.updateUser(userName, password)
    }

    override fun changeResultLabel(string: String) {

        resultTextView!!.setText(string)
    }

}