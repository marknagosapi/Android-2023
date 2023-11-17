package com.tasty.recipesapp.data.model

import com.google.gson.annotations.SerializedName

data class PriceModel (
    @SerializedName("consumption_portion")
    val consumptionPortion: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("portion")
    val portion: Int,
    @SerializedName("consumption_total")
    val consumptionTotal: Int

)
