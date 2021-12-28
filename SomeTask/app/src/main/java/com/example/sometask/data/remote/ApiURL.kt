package com.example.sometask.data.remote

object ApiURL {
    const val BASE_URL = "https://api.themoviedb.org/3/movie/"
    private const val API_KEY = "2725f59d7322c984c041e29f052bd245"
    private const val PAGE = "1"
    const val LATEST_MOVIE = "${BASE_URL}now_playing?api_key=${API_KEY}&language=en-US&page=${PAGE}"
    private const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/"
    private const val POSTER_SIZE_W342 = "w342"
    private const val POSTER_SIZE_W500 = "w500"
    private const val POSTER_SIZE_ORIGINAL = "original"
    const val POSTER_BASE_URL_OF_SIZE_W500 = POSTER_BASE_URL + POSTER_SIZE_W500
    const val POSTER_BASE_URL_OF_SIZE_W342 = POSTER_BASE_URL + POSTER_SIZE_W342
}