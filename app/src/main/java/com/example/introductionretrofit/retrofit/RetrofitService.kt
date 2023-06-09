package com.example.introductionretrofit.retrofit

import com.example.introductionretrofit.models.Movie
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {

    @GET("marvel")
    fun getMovie(): Call<List<Movie>>

}