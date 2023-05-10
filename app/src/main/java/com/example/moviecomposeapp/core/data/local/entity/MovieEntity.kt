package com.example.moviecomposeapp.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviecomposeapp.core.domain.model.MovieType

@Entity
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val poster: String,
    val type: MovieType
)

