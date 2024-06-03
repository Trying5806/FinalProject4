package com.example.finalproject

import com.squareup.moshi.Json

data class MovieResponse(
    @Json(name = "Title") val title: String,
    @Json(name = "imdbRating") val imdbRating: String
)
