package com.tasty.recipesapp.data.model

data class ComponentModel(
    val position: Int,
    val measurements: List<MeasurementDTO>,
    val raw_text: String,
    val extra_comment: String,
    val ingredient: IngredientModel,
    val id: Int
)
