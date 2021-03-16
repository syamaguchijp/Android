package com.example.sample.car

import javax.inject.Inject

class Driver @Inject constructor() { // コンストラクタに@InjectをつけないとDaggerは注入を行ってくれない

    @Inject lateinit var car: Crown
    @Inject lateinit var car2: Skyline

    fun drive() {
        println("drive ${car.name}")
        println("drive ${car2.name}")
    }
}