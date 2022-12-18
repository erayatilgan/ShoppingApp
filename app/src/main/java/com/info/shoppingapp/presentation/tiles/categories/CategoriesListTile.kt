package com.info.shoppingapp.presentation.tiles.categories

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.info.shoppingapp.domain.entities.Category

@Composable
fun CategoriesListTile(category: Category, onTap: (() -> Unit) = { }) {
    Surface(
        color = colors.surface,
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .fillMaxSize()
            .height(107.dp)
            .clickable { onTap() }
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 15.dp)

        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                verticalAlignment = Alignment.CenterVertically,
            )
            {
                Surface(
                    modifier = Modifier
                        .size(63.dp)
                        .clip(RoundedCornerShape(15.dp)),
                    color = colors.onSurface
                )
                {
                    Image(
                        modifier = Modifier.padding(17.dp),
                        painter = painterResource(id = category.image),
                        contentDescription = null,
                    )
                }
                Text(
                    text = category.category,
                    color = colors.primary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Column(Modifier.fillMaxWidth()) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                    tint = colors.primaryVariant,
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
    }
}