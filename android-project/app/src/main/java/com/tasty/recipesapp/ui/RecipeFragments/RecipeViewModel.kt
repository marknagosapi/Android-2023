package com.tasty.recipesapp.ui.RecipeFragments

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tasty.recipesapp.data.dto.InstructionDTO

class RecipeViewModel : ViewModel(){

    init{
        fetchOneRecipeInstruction()
    }
    fun fetchOneRecipeInstruction() {
        val gson = Gson()
        val jsonString = "{\n" +
                "   \"appliance\": null,\n" +
                "   \"end_time\": 26500,\n" +
                "   \"temperature\": null,\n" +
                "   \"id\": 43381,\n" +
                "   \"position\": 1,\n" +
                "   \"display_text\": \"In a blender or food processor, combine the yogurt, lime\n" +
                "juice, pepper, and chili powder and pulse to combine. Add 1⁄2 of the avocado and\n" +
                "blend until creamy.\",\n" +
                "   \"start_time\": 7000\n" +
                "   }"

        val test = gson.fromJson(jsonString, InstructionDTO::class.java)
        Log.i("GSON", test.toString())
    }

    fun fetchAllRecipeInstruction() {

        val gson = Gson()

        val jsonString = """[{
            "appliance": null,
            "end_time": 26500,
            "temperature": null,
            "id": 43381,
            "position": 1,
            "display_text": "In a blender or food processor, combine the yogurt, lime
            juice, pepper, and chili powder and pulse to combine. Add ½ of the avocado and
            blend until creamy.",
            "start_time": 7000
            },{
            "appliance": null,
            "end_time": 26500,
            "temperature": null,
            "id": 43381,
            "position": 1,
            "display_text": "In a blender or food processor, combine the yogurt, lime
            juice, pepper, and chili powder and pulse to combine. Add ½ of the avocado and
            blend until creamy.",
            "start_time": 7000
            },{
            "appliance": null,
            "end_time": 26500,
            "temperature": null,
            "id": 43381,
            "position": 1,
            "display_text": "In a blender or food processor, combine the yogurt, lime
            juice, pepper, and chili powder and pulse to combine. Add ½ of the avocado and
            blend until creamy.",
            "start_time": 7000
            }]"""


        val type = object : TypeToken<List<InstructionDTO>>() {}.type
        val instructionList = gson.fromJson<List<InstructionDTO>>(jsonString,type)
        Log.i("GSON", instructionList.toString())

    }

    fun fetchRecipeInstructionsFromFile(){


    }

}