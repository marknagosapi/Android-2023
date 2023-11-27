package com.tasty.recipesapp.data.model

import com.google.gson.annotations.SerializedName

data class TagModel (
    val id: Int,
    @SerializedName("display_name")
    val displayName: String,
    val type: String,
    @SerializedName("root_tag_type")
    val rootTagType: String,
    val name: String
)
