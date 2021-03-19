package com.example.sample.jsonobject

import org.json.JSONObject
import java.util.*

class JsonObjectManager {

    fun test() {

        // 文字列からオブジェクトへ
        val json = JSONObject("{\"name\":\"Kawasaki\",\"age\":27}")
        println(json.getString("name"))
        println(json.getString("age"))

        // オブジェクトから文字列へ
        val json2 = JSONObject()
        json2.put("name", "Suzuki")
        json2.put("age", 30)
        json2.put("birthday", Date())
        json2.put("nickname", null)
        println(json2.toString())
    }
}