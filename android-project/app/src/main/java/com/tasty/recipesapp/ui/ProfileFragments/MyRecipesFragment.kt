package com.tasty.recipesapp.ui.ProfileFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tasty.recipesapp.databinding.FragmentMyRecipesBinding
import com.tasty.recipesapp.ui.RecipeFragments.NewRecipeAdapter
import kotlinx.coroutines.launch


class MyRecipesFragment : Fragment() {

    private lateinit var binding: FragmentMyRecipesBinding
    private lateinit var profileViewModel: MyRecipesViewModel
    private lateinit var recipeAdapter: NewRecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentMyRecipesBinding.inflate(inflater, container, false)
        profileViewModel = ViewModelProvider(this).get(MyRecipesViewModel::class.java)
        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)



        recipeAdapter = NewRecipeAdapter(emptyList(),
            { recipe -> profileViewModel.confirmDeleteRecipe(recipe,requireContext(),this) },
            { recipe -> profileViewModel.onRecipeClicked(recipe, this) }
        )

        recyclerView.adapter = recipeAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            profileViewModel.getAllRecipes()
        }

        profileViewModel.allRecipes.observe(viewLifecycleOwner) { recipes ->
            recipeAdapter.updateData(recipes)
        }
        return binding.root
    }
}