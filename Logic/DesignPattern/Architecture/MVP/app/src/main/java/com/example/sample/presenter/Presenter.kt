package com.example.sample.presenter

import android.util.Log
import com.example.sample.model.User
import com.example.sample.model.UserManager
import java.lang.ref.WeakReference

interface PresenterInput {
    fun updateUser(userName: String, password: String)
}

interface PresenterOutput {
    fun changeResultLabel(string: String)
}

// * プレゼンテーションロジックを処理する
// * Viewに描画指示を出す（デリゲート）

class Presenter {

    var callbackRef: WeakReference<PresenterOutput>? = null // 弱参照

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
        Log.i("TAG", resultStr)

        val callback = callbackRef?.get()
        callback?.changeResultLabel(resultStr)
    }
}