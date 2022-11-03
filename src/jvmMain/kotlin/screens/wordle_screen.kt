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
 * Game field for wordge. This is the area where the Player guess the word itself
 */
@Composable
private fun GameField(
    modifier: Modifier = Modifier,
    wordAnswer: String,
    wordLength: Int
) {
    var wordInput by remember {
        mutableStateOf("")
    }

    var isInputEnabled by remember {
        mutableStateOf(true)
    }

    var isAlertDialogOn by remember {
        mutableStateOf(false)
    }

    val guessAttempts: MutableMap<String, Int> by remember {
        mutableStateOf(mutableMapOf())
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = wordInput,
            onValueChange = { input ->
                wordInput = input.filter { it.isLetter() }.take(wordLength).lowercase()
            },
            enabled = isInputEnabled,
            colors = TextFieldDefaults.textFieldColors(
                textColor = theme.TextColor,
                backgroundColor = theme.TextFieldColor,
                focusedIndicatorColor = theme.TextFieldColor,
                focusedLabelColor = theme.TextColor
            )
        )
        Spacer(modifier = Modifier.height(48.dp))
        if(wordInput.length == wordLength) {
            guessAttempts[wordInput] = points(inputWord = wordInput, answerWord = wordAnswer)
            GuessRow(
                inputWord = wordInput,
                answerWord = wordAnswer
            )
            if(wordInput == wordAnswer) {
                isInputEnabled = false
            }
        }
        if(!isInputEnabled) {
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                text = "You Win! The word is '$wordAnswer'",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = montserrat,
                color = theme.CorrectTextColor
            )
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            OutlinedButton(
                onClick = { isAlertDialogOn = true; println("Alert Dialog is: $isAlertDialogOn") },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = theme.ButtonBackgroundColor,
                    contentColor = theme.ButtonContentColor
                ),
                border = BorderStroke(
                    width=1.dp,
                    color = theme.ButtonBorderColor
                ),
                enabled = isInputEnabled
            ) {
                Text(
                    text = "Attempts",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = montserrat,
                    color = theme.TextColor
                )
            }
        }
        if(isAlertDialogOn) {
            InformationAlertDialog(
                titleText = "Attempts [top-5]",
                infoText = guessAttempts
                    .toList()
                    .sortedByDescending{ (_, points) -> points}.take(5).toMap()
                    .keys.joinToString("\n"),
                onClick = { isAlertDialogOn = false; println("Alert Dialog is: $isAlertDialogOn") }
            )
        }
    }
}


/**
 * Screen with the game of 'Wordge'
 */
@Composable
fun WordgeScreen(){
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
            size = 24.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(top = 8.dp),
        )
        if(!hasStarted) {
            StartButton(
                onClick = { hasStarted = true },
            )
        }
        else {
            Spacer(modifier = Modifier.height(24.dp))
            SelectWordLengthButtonRow(
                onClickList = onClickList,
                isEnabled = isEnabled
            )
            Spacer(modifier = Modifier.height(24.dp))
            if (!isEnabled && !hasNoInternetConnection) {
                GameField(
                    wordAnswer = word!!,
                    wordLength = wordLength
                )
            } else if (!isEnabled && hasNoInternetConnection) {
                NoInternetConnectionSign()
            }
        }
    }
}