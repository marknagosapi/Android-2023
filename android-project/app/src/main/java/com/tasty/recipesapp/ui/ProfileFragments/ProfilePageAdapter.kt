package com.tasty.recipesapp.ui.ProfileFragments

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ProfilePagerAdapter(fragmentActivity: ProfileFragment) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MyRecipesFragment() // Create a fragment for "My Recipes"
            1 -> UserProfileFragment() // Create a fragment for the default profile screen
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}
