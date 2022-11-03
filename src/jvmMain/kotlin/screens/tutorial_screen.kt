package screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import fragments.InformationSpacer
import navigation.NavController

import fonts.montserrat
import fragments.WText
import theme


private val categories = listOf(
    "wordge",
    "sharkman",
    "wfw",
    "about"
)


@Composable
private fun AfterInfoSign(
    modifier: Modifier = Modifier,
    text: String,
) {
    InformationSpacer()
    Text(
        text = """
                ~~~~~
                $text
        """.trimIndent(),
        fontFamily = montserrat,
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Center,
        color = theme.SuccessfulTextColor,
        letterSpacing = 2.sp,
        modifier = modifier
    )
    InformationSpacer()
}

/**
 * Information about wordge composable
 */
@Composable
private fun InformationWordle(
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 120.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InformationSpacer(height = 32)
        Text(
            text = """
                In this game, at first, you pick length of the word you want to guess.
                Then, an input window appears and you start guessing. As long as your input string length is equal to
                the length you've just picked, the program shows you whether the letter is perfectly fit ( green ), 
                the letter is in the word, but the position is wrong ( yellow )
                or is absent in this specific word ( gray ). 
                You can always see your top-5 best guesses ( the closest ones ) if you're confused, just click the 
                button on the bottom of the screen!
            """.trimIndent(),
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = montserrat,
            textAlign = TextAlign.Center,
            color = theme.TextColor
        )
        AfterInfoSign(
            text = "Have fun playing!"
        )
        NavigateToGameButton(
            navController = navController,
            route = Screen.WordleScreen.name
        )
    }
}


/**
 * Information about hangman composable
 */
@Composable
private fun InformationSharkman(
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 120.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InformationSpacer(height = 32)
        Text(
            text = """
                In this game, at first, a random word with length from 5 to 12 inclusive is guessed by the computer.
                Your goal is to keep the cake from being overcooked. You can do it by guessing letters from the guessed
                word. If you got the wrong letter for 7 times, the cake gets overcooked and there's no way to turn that 
                back. So, you better do your best not to make the Birthday messed up!
            """.trimIndent(),
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = montserrat,
            textAlign = TextAlign.Center,
            color = theme.TextColor
        )
        AfterInfoSign(
            text = "Have fun playing!"
        )
        NavigateToGameButton(
            navController = navController,
            route = Screen.SharkmanScreen.name
        )
    }
}


/**
 * Information about wfw composable
 */
@Composable
private fun InformationWordsFromWord(
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 120.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InformationSpacer(height = 32)
        Text(
            text = """
                In this game, a specific word is being guessed. Your goal is to assemble new words, that contain ONLY 
                the letters present in the starting word ( mind the amount of letters ). Once you've assembled the 
                necessary amount of words, a level is considered as passed. There're 3 modes: Easy - you have to assemble
                5 words and you don't lose points for entering wrong words; Normal - 7 words and 1 loss point;
                Hard - 10 words and 2 loss points. Time for a challenge!
            """.trimIndent(),
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = montserrat,
            textAlign = TextAlign.Center,
            color = theme.TextColor
        )
        AfterInfoSign(
            text = "Have fun playing!"
        )
        NavigateToGameButton(
            navController = navController,
            route = Screen.WordsFromWordScreen.name
        )
    }
}


/**
 * Information about the app
 */
@Composable
private fun InformationAbout(){
    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 120.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        InformationSpacer(height = 32)
        Text(
            text = """
                This is an app, created as a way to spend some spare time with fun!
                If you want to tribute to the developers, we'd be grateful. Just scan the QR-code below and leave 
                a positive comment.
            """.trimIndent(),
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = montserrat,
            textAlign = TextAlign.Center,
            color = theme.TextColor
        )
        InformationSpacer(height = 24)
        Image(
            painter = painterResource(resourcePath = "images/others/frame.png"),
            contentDescription = "qr_code_rick_roll",
            modifier = Modifier.size(100.dp),
        )
        AfterInfoSign(
            text = "Thank You for your support!"
        )
    }
}


/**
 * Select topic/category button
 */
@Composable
private fun SelectCategoryButton(
    onClick: () -> Unit,
    isEnabled: Boolean,
    category: String
) {
    OutlinedButton(
        onClick = onClick,
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = theme.ButtonBackgroundColor,
            contentColor = theme.ButtonContentColor
        ),
        border = BorderStroke(width = 1.dp, color = theme.ButtonBorderColor)
    ) {
        Text(
            text = category,
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            fontFamily = montserrat
        )
    }
}


@Composable
private fun NavigateToGameButton(
    navController: NavController,
    route: String
) {
    OutlinedButton(
        onClick = { navController.navigate(route = route) },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = theme.ButtonBackgroundColor,
            contentColor = theme.ButtonContentColor
        ),
        border = BorderStroke(width = 1.dp, color = theme.ButtonBorderColor),
        modifier = Modifier.width(150.dp).height(45.dp)
    ) {
        Text(
            text = "Play!",
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = montserrat
        )
    }
}

/**
 * Screen to show information about different mini-games
 */
@Composable
fun TutorialScreen(
    navController: NavController
) {
    var isSelectedWordle by remember {
        mutableStateOf(false)
    }

    var isSelectedSharkman by remember {
        mutableStateOf(false)
    }

    var isSelectedWordsFromWord by remember {
        mutableStateOf(false)
    }

    var isSelectedAbout by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WText(
            text = "How to play?",
            size = 24.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(top = 8.dp)
        )
        Spacer(modifier = Modifier.height(124.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            categories.forEachIndexed { i, _ ->
                when(i) {
                    0 -> {
                        /*
                         * select wordle button
                         */
                        SelectCategoryButton(
                            onClick = {
                                isSelectedWordle = true; isSelectedSharkman = false
                                isSelectedWordsFromWord = false; isSelectedAbout = false
                            },
                            isEnabled = !isSelectedWordle,
                            category = categories[0]
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                    }
                    1 -> {
                        /*
                         * select Sharkman button
                         */
                        SelectCategoryButton(
                            onClick = {
                                isSelectedWordle = false; isSelectedSharkman = true
                                isSelectedWordsFromWord = false; isSelectedAbout = false
                            },
                            isEnabled = !isSelectedSharkman,
                            category = categories[1]
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                    }
                    2 -> {
                        /*
                         * select words from word button
                         */
                        SelectCategoryButton(
                            onClick = {
                                isSelectedWordle = false; isSelectedSharkman = false
                                isSelectedWordsFromWord = true; isSelectedAbout = false
                            },
                            isEnabled = !isSelectedWordsFromWord,
                            category = categories[2]
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                    }
                    3 -> {
                        /*
                         * select about button
                         */
                        SelectCategoryButton(
                            onClick = {
                                isSelectedWordle = false; isSelectedSharkman = false
                                isSelectedWordsFromWord = false; isSelectedAbout = true
                            },
                            isEnabled = !isSelectedAbout,
                            category = categories[3]
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        if(isSelectedWordle) {
            InformationWordle(navController = navController)
        }
        else if (isSelectedSharkman) {
            InformationSharkman(navController = navController)
        }
        else if (isSelectedWordsFromWord) {
            InformationWordsFromWord(navController = navController)
        }
        else if (isSelectedAbout) {
            InformationAbout()
        }
    }
}