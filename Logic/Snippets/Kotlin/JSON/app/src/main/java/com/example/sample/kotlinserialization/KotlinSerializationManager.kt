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
        val json = Json { ignoreUnknownKeys = true } // 定義にはない属性が文字列に含まれていても無視できる
        val myDataList = json.decodeFromString<List<MyData>>(str)
        println(myDataList)

        // オブジェクトから文字列へ
        val resultStr = Json.encodeToString(myDataList)
        println(resultStr)

    }
}