package screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import backend.api.getRandomWord

import kotlin.random.Random.Default.nextInt

import fonts.montserrat
import fragments.NoInternetConnectionSign
import theme
import kotlinx.coroutines.*


/**
 * Box to show specific letter ( used in Hangman overall Player progress )
 */
@Composable
private fun LetterBox(
    letter: Char,
) {
    Box(
        modifier = Modifier.size(40.dp).background(color = theme.ScreenBackgroundColor),
        contentAlignment = Alignment.BottomCenter,
    ) {
        Text(
            text = letter.toString(),
            fontSize = (if(letter == '_') 40 else 18).sp,
            fontWeight = FontWeight.Medium,
            fontFamily = montserrat,
            color = theme.TextColor
        )
    }
}

@Composable
private fun SharkmanImage(
    path: String,
    contentDescription: String,
    modifier: Modifier = Modifier,
    imgWidth: Int = 600,
    imgHeight: Int = 275
) {
    Image(
        painter = painterResource(resourcePath = path),
        contentDescription = contentDescription,
        modifier = modifier.width(imgWidth.dp).height(imgHeight.dp).clip(shape = RoundedCornerShape(25))
    )
}

/**
 * Function which manages the in-game progress
 */
@Composable
private fun SharkmanGame(
    word: String
) {
    val guessedLetters: MutableSet<String> by remember {
        mutableStateOf(mutableSetOf())
    }

    val mistakeLetters: MutableSet<String> by remember {
        mutableStateOf(mutableSetOf())
    }

    var letterInput: String by remember {
        mutableStateOf("")
    }

    var mistakes: Int by remember {
        mutableStateOf(0)
    }

    var isInputEnabled: Boolean by remember {
        mutableStateOf(true)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = letterInput,
                onValueChange = { input ->
                    letterInput = input.filter { it.isLetter() }.take(1).lowercase()
                    println("LETTER INPUT: $letterInput")
                },
                enabled = isInputEnabled,
                placeholder = {
                    Text(
                        text = "letter",
                        fontFamily = montserrat
                    )
                },
                modifier = Modifier.width(100.dp),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = theme.TextColor,
                    backgroundColor = theme.TextFieldColor,
                    focusedIndicatorColor = theme.TextFieldColor,
                    focusedLabelColor = theme.TextColor
                )
            )
            Spacer(modifier = Modifier.width(12.dp))
            OutlinedButton(
                onClick = {
                    if(letterInput.isNotEmpty() && !guessedLetters.contains(letterInput)) {
                        if(letterInput in word) {
                            guessedLetters.add(letterInput)
                            if(guessedLetters.size == word.toSet().size) {
                                println("GAME END. WORD IS GUESSED")
                                isInputEnabled = false
                            }
                        } else if(!mistakeLetters.contains(letterInput)) {
                            mistakes++
                        }
                        println("Guessed letters: ${guessedLetters.joinToString(", ")}")
                    }
                    letterInput = ""
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = theme.ButtonBackgroundColor,
                    contentColor = theme.ButtonContentColor,
                ),
                border = BorderStroke(width = 1.dp, color = theme.ButtonBorderColor),
                enabled = isInputEnabled
            ) {
                Text(
                    text = "submit",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = montserrat,
                    color = theme.TextColor
                )
            }
        }
        Spacer(modifier = Modifier.height(72.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            word.forEach {
                if(guessedLetters.contains(it.toString())) {
                    LetterBox(letter = it)
                } else {
                    LetterBox(letter = '_')
                }
            }
        }
        Spacer(modifier = Modifier.height(72.dp))
        if(mistakes in 1 .. 7 && isInputEnabled) {
            SharkmanImage(
                path = "images/shark/shark$mistakes.png",
                contentDescription = "sharkman_image_$mistakes"
            )
        }
        if(mistakes == 8) {
            SharkmanImage(
                path = "images/shark/shark_player_defeat.png",
                contentDescription = "sharkman_image_player_defeat"
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Oh no! The guy died. Better luck next time",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = montserrat,
                textAlign = TextAlign.Center,
                color = theme.WrongTextColor
            )
            isInputEnabled = false
        }
        if(!isInputEnabled && mistakes < 8) {
            SharkmanImage(
                path = "images/shark/shark_player_win.png",
                contentDescription = "sharkman_image_player_win",
                imgWidth = 250,
                imgHeight = 250,
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "You saved the guy. The shark's sad and will come for revenge.",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = montserrat,
                textAlign = TextAlign.Center,
                color = theme.CorrectTextColor
            )
        }
    }
}


/**
 * Screen with the game of Hangman.
 * Keeps values of wordLength and word itself
 */
@Composable
fun SharkmanScreen(){
    // length = [5, 12]
    var word: String? by remember {
        mutableStateOf("")
    }
    var hasStarted by remember {
        mutableStateOf(false)
    }

    var hasNoInternetConnection by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Sh_rkm_n",
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = montserrat,
            modifier = Modifier.padding(top = 8.dp),
            color = theme.TextColor
        )
        if(!hasStarted) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                OutlinedButton(
                    onClick = {
                        hasStarted = true
                        runBlocking {
                            word = getRandomWord(wordLength = nextInt(5, 13))
                        }
                        if (word.isNullOrBlank()) { hasNoInternetConnection = true; println(hasNoInternetConnection) }
                        println(word)
                    },
                    //onClick = { hasStarted = true; word = "hangman" },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = theme.ButtonBackgroundColor,
                        contentColor = theme.ButtonContentColor,
                    ),
                    border = BorderStroke(width = 1.dp, color = theme.ButtonBorderColor),
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
        } else if (!hasNoInternetConnection && hasStarted) {
            Spacer(modifier = Modifier.height(120.dp))
            SharkmanGame(
                word = word!!
            )
        } else if (hasNoInternetConnection) {
            NoInternetConnectionSign()
        }
    }
}