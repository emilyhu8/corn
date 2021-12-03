package com.example.cornspace

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Joke(
    val success: Boolean,
    val joke: String,
)
