package com.B34.nutracker

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FoodApi {
    interface FoodApi {
        @GET("foods/search")
        fun searchFood(@Query("query") query: String, @Query("api_key") apiKey: String): Call<FoodResponse>

        @GET("{fdcId}")
        fun getFoodDetails(@Path("fdcId") fdcId: Int, @Query("api_key") apiKey: String): Call<FoodDetailsResponse>
    }
}