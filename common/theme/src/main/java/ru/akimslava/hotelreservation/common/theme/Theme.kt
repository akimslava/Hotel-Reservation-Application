package ru.akimslava.hotelreservation.common.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorScheme = darkColorScheme(
    primary = Gray,
    secondary = Gray,
    tertiary = Gray,
)

private val LightColorScheme = lightColorScheme(
    primary = White,
    onPrimary = Black,
    onPrimaryContainer = White,
    secondary = White,
    onSecondary = Black,
    tertiary = White,
    onTertiary = Black,
    background = White,
    onBackground = Black,
    surface = White,
    onSurface = Black,
)

@Composable
fun HotelReservationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    val systemUiController = rememberSystemUiController()
    if (!view.isInEditMode) {
        SideEffect {
            systemUiController.setStatusBarColor(
                color = White,
                darkIcons = !darkTheme,
            )
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}