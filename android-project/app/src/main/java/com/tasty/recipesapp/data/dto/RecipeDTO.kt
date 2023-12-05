package com.tasty.recipesapp.data.dto

import com.google.gson.annotations.SerializedName

data class RecipeDTO(
    val tags: List<TagDTO>,
    @SerializedName("thumbnail_alt_text")
    val thumbnailAltText: String?,
//    val promotion: String,
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String?,
//    val original_video_url: String?,
    val price: PriceDTO,
//    val tips_and_ratings_enabled: Boolean,
//    val servings_noun_plural: String,
    @SerializedName("user_ratings")
    val userRatings: UserRatingsDTO,
//
//    val language: String,
    val id: Int,
//
    val sections: List<SectionDTO>,
    val name: String,
    @SerializedName("video_url")
    val videoUrl: String,
//    val yields: String,
    val nutrition: NutritionDTO,
//    val is_app_only: Boolean,
//    val approved_at: Long,
//    val topics: List<TopicDTO>,
    val instructions: List<InstructionDTO>,
//    val slug: String,
//    val credits: List<CreditDTO>,
//    val nutrition_visibility: String,
//    val country: String,
//    val prep_time_minutes: Int,
//    val total_time_minutes: Int?,
//    val updated_at: Long,
//    val is_one_top: Boolean,
//    val video_id: Int,
//    val num_servings: Int,
//    val created_at: Long,
    val description: String,
//    val cook_time_minutes: Int,
//    val facebook_posts: List<String>,
//    val show_id: Int,
//
//    val keywords: String
)
