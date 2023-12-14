package com.tasty.recipesapp.ui.RecipeFragments

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasty.recipesapp.data.dao.RecipeDao

import com.tasty.recipesapp.data.dto.InstructionDTO
import com.tasty.recipesapp.data.model.InstructionModel
import com.tasty.recipesapp.data.model.RecipeModel
import com.tasty.recipesapp.data.repositories.RecipeRepository
import com.tasty.recipesapp.providers.RepositoryProvider
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {

    fun loadRecipesFromAssets(context: Context): List<RecipeModel>? {
        return RepositoryProvider.recipeRepository.loadRecipesFromAssets(context)
    }

    fun getAllRecipesFromApi(): List<RecipeModel>? {
        var recipes: List<RecipeModel> = listOf()
        viewModelScope.launch {
            recipes = RepositoryProvider.recipeRepository.getRecipesFromApi("0", "15")!!
        }
        return recipes
    }
}
