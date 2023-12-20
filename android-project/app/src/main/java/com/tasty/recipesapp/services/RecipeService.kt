package com.tasty.recipesapp.services

import com.tasty.recipesapp.data.dto.RecipeDTO
import com.tasty.recipesapp.data.model.RecipeModel
import com.tasty.recipesapp.data.repositories.RecipeResponseDTO
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RecipeService {
    @GET("recipes/list")
    @Headers(
        "X-RapidAPI-Key: dabe29c63fmshd8ca49f2c8d0d6cp134b59jsnb4569dcb3970",
        "X-RapidAPI-Host: tasty.p.rapidapi.com"
    )
suspend fun getRecipes(
    @Query("from") from: String, @Query("size") size: String, @Query("tags") tags: String? = null
): RecipeResponseDTO

}

