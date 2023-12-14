package com.tasty.recipesapp.services

import android.util.Log
import com.tasty.recipesapp.data.dto.RecipeDTO
import com.tasty.recipesapp.data.model.RecipeModel
import com.tasty.recipesapp.data.repositories.RecipeResponseDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RecipeApiClient {
    companion object {
        private const val BASE_URL = "https://tasty.p.rapidapi.com/"
        private val recipeService: RecipeService

        init {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            recipeService = retrofit.create(RecipeService::class.java)
        }
    }

    suspend fun getRecipes(from: String, size: String, tags: String? = null): RecipeResponseDTO? {
        return withContext(Dispatchers.IO) { try {
            recipeService.getRecipes(from, size, tags) } catch (e: Exception) {
            null
            }
        }
    }
}

