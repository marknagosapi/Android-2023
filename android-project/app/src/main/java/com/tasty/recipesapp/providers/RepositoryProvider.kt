package com.tasty.recipesapp.providers
import android.content.Context
import com.tasty.recipesapp.data.dao.RecipeDao
import com.tasty.recipesapp.data.database.RecipeDatabase
import com.tasty.recipesapp.data.repositories.InstructionRepository
import com.tasty.recipesapp.data.repositories.RecipeRepository

object RepositoryProvider {
    private lateinit var recipeDao: RecipeDao
    fun initialize(context: Context) {
        recipeDao = RecipeDatabase.getDatabase(context).recipeDao()
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
    val instructionsRepository: InstructionRepository = InstructionRepository()

}
