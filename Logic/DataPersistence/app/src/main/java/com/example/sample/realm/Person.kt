package com.example.sample.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Person(
    var name: String = "",
    var age: Int = 0
) : RealmObject()