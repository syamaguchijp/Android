package com.example.sample.model

import android.util.Log

class UserManager() {

    fun updateUser(user: User, handler: (user: User, isSuccess: Boolean) -> Unit) {

        val query = mutableMapOf<String, String>("username" to user.name, "password" to user.password)

        NetworkManager().get("https://httpbin.org/get", query, { statusCode: Int, responseBody: String ->

            Log.i("TAG","complete!!! ${responseBody}")
            if (statusCode == 200) {
                handler(user, true)
            } else {
                handler(user, false)
            }

        })
    }
}