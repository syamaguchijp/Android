package com.example.sample.kotlinserialization

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class KotlinSerializationManager {

    fun test() {

        // 文字列からオブジェクトへ
        var str = """
            [{"title":"タイトル","user":{"id":"ABCDE","profile_image_url":"imageURL1"}},
            {"title":"タイトル2","user":{"id":"ABCDE2","profile_image_url":"imageURL2"}}]
            """
        val myDataList = Json.decodeFromString<List<MyData>>(str)
        println(myDataList)

        // オブジェクトから文字列へ
        val resultStr = Json.encodeToString(myDataList)
        println(resultStr)

    }
}