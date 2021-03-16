package com.example.sample.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sample.model.User
import com.example.sample.model.UserManager
import java.lang.ref.WeakReference

class MainViewModel: ViewModel() {

    val name: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val password: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val result: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun updateUser(userName: String, password: String) {

        val user = User(userName, password)
        if (userName.length == 0 || password.length == 0) {
            displayResultLabel(user, false)
        }

        UserManager().updateUser(user, { user: User, isSuccess: Boolean ->
            displayResultLabel(user, isSuccess)
        })
    }

    private fun displayResultLabel(user: User?, isSuccess: Boolean) {

        var resultStr = "成功しました"
        if (!isSuccess) {
            resultStr = "失敗しました"
        }
        result.setValue(resultStr) // LiveDataでView側に反映される

        Log.i("TAG", resultStr)

    }
}