package com.info.shoppingapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun IconButtonX(
    imageVector: ImageVector,
    backgroundColor: Color? = null,
    foregroundColor: Color? = null,
    onTap: () -> Unit = {}
) {
    IconButton(
        modifier = Modifier.background(
            backgroundColor ?: colors.surface,
            shape = CircleShape
        ), onClick = onTap
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            tint = foregroundColor ?: colors.onBackground,
            modifier = Modifier.size(20.dp)
        )
    }
}