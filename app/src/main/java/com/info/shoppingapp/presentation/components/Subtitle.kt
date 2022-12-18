package com.info.shoppingapp.presentation.components

import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun Subtitle(title:String, modifier:Modifier = Modifier, style: TextStyle) {
    Text(
        text = title,
        textAlign = TextAlign.Start,
        style = style,
        modifier = Modifier.then(modifier)
    )
}