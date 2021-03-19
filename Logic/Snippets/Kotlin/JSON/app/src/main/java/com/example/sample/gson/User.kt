package com.example.sample.gson

import java.util.*

class User(
    var name: String,
    var birthday: Date,
    var isMale: Boolean,
    var age: Int,
    var height: Float,
    var nickName: String?
){
    fun dump() {
        println("$name $birthday $isMale $age $height $nickName")
    }
}