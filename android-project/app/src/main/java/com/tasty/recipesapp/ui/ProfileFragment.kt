package com.tasty.recipesapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavDirections
import androidx.navigation.findNavController

import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tasty.recipesapp.R

import com.tasty.recipesapp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("SCREENS","Opened Profile Screen!")
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false);

        val fabAddRecipe: FloatingActionButton = binding.fabAddRecipe
        fabAddRecipe.setOnClickListener {
            navigateToNewRecipeFragment()
        }
        return binding.root;
    }
    private fun navigateToNewRecipeFragment() {
        val action: NavDirections = object : NavDirections {

            override val actionId: Int
                get() = R.id.action_profileFragment_to_newRecipeFragment
            override val arguments: Bundle
                get() = bundleOf()

        }
        view?.findNavController()?.navigate(action)
    }
}