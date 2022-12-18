package com.info.shoppingapp.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextButtonX(
    modifier: Modifier = Modifier,
    backgroundColor: Color? = null,
    foregroundColor: Color? = null,
    text: String,
    onTap: () -> Unit = {},
) {
    Button(
        onClick = onTap,
        elevation = ButtonDefaults.elevation(0.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor ?: colors.primary),
        modifier = Modifier.then(
            modifier
                .clip(RoundedCornerShape(12.dp))
                .size(width = 120.dp, height = 60.dp)
        ),
    ) {
        Text(
            text = text,
            color = foregroundColor ?: Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
        )
    }
}