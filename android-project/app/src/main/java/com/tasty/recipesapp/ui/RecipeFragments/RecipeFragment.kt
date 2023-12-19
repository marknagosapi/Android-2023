package com.tasty.recipesapp.ui.RecipeFragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tasty.recipesapp.R
import com.tasty.recipesapp.data.entities.RecipeEntity
import com.tasty.recipesapp.data.model.RecipeModel
import com.tasty.recipesapp.databinding.FragmentRecipeBinding
import com.tasty.recipesapp.providers.RepositoryProvider
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject


class RecipeFragment : Fragment(), OnAddToFavoritesClickListener{

    private lateinit var binding: FragmentRecipeBinding
    private lateinit var adapter: RecipeAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("SCREENS", "Opened Recipe Screen!")
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRecipeBinding.inflate(inflater, container, false);
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this)[RecipeViewModel::class.java]
        val recipes = viewModel.getAllRecipesFromApi()


        val recyclerView: RecyclerView = binding.recyclerView;
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val adapter = recipes?.let { RecipeAdapter(it,this) }

        recyclerView.adapter = adapter

        val fabMenu: FloatingActionButton = binding.fabMenu
        fabMenu.setOnClickListener {
            if (adapter != null) {
                showPopupMenu(fabMenu, adapter)
            }
        }

        val editTextSearch: EditText = binding.editTextSearch
        val btnSearch: Button = binding.btnSearch

        btnSearch.setOnClickListener {
            val query = editTextSearch.text.toString()
            adapter?.search(query)
        }

    }
    private fun showPopupMenu(view: View, adapter: RecipeAdapter) {
        val popupMenu = PopupMenu(requireContext(), view)

        popupMenu.menuInflater.inflate(R.menu.menu_recipe_sort, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_sort_asc -> {
                    adapter?.sort(true)
                    true
                }
                R.id.action_sort_desc -> {
                    adapter.sort(false)
                    true
                }
                else -> false
            }
        }

        popupMenu.show()
    }

    private suspend fun saveRecipe(recipe: RecipeModel) : Boolean {

        val title = recipe.name
        val description = recipe.description
        var pictureURL = recipe.thumbnailUrl
        var videoURL = recipe.videoUrl


        val ingredients = mutableListOf<String>()
        for (i in 0 until recipe.sections.size) {
            for(j in 0 until recipe.sections[i].components.size){
                ingredients.add(recipe.sections[i].components[j].ingredient.name)
            }
        }

        val instructions = mutableListOf<String>()
        for (i in 0 until recipe.instructions.size) {
            instructions.add(recipe.instructions[i].displayText)
        }

        if(pictureURL == null ) {
            pictureURL = "";
        }

        val favRecipe =
                createJsonFromInputs(
                    title, description,
                    pictureURL, "FAV",ingredients, instructions
                )

        val recipeEntity = RecipeEntity(
            json = favRecipe
        )

        RepositoryProvider.recipeRepository.insertRecipe(recipeEntity)
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

    override fun onAddToFavoritesClick(recipe: RecipeModel) {
        lifecycleScope.launch {
           saveRecipe(recipe)
        }
    }

}