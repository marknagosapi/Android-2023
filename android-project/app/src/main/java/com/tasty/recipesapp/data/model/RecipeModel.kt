package com.tasty.recipesapp.data.model
data class RecipeDTO(
    val aspect_ratio: String?,
    val brand_id: String?,
    val tags: List<TagModel>,
    val thumbnail_alt_text: String?,
    val promotion: String,
    val thumbnail_url: String?,
    val original_video_url: String?,
    val price: PriceModel,
    val tips_and_ratings_enabled: Boolean,
    val servings_noun_plural: String,
    val video_ad_content: String,
    val seo_title: String?,
    val seo_path: String,
    val canonical_id: String,
    val beauty_url: String?,
    val user_ratings: UserRatingsModel,
    val draft_status: String,
    val language: String,
    val id: Int,
    val servings_noun_singular: String,
    val sections: List<SectionModel>,
    val name: String,
    val inspired_by_url: String?,
    val video_url: String,
    val yields: String,
    val nutrition: NutritionModel,
    val is_app_only: Boolean,
    val approved_at: Long,
    val topics: List<TopicModel>,
    val instructions: List<InstructionModel>,
    val slug: String,
    val credits: List<CreditModel>,
    val nutrition_visibility: String,
    val is_subscriber_content: Boolean,
    val country: String,
    val prep_time_minutes: Int,
    val total_time_minutes: Int?,
    val updated_at: Long,
    val is_one_top: Boolean,
    val video_id: Int,
    val num_servings: Int,
    val created_at: Long,
    val description: String,
    val cook_time_minutes: Int,
    val facebook_posts: List<String>,
    val show_id: Int,
    val is_shoppable: Boolean,
    val keywords: String
)
