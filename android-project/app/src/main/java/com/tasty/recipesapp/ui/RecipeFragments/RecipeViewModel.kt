package com.tasty.recipesapp.ui.RecipeFragments

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.tasty.recipesapp.data.dto.InstructionDTO
import com.tasty.recipesapp.data.model.InstructionModel
import com.tasty.recipesapp.providers.RepositoryProvider

class RecipeViewModel : ViewModel(){

    private val _instructions = MutableLiveData<List<InstructionModel>>()
    val instructionModels: MutableLiveData<List<InstructionModel>> = _instructions



    fun loadInstructionData(context: Context) {
        val data = RepositoryProvider.instructionsRepository.getAll(context)
        instructionModels.value = data
    }
}