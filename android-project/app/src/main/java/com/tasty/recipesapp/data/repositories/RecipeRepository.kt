package com.tasty.recipesapp.data.repositories

import com.google.gson.Gson
import com.tasty.recipesapp.data.dto.RecipeDTO
import android.content.Context
import com.tasty.recipesapp.data.dao.RecipeDao
import com.tasty.recipesapp.data.entities.RecipeEntity
import com.tasty.recipesapp.data.model.RecipeModel
import com.tasty.recipesapp.data.utils.Mapping.toModel
import com.tasty.recipesapp.data.utils.Mapping.toModelList
import org.json.JSONObject
import java.lang.Exception

data class RecipeResponseDTO(
    val count : Int,
    val results: List<RecipeDTO>
)
class RecipeRepository(private val recipeDao: RecipeDao){

    fun loadRecipesFromAssets(context: Context): List<RecipeModel>? {
        val jsonFileName = "data.json"
        val json: String
        try {
            val inputStream = context.assets.open(jsonFileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charsets.UTF_8)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
        val gson = Gson()
//        Create a list of the recipes from the results of the json file
        val recipeList = gson.fromJson(json, RecipeResponseDTO::class.java)
        return recipeList.results.toModelList()
    }

    suspend fun insertRecipe(recipe: RecipeEntity) {
        recipeDao.insertRecipe(recipe)
    }
    suspend fun getAllRecipes(): List<RecipeModel> {
        val gson = Gson()
        return recipeDao.getAllRecipes().map {
            val jsonObject = JSONObject(it.json)
            jsonObject.apply{ put("id", it.internalId) }
            gson.fromJson(jsonObject.toString(), RecipeDTO::class.java).toModel()
        }
    }
}

