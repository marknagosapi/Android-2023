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

    private val _allRecipes = MutableLiveData<List<NewRecipeModel>>()
    val allRecipes: LiveData<List<NewRecipeModel>>
        get() = _allRecipes
    suspend fun getAllRecipes(){
        viewModelScope.launch {
            val recipes = RepositoryProvider.recipeRepository.getAllOwnRecipes()
            _allRecipes.value = recipes
        }
    }

    fun insertRecipe(recipe: RecipeEntity) {
        viewModelScope.launch {
            RepositoryProvider.recipeRepository.insertRecipe(recipe)
        }
    }

    fun removeRecipe(recipe: RecipeEntity) {
        viewModelScope.launch {
            RepositoryProvider.recipeRepository.deleteRecipe(recipe)
            _allRecipes.value = RepositoryProvider.recipeRepository.getAllOwnRecipes()
        }
    }

    fun confirmDeleteRecipe(recipe: NewRecipeModel, context: Context, profileFragment: ProfileFragment) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.apply {
            setTitle("Recept törlése")
            setMessage("Biztosan törölni szeretné ezt a receptet?")
            setPositiveButton("Igen") { _, _ ->
                deleteRecipe(recipe, profileViewModel = this@ProfileViewModel, viewLifecycleOwner = profileFragment)
            }
            setNegativeButton("Mégsem") { dialog, _ ->
                dialog.dismiss()
            }
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
    private fun deleteRecipe(recipe: NewRecipeModel, profileViewModel: ProfileViewModel = this, viewLifecycleOwner: ProfileFragment) {
        val recipeEntity = convertToRecipeEntity(recipe)

        viewLifecycleOwner.lifecycleScope.launch {
            profileViewModel.removeRecipe(recipeEntity)
            profileViewModel.getAllRecipes()
        }
    }
    private fun convertToRecipeEntity(recipe: NewRecipeModel): RecipeEntity {
        val gson = Gson()
        return RecipeEntity(
            internalId = recipe.id,
            json = gson.toJson(recipe)
        )
    }

    fun onRecipeClicked(recipe: NewRecipeModel, fragment: ProfileFragment) {
        val bundle = Bundle()
        bundle.putLong("recipe", recipe.id)
        val detailFragment = NewRecipeDetailFragment()
        detailFragment.arguments = bundle
        NavHostFragment.findNavController(fragment).navigate(R.id.action_profileFragment_to_newRecipeDetailFragment, bundle)
    }

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