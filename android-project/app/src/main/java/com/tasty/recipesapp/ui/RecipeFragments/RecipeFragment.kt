package com.tasty.recipesapp.ui.RecipeFragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tasty.recipesapp.R
import com.tasty.recipesapp.data.model.RecipeModel
import com.tasty.recipesapp.databinding.FragmentProfileBinding
import com.tasty.recipesapp.databinding.FragmentRecipeBinding

class RecipeFragment : Fragment() {

    private lateinit var binding: FragmentRecipeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("SCREENS", "Opened Recipe Screen!")
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRecipeBinding.inflate(inflater, container, false);
        return binding.root;
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this)[RecipeViewModel::class.java]
        val recipes = viewModel.loadRecipesFromAssets(requireContext())

        val recyclerView: RecyclerView = binding.recyclerView;
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val adapter = recipes?.let { RecipeAdapter(it) }
        recyclerView.adapter = adapter


        recipes?.forEach {
            Log.d("Recipe", "Recipe ID: ${it.id} Recipe Name: ${it.name}")

            Log.d("Recipe", "Recipe Description:")
            Log.d("Recipe",it.description)
            Log.d("Recipe","Price: ${it.price.total}")
            Log.d("Recipe","Nutrition:")
            Log.d("Recipe", "Calories: ${it.nutrition.calories}")
            Log.d("Recipe", "Carbohydrates: ${it.nutrition.carbohydrates}")
            Log.d("Recipe", "Fat: ${it.nutrition.fat}")
            Log.d("Recipe", "Sugar: ${it.nutrition.sugar}")
            Log.d("Recipe", "Instructions:")
//            Log the instructions
            it.instructions.forEach {
                Log.d("Recipe", "\t${it.displayText}")
            }

//            Log the sections
            Log.d("Recipe", "Sections:")
            it.sections.forEach {
                Log.d("Recipe", "\t${it.name}")
                it.components.forEach {
                    Log.d("Recipe", "\t\t${it.raw_text}")
                }
            }
        }
    }
}