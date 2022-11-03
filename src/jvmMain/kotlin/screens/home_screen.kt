package screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
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
            /*
            Text(
                text = "Wordmes",
                fontWeight = FontWeight.SemiBold,
                fontSize = 48.sp,
                fontFamily = montserrat,
                modifier = Modifier.padding(top = animatedPadding.dp),
                color = theme.TextColor
            )
            */
        }
        Spacer(modifier = Modifier.height(40.dp))
        WText(
            text = "use full-screen mode for better experience",
            size = 16.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier.align(alignment = Alignment.BottomCenter).padding(bottom = 16.dp),
        )
        /*
        Text(
            text = "use full-screen mode for better experience",
            fontWeight = FontWeight.Light,
            fontSize = 16.sp,
            fontFamily = montserrat,
            modifier = Modifier.align(alignment = Alignment.BottomCenter).padding(bottom = 16.dp),
            color = theme.TextColor
        )
        */
    }
}