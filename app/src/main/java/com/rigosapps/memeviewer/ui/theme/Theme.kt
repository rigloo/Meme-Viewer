package com.rigosapps.memeviewer.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = LightPurple,
    primaryVariant = LightPurple,
    secondary = secondaryColor,
    background = DarkPurple,
)

private val LightColorPalette = lightColors(
    primary = LightPurple,
    primaryVariant = LightPurple,
    secondary = secondaryColor,


    background = DarkPurple,
//    surface = Color.White,
//    onPrimary = Color.White,
//    onSecondary = Color.Black,
//    onBackground = Color.Black,
//    onSurface = Color.Black,

)

@Composable
fun QuoteAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

    val systemUiController = rememberSystemUiController()
    val colors = if (darkTheme) {
        systemUiController.setSystemBarsColor(
           DarkColor
        )
        DarkColorPalette
    } else {
        systemUiController.setSystemBarsColor(
            DarkColor
        )
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}