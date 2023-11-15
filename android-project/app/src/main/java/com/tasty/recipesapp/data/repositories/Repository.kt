package com.tasty.recipesapp.data.repositories

import com.google.gson.Gson
import com.tasty.recipesapp.data.dto.RecipeDTO
import android.content.Context
import com.google.gson.reflect.TypeToken
import com.tasty.recipesapp.data.model.RecipeModel
import com.tasty.recipesapp.data.utils.Mapping.toModelList
import java.io.IOException
import java.lang.Exception

data class RecipeResponseDTO(
    val count : Int,
    val results: List<RecipeDTO>
)

class RecipeRepository{
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


    fun readAll(context: Context): List<RecipeDTO> {
        val gson = Gson()
        var recipeList = listOf<RecipeDTO>()
        val assetManager = context.assets
        try {
            val inputStream = assetManager.open("data.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val jsonString = String(buffer, Charsets.UTF_8)

//            //If there is an extra label
//            val jsonObject = JSONObject(jsonString)
//            val instructionsArray = jsonObject.getJSONArray("instructions")

            val type = object : TypeToken<List<RecipeDTO>>() {}.type
            //if it is simple
            //val instructionList = gson.fromJson<List<InstructionDTO>>(jsonString, type)
            // if with label
//            recipeList = gson.fromJson(instructionsArray.toString(), type)


//            Log.i("GSON", recipeList.toString())
            //instructions.value = instructionList
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return recipeList
    }
}

