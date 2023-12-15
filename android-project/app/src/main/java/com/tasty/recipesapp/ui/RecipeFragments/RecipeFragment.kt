package com.tasty.recipesapp.ui.RecipeFragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.PopupMenu
import android.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tasty.recipesapp.R
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

        val fabMenu: FloatingActionButton = binding.fabMenu
        fabMenu.setOnClickListener { showPopupMenu(fabMenu) }

        val editTextSearch: EditText = binding.editTextSearch
        val btnSearch: Button = binding.btnSearch

        btnSearch.setOnClickListener {
            val query = editTextSearch.text.toString()
            adapter?.search(query)
        }

    }
    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(requireContext(), view)
        popupMenu.menuInflater.inflate(R.menu.menu_recipe_sort, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_search -> {
                    // Handle search action
                    true
                }
                R.id.action_sort -> {
                    // Handle filter action
                    true
                }
                R.id.menu_filter -> {
                    // Handle sort action
                    true
                }
                else -> false
            }
        }

        popupMenu.show()
    }
}