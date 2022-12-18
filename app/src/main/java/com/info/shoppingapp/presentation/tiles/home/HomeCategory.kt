package com.info.shoppingapp.presentation.tiles.home

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.info.shoppingapp.domain.entities.Category
import com.info.shoppingapp.presentation.screens.category.CategoriesPage

@Composable
fun HomeCategory(category: Category) {
    Surface(color = colors.background) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val context = LocalContext.current
            IconButton(
                onClick = {
                    context.startActivity(
                        Intent(context, CategoriesPage::class.java)
                    )
                },
                modifier = Modifier
                    .clip(CircleShape)
                    .size(60.dp)
                    .background(
                        colors.surface,
                    )
            ) {
                Image(
                    painter = painterResource(id = category.image),
                    contentDescription = null,
                    modifier = Modifier.padding(17.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = category.category, color = Color.Black, fontWeight = FontWeight.SemiBold)
        }
    }
}