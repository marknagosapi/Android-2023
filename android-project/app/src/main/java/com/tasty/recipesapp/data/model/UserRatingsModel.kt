package com.tasty.recipesapp.data.model

import com.google.gson.annotations.SerializedName

data class UserRatingsModel(
    @SerializedName("count_positive")
    val countPositive: Int,
    @SerializedName("score")
    val score: Double,
    @SerializedName("count_negative")
    val countNegative: Int
)