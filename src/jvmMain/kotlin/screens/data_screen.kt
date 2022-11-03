package screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import kotlin.math.round

import fragments.InformationSpacer
import fragments.WText

import theme

@Composable
private fun GameRecord(
    game: String,
    tries: Int,
    wins: Int
) {
    val winPercentage = if (tries > 0) round(wins / tries.toFloat() * 100 / 100) else 0f
    Card(
        modifier = Modifier.width(300.dp).height(200.dp),
        shape = RoundedCornerShape(20),
        border = BorderStroke(width = 1.dp, color = theme.CardBorderColor),
        backgroundColor = theme.CardBackgroundColor
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(12.dp)
        ) {
            WText(
                text = game,
                size = 12.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Italic
            )
            InformationSpacer(height = 16)
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                WText(
                    text = "Wins:\n${wins}",
                    size = 8.sp,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Start
                )
                WText(
                    text = "Total Played:\n${tries}",
                    size = 8.sp,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center
                )
                WText(
                    text = "Win percentage:\n${winPercentage}",
                    size = 8.sp,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.End
                )
            }
        }
    }
}

@Composable
fun DataScreen(
    triesList: List<Int> = listOf(10, 10, 10),
    winList: List<Int> = listOf(5, 4, 8)
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WText(
            text = "User Record",
            size = 16.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(top = 8.dp)
        )
        InformationSpacer(height = 72)
        Row(
            modifier = Modifier.padding(start = 160.dp, end = 80.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            GameRecord(
                game = "Wordle",
                tries = triesList[0],
                wins = winList[0],
            )
            GameRecord(
                game = "Sharkman",
                tries = triesList[1],
                wins = winList[1],
            )
            GameRecord(
                game = "Words from Word",
                tries = triesList[2],
                wins = winList[2],
            )
        }
    }
}