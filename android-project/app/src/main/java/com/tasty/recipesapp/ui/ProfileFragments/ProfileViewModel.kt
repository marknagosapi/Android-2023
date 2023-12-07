package com.tasty.recipesapp.ui.ProfileFragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.gson.Gson
import com.tasty.recipesapp.R
import com.tasty.recipesapp.data.entities.RecipeEntity
import com.tasty.recipesapp.data.model.NewRecipeModel
import com.tasty.recipesapp.providers.RepositoryProvider
import com.tasty.recipesapp.ui.RecipeFragments.NewRecipeDetailFragment
import kotlinx.coroutines.launch

class ProfileViewModel() : ViewModel(){

    fun navigateToNewRecipeFragment(view: View) {
        val action: NavDirections = object : NavDirections {

            override val actionId: Int
                get() = R.id.action_profileFragment_to_newRecipeFragment
            override val arguments: Bundle
                get() = bundleOf()

        }
        view?.findNavController()?.navigate(action)
    }

}