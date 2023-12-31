package com.tasty.recipesapp.ui.RecipeFragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.tasty.recipesapp.R
import com.tasty.recipesapp.data.entities.RecipeEntity
import com.tasty.recipesapp.providers.RepositoryProvider
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject


class NewRecipeFragment : Fragment() {

    private lateinit var etTitle: EditText
    private lateinit var etDescription: EditText
    private lateinit var etPictureUrl: EditText
    private lateinit var etVideoUrl: EditText
    private lateinit var llIngredientsContainer: LinearLayout
    private lateinit var llInstructionsContainer: LinearLayout

    private var numberOfInstructions = 0;
    private var numberOfIngredients = 0;

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_new_recipe, container, false)
        etTitle = view.findViewById(R.id.etTitle)
        etDescription = view.findViewById(R.id.etDescription)
        etPictureUrl = view.findViewById(R.id.etPictureUrl)
        etVideoUrl = view.findViewById(R.id.etVideoUrl)
        llIngredientsContainer = view.findViewById(R.id.llIngredientsContainer)
        llInstructionsContainer = view.findViewById(R.id.llInstructionsContainer)

        val btnAddIngredient: Button = view.findViewById(R.id.btnAddIngredient)

        btnAddIngredient.setOnClickListener {
            addNewField(llIngredientsContainer, "Ingredient")
        }

        val btnAddInstruction: Button = view.findViewById(R.id.btnAddInstruction)
        btnAddInstruction.setOnClickListener {
            addNewField(llInstructionsContainer, "Instruction")
        }

        val btnSave: Button = view.findViewById(R.id.btnSave)
        btnSave.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                if(saveRecipe(view)) {
                    findNavController().navigateUp()
                    Toast.makeText(requireContext(), "Your Recipe Was Saved!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        val exitButton: Button = view.findViewById(R.id.exitAddRecipeButton)
        exitButton.setOnClickListener {
            findNavController().navigateUp()
        }

        return view
    }

    private fun addNewField(container: LinearLayout, hint: String) {
        val editText = EditText(requireContext())
        editText.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        if(container == llIngredientsContainer){
            editText.hint =  "#" +(numberOfIngredients + 1).toString() + "."+ hint;
            numberOfIngredients++
        } else {
            editText.hint =  "#" +(numberOfInstructions + 1).toString() + "."+  hint
            numberOfInstructions++
        }

        container.addView(editText)
    }

    private fun saveRecipe(view: View) : Boolean {

        etTitle = view.findViewById(R.id.etTitle)
        etDescription = view.findViewById(R.id.etDescription)
        etPictureUrl = view.findViewById(R.id.etPictureUrl)
        etVideoUrl = view.findViewById(R.id.etVideoUrl)

        llIngredientsContainer = view.findViewById(R.id.llIngredientsContainer)
        llInstructionsContainer = view.findViewById(R.id.llInstructionsContainer)

        val title = etTitle.text.toString()
        val description = etDescription.text.toString()
        val pictureURL = etPictureUrl.text.toString()
        val videoURL = etVideoUrl.text.toString()


//        Validation

        if(title.isEmpty() || description.isEmpty() ) {
            Toast.makeText(requireContext(), "Please Fill In At Least Title and Description!", Toast.LENGTH_SHORT).show()
            return false
        }

        val ingredients = mutableListOf<String>()
        for (i in 0 until llIngredientsContainer.childCount) {
            val editText = llIngredientsContainer.getChildAt(i) as? EditText
            editText?.let {
                if(it.text.toString().isNotEmpty()) {
                    ingredients.add(it.text.toString())
                }
            }
        }

        val instructions = mutableListOf<String>()
        for (i in 0 until llInstructionsContainer.childCount) {
            val editText = llInstructionsContainer.getChildAt(i) as? EditText
            editText?.let {
                if(it.text.toString().isNotEmpty()) {
                    instructions.add(it.text.toString())
                }
            }
        }

        val recipe = createJsonFromInputs(title, description, pictureURL, videoURL, ingredients, instructions)
        val recipeEntity = RecipeEntity(
            json = recipe
        )

        viewLifecycleOwner.lifecycleScope.launch {

            RepositoryProvider.recipeRepository.insertRecipe(recipeEntity)
        }

        findNavController().navigateUp()
        return true
    }
    private fun createJsonFromInputs(title: String, description: String, pictureUrl: String, videoUrl: String, ingredients: List<String>, instructions: List<String>): String {
        val jsonObject = JSONObject()
        jsonObject.put("title", title)
        jsonObject.put("description", description)
        jsonObject.put("thumbnailUrl", pictureUrl)
        jsonObject.put("videoUrl", videoUrl)
        jsonObject.put("ingredients", JSONArray(ingredients))
        jsonObject.put("instructions", JSONArray(instructions))

        return jsonObject.toString()
    }

}
