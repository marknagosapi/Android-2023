package com.tasty.recipesapp.ui.RecipeFragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.FragmentRecipeDetailBinding
import kotlin.math.log

class RecipeDetailFragment : Fragment() {

    companion object {
        const val recipeTitle = "arg_recipe_title"
        const val recipeDescription = "arg_recipe_description"
        private const val SWIPE_THRESHOLD = 500
        const val ARG_RECIPE_IMAGE = "arg_recipe_image"
        const val ARG_RECIPE_INSTRUCTIONS = "arg_recipe_instructions"
        // Add other necessary arguments
    }
    private lateinit var binding: FragmentRecipeDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("SCREENS","Opened Recipe Detail Screen!")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRecipeDetailBinding.inflate(inflater, container, false);
        return binding.root;
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this)[RecipeDetailViewModel::class.java]

        val id = arguments?.getInt("arg_recipe_id") ?: 0

        val loadingAnimationResourceId = R.drawable.loading_animation

        viewModel.loadRecipeFromApi(id.toString())
        viewModel.recipeData.observe(viewLifecycleOwner){recipe->
            // Update UI with recipe details
            Glide.with(requireContext())
                .load(recipe?.thumbnailUrl)
                .placeholder(loadingAnimationResourceId)
                .into(binding.recipeDetailImage)


            var instructions: MutableList<String> = mutableListOf()
            var result: String = ""
            recipe?.instructions?.forEach() { ins ->
               instructions.add("- " + ins.displayText)
            }

            var ingredients: MutableList<String> = mutableListOf()
            recipe?.sections?.forEach() { sec ->
                sec.components.forEach() { comp ->
                    ingredients.add("- " + comp.ingredient.name)
                }
            }
            val ingredientResult = ingredients.joinToString(separator = "\n")
            result = instructions.joinToString(separator = "\n")
            binding.recipeIngredientsHolder.text = ingredientResult
            binding.recipeInstructionsHolder.text = result
            binding.recipeTitle.text = recipe?.name
            binding.recipeDescription.text = recipe?.description

        }

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        view.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_MOVE && event.x > event.rawX + SWIPE_THRESHOLD) {
                // Swipe right detected
                activity?.onBackPressed()
                true
            } else {
                false
            }
        }

    }


}