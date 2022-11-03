package fonts

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font

val montserrat: FontFamily = FontFamily(
    Font(
        resource = "fonts/MontserratAlternates_Regular.ttf",
        weight = FontWeight.W400,
        style = FontStyle.Normal
    ),
    Font(
        resource = "fonts/MontserratAlternates_Bold.ttf",
        weight = FontWeight.Bold,
        style = FontStyle.Normal
    ),
    Font(
        resource = "fonts/MontserratAlternates_SemiBold.ttf",
        weight = FontWeight.SemiBold,
        style = FontStyle.Normal
    ),
    Font(
        resource = "fonts/MontserratAlternates_Light.ttf",
        weight = FontWeight.Light,
        style = FontStyle.Normal
    ),
    Font(
        resource = "fonts/MontserratAlternates_Thin.ttf",
        weight = FontWeight.Thin,
        style = FontStyle.Normal
    ),
    Font(
        resource = "fonts/MontserratAlternates_Medium.ttf",
        weight = FontWeight.Medium,
        style = FontStyle.Normal
    ),
)