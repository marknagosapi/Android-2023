package com.tasty.recipesapp.ui.RecipeFragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.media3.common.MediaItem
import androidx.media3.common.MimeTypes
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.bumptech.glide.Glide

import com.tasty.recipesapp.databinding.FragmentNewRecipeDetailBinding
import com.tasty.recipesapp.providers.RepositoryProvider
import kotlinx.coroutines.launch

class NewRecipeDetailFragment : Fragment() {

    private lateinit var binding: FragmentNewRecipeDetailBinding
    private lateinit var player: ExoPlayer
    private lateinit var playerView: PlayerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewRecipeDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        playerView = binding.playerView // Initialize the PlayerView from your layout

        lifecycleScope.launch {
            val args = this@NewRecipeDetailFragment.arguments
            val inputId = args?.get("recipe") as? Long

            inputId?.let { id ->
                val recipe = RepositoryProvider.recipeRepository.getRecipeById(id)
                Log.d("Recipe", "onCreateView: " + recipe)
                val textViewTitle = binding.textViewTitle
                val imageViewRecipe = binding.imageViewRecipe
                val textViewDescription = binding.textViewDescription
                val textViewIngredients = binding.textViewIngredients
                val textViewInstructions = binding.textViewInstructions
                val textViewVideoUrl = binding.textViewVideoUrl

                Glide.with(this@NewRecipeDetailFragment).load(recipe?.thumbnailUrl).into(imageViewRecipe)
                textViewTitle.text = recipe?.title
                textViewDescription.text = recipe?.description
                textViewIngredients.text = recipe?.ingredients?.joinToString(separator = "\n") { "- $it" }
                textViewInstructions.text = recipe?.instructions?.joinToString(separator = "\n") { "- $it" }
                textViewVideoUrl.text = recipe?.videoUrl

                // Initialize player
                player = ExoPlayer.Builder(requireContext()).build()

                val mediaItem = MediaItem.Builder()
                    .setUri(recipe?.videoUrl ?: "") // Use the actual video URL from your recipe
                    .setMimeType(MimeTypes.APPLICATION_MP4)
                    .build()

                playerView.player = player // Set the player for the playerView

                // Prepare the player and start playback
                player.setMediaItem(mediaItem)
                player.prepare()
                player.play()
            }
        }

        return view
    }
}
