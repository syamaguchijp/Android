package com.example.sample

import kotlinx.serialization.*

@Serializable
data class RowData(
    val title: String,
    val user: User
) {
    @Serializable
    data class User(
        val id: String,
        val profile_image_url: String
    )
}
