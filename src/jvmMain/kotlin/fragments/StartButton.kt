package fragments

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import fonts.montserrat
import theme

@Composable
fun StartButton(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        OutlinedButton(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = theme.ButtonBackgroundColor,
                contentColor = theme.ButtonContentColor
            ),
            border = BorderStroke(
                width=1.dp,
                color = theme.ButtonBorderColor
            ),
            modifier = Modifier.width(120.dp).height(50.dp)
        ) {
            Text(
                text = "Start!",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = montserrat,
                textAlign = TextAlign.Center,
                color = theme.TextColor
            )
        }
    }
}