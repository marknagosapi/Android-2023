package com.tasty.recipesapp.ui.RecipeFragments

import android.media.MediaPlayer

import android.os.AsyncTask
import android.os.Bundle
import android.provider.MediaStore.Video
import android.widget.MediaController
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.media3.common.MediaItem
import androidx.media3.common.MimeTypes
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.FragmentNewRecipeDetailBinding
import com.tasty.recipesapp.providers.RepositoryProvider
import kotlinx.coroutines.launch
import java.io.IOException

class NewRecipeDetailFragment : Fragment() {

    private lateinit var binding: FragmentNewRecipeDetailBinding
    private lateinit var videoViewModel: VideoViewModel
    private lateinit var videoView: VideoView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewRecipeDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        videoViewModel = ViewModelProvider(this).get(VideoViewModel::class.java)
        videoView = binding.playerView

        binding.backButtonNewRecipeDetail.setOnClickListener {
            findNavController().navigateUp()
        }




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

                Glide.with(this@NewRecipeDetailFragment)
                    .load(recipe?.thumbnailUrl)
                    .placeholder(R.drawable.cheesecake_logo)
                    .into(imageViewRecipe)
                textViewTitle.text = recipe?.title
                textViewDescription.text = recipe?.description
                textViewIngredients.text = recipe?.ingredients?.joinToString(separator = "\n") { "- $it" }
                textViewInstructions.text = recipe?.instructions?.joinToString(separator = "\n") { "- $it" }
                textViewVideoUrl.text = recipe?.videoUrl


                // Observe changes in video readiness
                videoViewModel.isVideoReady.observe(viewLifecycleOwner, Observer { isReady ->
                    if (isReady) {
                        // Start playing the video
                        videoView.start()
                    } else {
                        // Handle the error or notify the user
                    }
                })
                recipe?.videoUrl?.let { videoViewModel.setVideoPath(it) }
            }
        }

        return view
    }
    override fun onDestroy() {
        // Release resources
        if (videoView.isPlaying) {
            videoView.stopPlayback()
        }
        super.onDestroy()
    }


}
