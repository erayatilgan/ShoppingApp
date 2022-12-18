package com.info.shoppingapp.presentation.ui.theme
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Primary,
    onPrimary = OnPrimary,
    primaryVariant = PrimaryVariant,
    background = Background,
    surface = Surface,
    onSurface = OnSurface
)

private val LightColorPalette = lightColors(
    primary = Primary,
    onPrimary = OnPrimary,
    primaryVariant = PrimaryVariant,
    background = Background,
    surface = Surface,
    onSurface = OnSurface
)

@Composable
fun ShoppingAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

