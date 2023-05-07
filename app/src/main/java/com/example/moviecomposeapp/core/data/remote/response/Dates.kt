package com.example.moviecomposeapp.core.data.remote.response

import com.squareup.moshi.Json

data class Dates(
    @field:Json(name = "maximum")
    val maximum: String,
    @field:Json(name = "minimum")
    val minimum: String
)