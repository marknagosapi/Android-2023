package com.tasty.recipesapp.data.repositories

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tasty.recipesapp.data.dto.InstructionDTO

import com.tasty.recipesapp.data.model.InstructionModel
import com.tasty.recipesapp.data.model.InstructionTime
import org.json.JSONObject
import java.io.IOException

class InstructionRepository : IGenericRepository<InstructionDTO, InstructionModel> {
    override fun InstructionDTO.toModel(): InstructionModel {
        return InstructionModel(
            id = this.id,
            displayText = this.displayText,
            time = InstructionTime(this.startTime, this.endTime)
        )
    }

    override fun List<InstructionDTO>.toModelList(): List<InstructionModel> {
        return this.map { it.toModel() }
    }


}