package com.tasty.recipesapp.data.model

data class InstructionModel(
    val id: Int,
    val displayText: String,
    val time: InstructionTime
)
data class InstructionTime(
    val startTime: Int,
    val endTime: Int
)
