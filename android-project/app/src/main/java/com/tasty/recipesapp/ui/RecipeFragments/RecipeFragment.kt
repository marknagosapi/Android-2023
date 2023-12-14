package com.tasty.recipesapp.ui.RecipeFragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tasty.recipesapp.databinding.FragmentRecipeBinding


class RecipeFragment : Fragment() {

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
        Log.d("RR", "onViewCreated: "+ recipes)

        val recyclerView: RecyclerView = binding.recyclerView;
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val adapter = recipes?.let { RecipeAdapter(it) }
        recyclerView.adapter = adapter

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                val searchItem = menu.findItem(com.tasty.recipesapp.R.id.action_search)
                val searchView = searchItem.actionView as SearchView
                menuInflater.inflate(com.tasty.recipesapp.R.menu.menu_recipe_sort, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu selection
                return when (menuItem.itemId) {
                    com.tasty.recipesapp.R.id.action_search -> {
                        Log.d("alma", "onMenuItemSelected: Search")
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

    }
}