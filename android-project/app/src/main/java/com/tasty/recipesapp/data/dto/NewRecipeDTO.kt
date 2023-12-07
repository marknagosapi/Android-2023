package com.tasty.recipesapp.data.dto

data class NewRecipeDTO(
    val id: Long,
    val title: String?,
    val description: String?,
    val thumbnailUrl: String?,
    val videoUrl: String?,
    val ingredients: List<String>?,
    val instructions: List<String>?
)
