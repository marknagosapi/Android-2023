package com.tasty.recipesapp.ui.RecipeFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.tasty.recipesapp.R


class NewRecipeFragment : Fragment() {

    private lateinit var etTitle: EditText
    private lateinit var etDescription: EditText
    private lateinit var etPictureUrl: EditText
    private lateinit var etVideoUrl: EditText
    private lateinit var llIngredientsContainer: LinearLayout
    private lateinit var llInstructionsContainer: LinearLayout

    private var numberOfInstructions = 0;
    private var numberOfIngredients = 0;

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
            saveRecipe()
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

    private fun saveRecipe() {
        // Implement the logic to save the recipe in the Room database
        // You may need to create a Recipe entity and a RecipeDAO for database operations
        // Example: RecipeDatabase.getInstance(requireContext()).getRecipeDao().insert(recipe)
    }
}
