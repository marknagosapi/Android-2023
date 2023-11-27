package com.tasty.recipesapp.ui.RecipeFragments

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tasty.recipesapp.data.dao.RecipeDao

import com.tasty.recipesapp.data.dto.InstructionDTO
import com.tasty.recipesapp.data.model.InstructionModel
import com.tasty.recipesapp.data.model.RecipeModel
import com.tasty.recipesapp.data.repositories.RecipeRepository
import com.tasty.recipesapp.providers.RepositoryProvider

class RecipeViewModel : ViewModel(){

    fun loadRecipesFromAssets(context: Context): List<RecipeModel>? {
        return RepositoryProvider.recipeRepository.loadRecipesFromAssets(context)
    }

}