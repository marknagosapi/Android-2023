package com.tasty.recipesapp.data.model

import com.tasty.recipesapp.data.dto.UnitDTO
data class MeasurementDTO(
    val unit: UnitDTO,
    val quantity: String,
    val id: Int
)