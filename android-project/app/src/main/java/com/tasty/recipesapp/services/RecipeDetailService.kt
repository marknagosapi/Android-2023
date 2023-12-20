package com.tasty.recipesapp.services

import com.tasty.recipesapp.data.dto.RecipeDTO
import com.tasty.recipesapp.data.repositories.RecipeResponseDTO
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RecipeDetailService {
    @GET("recipes/get-more-info")
    @Headers(
        "X-RapidAPI-Key: dabe29c63fmshd8ca49f2c8d0d6cp134b59jsnb4569dcb3970",
        "X-RapidAPI-Host: tasty.p.rapidapi.com"
    )
    suspend fun getRecipeDetail(
        @Query("id") id: String
    ): RecipeDTO
}