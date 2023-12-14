package com.tasty.recipesapp.ui.RecipeFragments

// RecipeAdapter.kt
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tasty.recipesapp.data.model.RecipeModel

import com.tasty.recipesapp.R
import com.tasty.recipesapp.data.model.ascendingComparator
import com.tasty.recipesapp.data.model.descendingComparator
import java.util.Locale

class RecipeAdapter(private var recipes: List<RecipeModel>) :

    RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>(), Filterable{

    private var filteredRecipes: List<RecipeModel> = recipes

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val recipeTitle: TextView = itemView.findViewById(R.id.recipeTitle)
        val recipeCalories: TextView = itemView.findViewById(R.id.calories)
        val recipeProtein: TextView = itemView.findViewById(R.id.protein)
        val recipeCarbs: TextView = itemView.findViewById(R.id.carbohydrates)
        val recipeFats: TextView = itemView.findViewById(R.id.fats)
        val recipeImage: ImageView = itemView.findViewById(R.id.recipeImage)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = filteredRecipes[position]
        holder.recipeTitle.text = recipe.name
        holder.recipeCalories.text = "Calories: " + recipe.nutrition.calories.toString()
        holder.recipeProtein.text = "Protein: " + recipe.nutrition.protein.toString()
        holder.recipeCarbs.text = "Carbohydrates: " + recipe.nutrition.carbohydrates.toString()
        holder.recipeFats.text = "Fats: " + recipe.nutrition.fat.toString()

        holder.itemView.setOnClickListener { view ->
            val action: NavDirections = object : NavDirections {

                override val actionId: Int
                    get() = R.id.action_recipeFragment_to_recipeDetailFragment2
                override val arguments: Bundle
                    get() = bundleOf(
                        "arg_recipe_title" to recipe.name,
                        "arg_recipe_description" to recipe.description,
                        "arg_recipe_image" to recipe.thumbnailUrl,
                        "arg_recipe_instructions" to recipe.instructions
                    )
            }
            view.findNavController().navigate(action)
        }

        Glide.with(holder.itemView.context)
            .load(recipe.thumbnailUrl)
            .placeholder(R.drawable.cheesecake_logo) // Placeholder image if loading fails
            .into(holder.recipeImage)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<RecipeModel>) {
        recipes = newList
        filteredRecipes = newList
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun sort(ascending: Boolean) {
        val comparator = if (ascending) ascendingComparator else descendingComparator
        filteredRecipes = filteredRecipes.sortedWith(comparator)
        notifyDataSetChanged()
    }

    // Implement Filterable interface methods
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val query = charSequence.toString().lowercase(Locale.getDefault())
                val filteredList = mutableListOf<RecipeModel>()

                for (recipe in recipes) {
                    if (recipe.name.lowercase(Locale.getDefault()).contains(query)) {
                        filteredList.add(recipe)
                    }
                }

                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                filteredRecipes = filterResults.values as List<RecipeModel>
                notifyDataSetChanged()
            }
        }
    }
}
