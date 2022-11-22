package screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.runBlocking

import fragments.StartButton
import fragments.NoInternetConnectionSign

import backend.api.getRandomWord

import fonts.montserrat
import fragments.WText
import theme


/**
 * Function to calculate points to sort them. Used in the `Attempts` list
 *      inputWord: the word the user inputs
 *      answerWord: the word that is the answer
 */
private fun points(
    inputWord: String,
    answerWord: String
): Int
{
    var pts = 0
    for(i in inputWord.indices) {
        if(inputWord[i] == answerWord[i]) pts += 2
        else if (inputWord[i] in answerWord) pts += 1
    }
    return pts
}


/**
 * Information Dialog, that pops up on top of the screen
 *      titleText: title of the dialog
 *      infoText: main text of the dialog
 *      onClick: function that defines what happens when you click anywhere on the screen except for the dialog
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun InformationAlertDialog(
    titleText: String,
    infoText: String,
    onClick: () -> Unit
) {
    AlertDialog(
        modifier = Modifier.width(350.dp).height(200.dp),
        onDismissRequest = onClick,
        title = {
            Text(
                text = titleText,
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = montserrat
            )
        },
        text = {
            Text(
                text = infoText,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = montserrat
            )
        },
        confirmButton = {},
    )
}


/**
 * Button to select word length
 *      onClick: function that returns the length of the guessed word
 *      length: button content text
 *      isEnabled: true - if the
 */
@Composable
private fun SelectWordLengthButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    length: Int,
    isEnabled: Boolean
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = theme.ButtonBackgroundColor,
            contentColor = theme.ButtonContentColor
        ),
        border = BorderStroke(
            width=1.dp,
            color = theme.ButtonBorderColor
        )
    ) {
        Text(
            text = length.toString(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            fontFamily = montserrat,
            color = theme.TextColor
        )
    }
}


/**
 * Row with buttons that select word length for Wordge game
 *      onClickList: List with onClick functions for different buttons
 *      isEnabled: Whether the game started ( false ) or the user hasn't chosen the game mode ( true )
 */
@Composable
private fun SelectWordLengthButtonRow(
    modifier: Modifier = Modifier,
    onClickList: List<() -> Unit>,
    isEnabled: Boolean
) {
    Row(
        modifier = modifier
            .width(500.dp)
            .padding(horizontal = 120.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for(i in 0..2) {
            SelectWordLengthButton(
                onClick = onClickList[i],
                length = i + 5,
                isEnabled = isEnabled
            )
        }
    }
}


/**
 * LetterBox - a box of letter, indicating the correctness of position of specific letter given.
 *      inputLetter: content tof the box
 *      backgroundColor: the color
 */
@Composable
private fun LetterBox(
    inputLetter: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(50.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = backgroundColor),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = inputLetter,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = montserrat,
            color = theme.TextColor
        )
    }
}


/**
 * Guess Row - a row of letters, indicating how a specific letter in the input corresponds to the
 * correct letters in the answer word. Appears when length of input is equal to the length of the answer word
 *      inputWord: user input
 *      answerWord: guessed word
 */
@Composable
private fun GuessRow(
    inputWord: String,
    answerWord: String,
    modifier: Modifier = Modifier
) {
    var backgroundColor: Color by remember {
        mutableStateOf(Color.LightGray.copy(alpha = 0.65f))
    }
    Row(
        modifier = modifier.fillMaxWidth().padding(horizontal = 250.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for(i in inputWord.indices) {
            backgroundColor =
                if(inputWord[i] == answerWord[i]) {
                    Color.Green.copy(alpha = 0.4f)
                } else if (inputWord[i] in answerWord) {
                    Color.Yellow.copy(alpha = 0.4f)
                } else {
                    Color.LightGray.copy(alpha = 0.65f)
                }
            LetterBox(
                inputLetter = inputWord[i].toString(),
                backgroundColor = backgroundColor
            )
            if(i != inputWord.lastIndex) Spacer(modifier = Modifier.width(8.dp))
        }
    }
}


/**
 * WordleGameField - the game field. All the gameplay is in this function
 *      wordAnswer: guessed word
 *      wordLength: length of the word chosen by the user
 */
@Composable
private fun WordleGameField(
    modifier: Modifier = Modifier,
    wordAnswer: String,
    wordLength: Int
) {
    var wordInput: String by remember {
        mutableStateOf("")
    }

    var isInputEnabled: Boolean by remember {
        mutableStateOf(true)
    }

    var isTextFieldFull: Boolean by remember {
        mutableStateOf(false)
    }

    val guessAttempts: MutableList<String> by remember {
        mutableStateOf(mutableListOf())
    }

    var hasLost: Boolean by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = wordInput,
            onValueChange = { input ->
                wordInput = input.filter { it.isLetter() }.take(wordLength).lowercase()
                if ((wordInput.length == wordLength) && isTextFieldFull && (wordInput !in guessAttempts)) {
                    isTextFieldFull = false
                    guessAttempts.add(wordInput)
                }
                else { isTextFieldFull = true }
                if (guessAttempts.size >= 6) {
                    isInputEnabled = false
                    hasLost = wordAnswer !in guessAttempts
                }
            },
            enabled = isInputEnabled,
            colors = TextFieldDefaults.textFieldColors(
                textColor = theme.TextColor,
                backgroundColor = theme.TextFieldColor,
                focusedIndicatorColor = theme.TextFieldColor,
                focusedLabelColor = theme.TextColor
            )
        )

        Spacer(modifier = Modifier.height(16.dp))
        Divider(modifier = Modifier.width(72.dp).height(2.dp), color = theme.CardBorderColor)
        Spacer(modifier = Modifier.height(16.dp))

        for(guess in guessAttempts) {
            GuessRow(
                inputWord = guess,
                answerWord = wordAnswer
            )
            Spacer(modifier = Modifier.height(12.dp))
        }

        if(wordInput == wordAnswer && isInputEnabled) {
            isInputEnabled = false
        }
        if(!isInputEnabled && !hasLost) {
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                text = "You win! The word is '$wordAnswer'",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = montserrat,
                color = theme.CorrectTextColor
            )
        } else if (hasLost) {
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                text = "You lost. Better luck next time ( the word was `$wordAnswer` )",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = montserrat,
                color = theme.TextColor
            )
        }
    }
}


/**
 * Wordle Screen. Screen that the user is navigated to by the NavHost
 */
@Composable
fun WordleScreen(){
    var word: String? by remember {
        mutableStateOf("")
    }

    var hasNoInternetConnection by remember {
        mutableStateOf(false)
    }

    var wordLength by remember {
        mutableStateOf(0)
    }

    var isEnabled by remember {
        mutableStateOf(true)
    }

    var hasStarted by remember {
        mutableStateOf(false)
    }

    val onClickList: List<() -> Unit> = listOf(
        {
            hasNoInternetConnection = false
            wordLength = 5
            runBlocking {
                word = getRandomWord(wordLength = wordLength)
            }
            isEnabled = false
            if (word.isNullOrBlank()) { hasNoInternetConnection = true; println(hasNoInternetConnection) }
            println("WordLength: $wordLength; Word: $word")
        },
        {
            hasNoInternetConnection = false
            wordLength = 6
            runBlocking {
                word = getRandomWord(wordLength = wordLength)
            }
            if (word.isNullOrBlank()) { hasNoInternetConnection = true; println(hasNoInternetConnection) }
            isEnabled = false
            println("WordLength: $wordLength; Word: $word")
        },
        {
            hasNoInternetConnection = false
            wordLength = 7
            runBlocking {
                word = getRandomWord(wordLength = wordLength)
            }
            isEnabled = false
            if (word.isNullOrBlank()) { hasNoInternetConnection = true; println(hasNoInternetConnection) }
            println("WordLength: $wordLength; Word: $word")
        }
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WText(
            text = "Wordle",
            size = 32.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(top = 8.dp),
        )
        if(!hasStarted) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                StartButton(
                    onClick = { hasStarted = true },
                    text = "start"
                )
            }
        }
        else {
            Spacer(modifier = Modifier.height(24.dp))
            SelectWordLengthButtonRow(
                onClickList = onClickList,
                isEnabled = isEnabled
            )
            Spacer(modifier = Modifier.height(24.dp))
            if (!isEnabled && !hasNoInternetConnection) {
                WordleGameField(
                    wordAnswer = word!!,
                    wordLength = wordLength
                )
            } else if (!isEnabled && hasNoInternetConnection) {
                NoInternetConnectionSign()
            }
        }
    }
}