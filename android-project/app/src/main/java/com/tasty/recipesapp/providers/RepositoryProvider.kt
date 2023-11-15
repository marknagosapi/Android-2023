package com.tasty.recipesapp.providers

import com.tasty.recipesapp.data.repositories.InstructionRepository

object RepositoryProvider {

    val instructionsRepository: InstructionRepository = InstructionRepository()

}
