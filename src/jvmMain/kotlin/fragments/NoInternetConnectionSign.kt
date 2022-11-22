package fragments

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import fonts.montserrat
import theme

/**
 * NoInternetConnectionSign - Text that tells the user that an error occurred because of his poor internet connection
 */
@Composable
fun NoInternetConnectionSign(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 70.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = """
                An unforeseen error occurred due to your poor Internet Connection.
                Check your Internet Connection and try again later.
            """.trimIndent(),
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = montserrat,
            color = theme.WrongTextColor,
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline
        )
    }
}