package screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fonts.montserrat
import fragments.InformationSpacer
import fragments.WText
import theme


private data class Creator(
    val name: String = "",
    val surname: String = "Unknown",
    val contact: String = "No contact info",
    val photoPath: String,
    val field: String = "programmer"
)


private val creators: List<Creator> = listOf(
    Creator(
        name = "Stanislav",
        surname = "Tomulets",
        contact = "stantom16@gmail.com",
        photoPath = "images/creators/creator1.jpg"
    )
)


@Composable
private fun CreatorCard(
    creator: Creator,
) {
    Card(
        modifier = Modifier.width(400.dp).height(120.dp),
        shape = RoundedCornerShape(4.dp),
        border = BorderStroke(width = 2.dp, color = theme.CardBorderColor),
        backgroundColor = theme.CardBackgroundColor,
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(14.dp),
        ) {
            Column(
                modifier = Modifier.weight(3f),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = creator.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = montserrat,
                    color = theme.TextColor
                )
                Text(
                    text = creator.surname,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = montserrat,
                    color = theme.TextColor
                )
                Box(
                    contentAlignment = Alignment.BottomStart,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    SelectionContainer {
                        Text(
                            text = creator.contact,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = montserrat,
                            color = theme.TextColor
                        )
                    }
                }
            }
            Image(
                painter = painterResource(resourcePath = creator.photoPath),
                contentDescription = null,
                modifier = Modifier.fillMaxHeight().width(70.dp).align(Alignment.CenterVertically).weight(1f)
            )
        }
    }
}


@Composable
private fun CreatorsField() {
    Column(
        modifier = Modifier.fillMaxSize().padding(top = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        creators.forEachIndexed { index: Int, creator: Creator ->
            CreatorCard(creator = creator)
            if (index != creators.lastIndex) InformationSpacer(height = 48)
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = "Images by FlatIcon",
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    fontFamily = montserrat,
                    color = theme.TextColor
                )
                Spacer(modifier = Modifier.height(8.dp))
                SelectionContainer{
                    Text(
                        text = "https://www.flaticon.com",
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        fontFamily = montserrat,
                        color = theme.TextColor
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
            }

        }
    }
}


@Composable
fun CreatorsScreen(){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        WText(
            text = "Created by",
            size = 24.sp,
            modifier = Modifier.padding(8.dp),
            fontWeight = FontWeight.SemiBold,
        )
        CreatorsField()
    }
}