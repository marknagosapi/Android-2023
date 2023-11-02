package com.tasty.recipesapp.ui.RecipeFragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.FragmentProfileBinding
import com.tasty.recipesapp.databinding.FragmentRecipeDetailBinding

class RecipeDetailFragment : Fragment() {


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


}