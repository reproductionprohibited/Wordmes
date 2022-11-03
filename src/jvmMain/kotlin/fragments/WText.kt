package fragments


import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

import fonts.montserrat
import theme

@Composable
fun WText(
    text: String,
    size: TextUnit = 16.sp,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight,
    fontStyle: FontStyle = FontStyle.Normal,
    textAlign: TextAlign = TextAlign.Center,
    textDecoration: TextDecoration = TextDecoration.None
) {
    Text(
        text = text,
        fontSize = size,
        fontWeight = fontWeight,
        fontFamily = montserrat,
        modifier = modifier,
        textAlign = textAlign,
        textDecoration = textDecoration,
        color = theme.TextColor
    )
}