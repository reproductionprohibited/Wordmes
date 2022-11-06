package screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import fonts.montserrat
import fragments.WText
import theme


@Composable
fun HomeScreen(){
    val infiniteTransition = rememberInfiniteTransition()
    val animatedPadding by infiniteTransition.animateFloat(
        initialValue = 28f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    Box(
        modifier  = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WText(
                text = "Wordmes",
                size = 48.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = animatedPadding.dp)
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WText(
                text = "use full-screen mode for better experience",
                size = 16.sp,
                fontWeight = FontWeight.Light
            )
            Spacer(modifier = Modifier.height(16.dp))
            WText(
                text = "Images by FlatIcon",
                size = 14.sp,
                fontWeight = FontWeight.Normal
            )
            Spacer(modifier = Modifier.height(8.dp))
            SelectionContainer {
                WText(
                    text = "https://www.flaticon.com",
                    size = 12.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }
        }
    }
}