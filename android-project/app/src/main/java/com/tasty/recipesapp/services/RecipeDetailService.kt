package com.tasty.recipesapp.services

import com.tasty.recipesapp.data.dto.RecipeDTO
import com.tasty.recipesapp.data.repositories.RecipeResponseDTO
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RecipeDetailService {
    @GET("recipes/get-more-info")
    @Headers(
        "X-RapidAPI-Key: d6c1a944bamsh08ccf3ccbca8dfdp134997jsn1106db769446",
        "X-RapidAPI-Host: tasty.p.rapidapi.com"
    )
    suspend fun getRecipeDetail(
        @Query("id") id: String
    ): RecipeDTO
}