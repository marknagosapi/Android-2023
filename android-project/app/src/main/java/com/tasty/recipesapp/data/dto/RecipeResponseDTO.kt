package com.tasty.recipesapp.data.dto

data class RecipeResponseDTO (
    val count: Int,
    val results: List<RecipeDTO>
)