package com.tasty.recipesapp.ui.RecipeFragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.FragmentProfileBinding
import com.tasty.recipesapp.databinding.FragmentRecipeBinding

class RecipeFragment : Fragment() {

    private lateinit var binding: FragmentRecipeBinding
    private val recipeViewModel : RecipeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("SCREENS", "Opened Recipe Screen!")
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recipeViewModel.loadInstructionData(requireContext() )
        recipeViewModel.instructionModels.observe(viewLifecycleOwner) {  instructions ->
            for (instructionModel in instructions) {
                Log.d("Instructions", instructionModel.toString())
            }
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRecipeBinding.inflate(inflater, container, false);
        return binding.root;
    }
}