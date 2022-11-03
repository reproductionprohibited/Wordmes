package theming

import androidx.compose.ui.graphics.Color

data class DarkTheme(
    val screenBackgroundColor: Color = Color.DarkGray.copy(alpha = 0.8f),
    val textColor: Color = Color(0xBB86FC),
    val buttonBackgroundColor: Color = Color(0x03DAC5).copy(alpha = 0.25f),
    val buttonContentColor: Color = Color(0x3700B3).copy(alpha = 0.75f),
    val buttonBorderColor: Color = Color(0x3700B3),
    val navigationRailColor: Color = Color.DarkGray,
    val navigationRailItemBackgroundColor: Color = Color.DarkGray,
    val navigationRailItemSelectedContentColor: Color = Color(0xFF7597),
    val navigationRailItemUnselectedContentColor: Color = Color.DarkGray.copy(alpha = 0.5f)
)