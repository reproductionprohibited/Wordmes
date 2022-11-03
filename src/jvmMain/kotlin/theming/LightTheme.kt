package theming

import androidx.compose.ui.graphics.Color

data class LightTheme (
    val screenBackgroundColor: Color = Color.White,
    val textColor: Color = Color.Black,
    val buttonBackgroundColor: Color = Color.LightGray.copy(alpha = 0.25f),
    val buttonContentColor: Color = Color.DarkGray.copy(alpha = 0.75f),
    val buttonBorderColor: Color = Color.DarkGray,
    val navigationRailColor: Color = Color.White,
    val navigationRailItemBackgroundColor: Color = Color.White,
    val navigationRailItemSelectedContentColor: Color = Color.DarkGray.copy(alpha = 0.9f),
    val navigationRailItemUnselectedContentColor: Color = Color.LightGray.copy(alpha = 0.4f)
)