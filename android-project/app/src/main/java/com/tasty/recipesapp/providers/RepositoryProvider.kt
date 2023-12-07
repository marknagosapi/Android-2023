package com.tasty.recipesapp.providers
import android.content.Context
import com.tasty.recipesapp.data.dao.RecipeDao
import com.tasty.recipesapp.data.database.RecipeDatabase
import com.tasty.recipesapp.data.repositories.InstructionRepository
import com.tasty.recipesapp.data.repositories.PreferencesManager
import com.tasty.recipesapp.data.repositories.RecipeRepository

object RepositoryProvider {
    private lateinit var recipeDao: RecipeDao
    lateinit var preferencesManager : PreferencesManager
    fun initialize(context: Context) {
        recipeDao = RecipeDatabase.getDatabase(context).recipeDao()
    }
    fun initaliazePreferencesManager(context: Context) {
        preferencesManager = PreferencesManager(context)
    }

    val recipeRepository: RecipeRepository by lazy {
        checkInitialized()
        RecipeRepository(recipeDao)
    }
    private fun checkInitialized() {
        if (!::recipeDao.isInitialized) {
            throw UninitializedPropertyAccessException("RepositoryProvider has not been initialized")
        }
    }


}
