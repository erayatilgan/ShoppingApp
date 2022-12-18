package com.info.shoppingapp.domain.entities

import java.io.Serializable

data class Instruction(
    val firstDescription: String,
    val secondDescription: String,
    val image: Int,
): Serializable