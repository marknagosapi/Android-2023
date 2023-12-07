package com.tasty.recipesapp.ui.ProfileFragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


import com.tasty.recipesapp.databinding.FragmentProfileBinding
import com.tasty.recipesapp.ui.RecipeFragments.NewRecipeAdapter


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var recipeAdapter: NewRecipeAdapter
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


        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fabAddRecipe: FloatingActionButton = binding.fabAddRecipe
        fabAddRecipe.setOnClickListener {
            profileViewModel.navigateToNewRecipeFragment(binding.root)
        }

        viewPager = binding.viewPager
        tabLayout = binding.tabLayout

        profileViewModel = ViewModelProvider(requireActivity()).get(ProfileViewModel::class.java)

        // Create and set up the adapter
        val profilePagerAdapter = ProfilePagerAdapter(this)
        viewPager.adapter = profilePagerAdapter

        // Set up tabs
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "My Recipes"
                1 -> "Profile"
                else -> ""
            }
        }.attach()
    }
}