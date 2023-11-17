package com.tasty.recipesapp.ui.RecipeFragments

// RecipeAdapter.kt
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tasty.recipesapp.data.model.RecipeModel

import com.tasty.recipesapp.R
class RecipeAdapter(private val recipes: List<RecipeModel>) :


    RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.recipeTitle.text = recipe.name
        holder.recipeCalories.text = "Calories: " + recipe.nutrition.calories.toString()
        holder.recipeProtein.text = "Protein: " + recipe.nutrition.protein.toString()
        holder.recipeCarbs.text = "Carbohydrates: " + recipe.nutrition.carbohydrates.toString()
        holder.recipeFats.text = "Fats: " + recipe.nutrition.fat.toString()
        // Bind other data as needed
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recipeTitle: TextView = itemView.findViewById(R.id.recipeTitle)
        val recipeCalories: TextView = itemView.findViewById(R.id.calories)
        val recipeProtein: TextView = itemView.findViewById(R.id.protein)
        val recipeCarbs: TextView = itemView.findViewById(R.id.carbohydrates)
        val recipeFats: TextView = itemView.findViewById(R.id.fats)
        // Initialize other views here
    }
}
