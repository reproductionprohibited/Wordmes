package theming

import androidx.compose.ui.graphics.Color

enum class Theme(
    val ScreenBackgroundColor: Color,
    val TextColor: Color,
    val WrongTextColor: Color,
    val CorrectTextColor: Color,
    val SuccessfulTextColor: Color,
    val ButtonBackgroundColor: Color,
    val ButtonContentColor: Color,
    val ButtonBorderColor: Color,
    val NavigationRailColor: Color,
    val NavigationRailItemBackgroundColor: Color,
    val NavigationRailItemSelectedContentColor: Color,
    val NavigationRailItemUnselectedContentColor: Color,
    val CardBackgroundColor: Color,
    val CardBorderColor: Color,
    val TextFieldColor: Color,
    val ImageBorderStrokeColor: Color,
) {
    LightTheme(
        ScreenBackgroundColor = Color.White,
        TextColor = Color.Black,
        WrongTextColor = Color(0xdc143c).copy(alpha = 0.6f),
        CorrectTextColor = Color(0x245c2a).copy(alpha = 0.8f),
        //CorrectTextColor = Color.Green.copy(alpha = 0.7f),
        SuccessfulTextColor = Color.Magenta.copy(alpha = 0.9f),
        ButtonBackgroundColor = Color.LightGray.copy(alpha = 0.25f),
        ButtonContentColor = Color.DarkGray.copy(alpha = 0.75f),
        ButtonBorderColor = Color.DarkGray,
        NavigationRailColor = Color.White,
        NavigationRailItemBackgroundColor = Color.White,
        NavigationRailItemSelectedContentColor = Color.DarkGray.copy(alpha = 0.9f),
        NavigationRailItemUnselectedContentColor = Color.LightGray.copy(alpha = 0.4f),
        CardBackgroundColor = Color.LightGray.copy(alpha = 0.4f),
        CardBorderColor = Color.DarkGray,
        TextFieldColor = Color.LightGray.copy(alpha = 0.4f),
        ImageBorderStrokeColor = Color.LightGray.copy(alpha = 0.6f)
    ),
    /*
    DarkTheme(
        ScreenBackgroundColor = Color.DarkGray.copy(alpha = 0.8f),
        TextColor = Color.White.copy(alpha = 0.8f),
        ButtonBackgroundColor = Color(0x03DAC5).copy(alpha = 0.25f),
        ButtonContentColor = Color.White.copy(alpha = 0.8f),
        ButtonBorderColor = Color.White,
        NavigationRailColor = Color.LightGray,
        NavigationRailItemBackgroundColor = Color.LightGray,
        NavigationRailItemSelectedContentColor = Color.DarkGray,
        NavigationRailItemUnselectedContentColor = Color.DarkGray.copy(alpha = 0.5f),
        CardBackgroundColor = Color(0x03DAC5).copy(alpha = 0.25f),
        CardBorderColor = Color(0x3700B3),
        TextFieldColor = Color.DarkGray.copy(alpha = 0.8f),
    )
    */
}