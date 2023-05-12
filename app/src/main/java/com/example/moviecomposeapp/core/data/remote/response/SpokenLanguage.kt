package com.example.moviecomposeapp.core.data.remote.response


import com.squareup.moshi.Json

data class SpokenLanguage(
    @field:Json(name = "english_name")
    val englishName: String,
    @field:Json(name = "iso_639_1")
    val iso6391: String,
    @field:Json(name = "name")
    val name: String
)