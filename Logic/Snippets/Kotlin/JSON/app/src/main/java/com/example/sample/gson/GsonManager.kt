package com.example.sample.gson

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

class GsonManager {

    fun test() {

        // オブジェクトから文字列へ
        val user = User("Honda", Date(), true, 21, 175.1f, null)
        val userString = Gson().toJson(user)
        println(userString)

        // 文字列からオブジェクトへ
        val user2 = Gson().fromJson<User>(userString, User::class.java) as User
        user2.dump()

        // Mapから文字列へ
        val map = mutableMapOf<String, Any>("a" to "AAAA", "b" to 7, "c" to 100.1)
        val mapString = Gson().toJson(map)
        println(mapString)

        // 文字列からMapへ
        val type : Type = object : TypeToken<MutableMap<String, Any>>() {}.type
        val map2 = Gson().fromJson<MutableMap<String, Any>>(mapString, type) as MutableMap<String, Any>
        for ((key, value) in map2) {
            println("$key = $value")
        }

    }
}