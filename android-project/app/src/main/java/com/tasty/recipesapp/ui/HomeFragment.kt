package com.tasty.recipesapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.R
import androidx.navigation.findNavController
import com.tasty.recipesapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        binding.getStartedButton.setOnClickListener() {
            binding.root.findNavController().navigate(com.tasty.recipesapp.R.id.action_homeFragment_to_recipeFragment)
        }
        return binding.root;
    }

}