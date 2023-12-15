package com.tasty.recipesapp.ui.RecipeFragments

import android.util.Log

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasty.recipesapp.data.model.RecipeModel
import com.tasty.recipesapp.providers.RepositoryProvider
import kotlinx.coroutines.launch

class RecipeDetailViewModel : ViewModel() {

    private val recipeData = MutableLiveData<RecipeModel>()

    fun loadRecipeFromApi(id: String): RecipeModel? {
    viewModelScope.launch {
        recipeData.value = RepositoryProvider.recipeRepository.getRecipeDetailFromApi(id)
    }
        Log.d("RecipeDetailViewModel", "recipeData.value: ${recipeData.value}")
        return recipeData.value
    }
}