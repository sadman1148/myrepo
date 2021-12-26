package com.example.sometask.data

object apiURL {
    const val BASE_URL = "https://api.themoviedb.org/3/movie/"
    const val API_KEY = "2725f59d7322c984c041e29f052bd245"
    const val NEW_MOVIE_LIST = "${BASE_URL}now_playing?api_key=$API_KEY&language=en-US&page=1"
}