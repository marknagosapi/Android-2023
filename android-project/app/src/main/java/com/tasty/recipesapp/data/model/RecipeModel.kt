package com.tasty.recipesapp.data.model
data class RecipeModel (
    val tags: List<TagModel>,
    val thumbnailAltText: String?,
//    val promotion: String,
    val thumbnailUrl: String?,
//    val original_video_url: String?,
    val price: PriceModel,
//    val tips_and_ratings_enabled: Boolean,
//    val servings_noun_plural: String,
    val userRatings: UserRatingsModel,
//    val language: String,
    val id: Int,
    val sections: List<SectionModel>,
    val name: String,
    val videoUrl: String?,
//    val yields: String,
    val nutrition: NutritionModel,
//
//    val approved_at: Long,
//    val topics: List<TopicModel>,
    val instructions: List<InstructionModel>,
//    val slug: String,
//    val credits: List<CreditModel>,
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
//    val keywords: String
)
// Sorting in ascending order based on title
val ascendingComparator: Comparator<RecipeModel> = compareBy { it.userRatings.score}
// Sorting in descending order based on title
val descendingComparator: Comparator<RecipeModel> = compareByDescending { it.userRatings.score }


