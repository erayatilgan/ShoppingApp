package com.info.shoppingapp.core.databases

import com.info.shoppingapp.R
import com.info.shoppingapp.domain.entities.Instruction

object InstructionFakeData {
    private const val firstBigText = "No more \nboring things\n"
    private const val secondBigText = "Exploring the \nfashion trends\n"
    private const val firstSmallText = "Picking up accessories from\npopular European brands."
    private const val secondSmallText = "We form an assortment that\nfollows fashion trends."


    val sliderList: List<Instruction>
        get() = listOf(
            Instruction(
                firstDescription = firstBigText,
                secondDescription = firstSmallText,
                image = R.drawable.instruction_first
            ),
            Instruction(
                firstDescription = secondBigText,
                secondDescription = secondSmallText,
                image = R.drawable.instruction_second
            ),
            Instruction(
                firstDescription = firstBigText,
                secondDescription = firstSmallText,
                image = R.drawable.instruction_first
            ),
        )
}