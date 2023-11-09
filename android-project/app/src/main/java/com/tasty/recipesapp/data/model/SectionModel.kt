package com.tasty.recipesapp.data.model

import com.tasty.recipesapp.data.dto.ComponentDTO

data class SectionModel (
    val components: List<ComponentDTO>,
    val name: String?,
    val position: Int
)
