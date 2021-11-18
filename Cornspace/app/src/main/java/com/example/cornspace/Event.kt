package com.example.cornspace

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Event (

        val name: String? = "",
        val location: String? ="",
        val details: String?="",
        val date: String? = "",

)