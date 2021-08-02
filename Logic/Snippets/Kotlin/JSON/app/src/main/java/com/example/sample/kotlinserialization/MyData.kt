package com.example.sample.kotlinserialization

import kotlinx.serialization.*

@Serializable
data class MyData(
    val title: String,
    val user: User
) {
    @Serializable
    data class User(
        val id: String,
        val profile_image_url: String
    )
}
