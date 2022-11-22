package screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import fragments.WText
import theme
import fonts.montserrat
import fragments.StartButton
import kotlin.random.Random

/**
 * Counting the amount of bulls and cows in user guess
 *      number: user input
 *      guessedNumber: guessed number
 */
private fun getBullsAndCows(
    number: String,
    guessedNumber: String
): Pair<Int, Int> {
    var bulls: Int = 0
    var cows: Int = 0
    val bullList: MutableList<Char> = mutableListOf()
    for (i in guessedNumber.indices) {
        if (guessedNumber[i] == number[i]) { bulls++; bullList.add(number[i]) }
    }
    for (i in number.indices) {
        if (bullList.contains(number[i])) { bullList.remove(number[i]) }
        else if (number[i] in guessedNumber) { cows++ }
    }
    return Pair(bulls, cows)
}

/**
 * GameField handles the game process.
 *      guessedNumber: the number user has to guess
 */
@Composable
private fun GameField(
    guessedNumber: Int
) {
    var playerGuess: String by remember {
        mutableStateOf("")
    }

    val guesses: MutableList<Triple<String, Int, Int>> by remember {
        mutableStateOf(mutableListOf())
    }
    var output: String by remember {
        mutableStateOf("")
    }

    var isGameFinished: Boolean by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(bottom = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        WText(
            text = "${guessedNumber.toString().length}-digit number is guessed",
            size = 14.sp,
            fontWeight = FontWeight.Normal
        )
        OutlinedTextField(
            value = playerGuess,
            onValueChange = { input ->
                if (input.length <= guessedNumber.toString().length) { playerGuess = input.filter { it.isDigit() } }
            },
            modifier = Modifier.width(240.dp),
            label = {
                Text(
                    text = "guess",
                    fontFamily = montserrat
                )
            },
            enabled = !isGameFinished,
            textStyle = TextStyle.Default.copy(fontFamily = montserrat),
            colors = TextFieldDefaults.textFieldColors(
                textColor = theme.TextColor,
                backgroundColor = theme.TextFieldColor,
                focusedIndicatorColor = theme.TextFieldColor,
                focusedLabelColor = theme.TextColor
            )
        )
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedButton(
            onClick = {
                if (playerGuess.length == guessedNumber.toString().length) {
                    val bc: Pair<Int, Int> =
                        getBullsAndCows(number = playerGuess, guessedNumber = guessedNumber.toString())
                    guesses.add(Triple(playerGuess, bc.first, bc.second))
                    output = ""
                    for (guess in guesses) {
                        output += "${guess.first} : ${guess.second} bulls, ${guess.third} cows\n"
                    }
                    if (guesses.size >= 5) {
                        guesses.removeAt(0)
                    }
                    if (bc.first == guessedNumber.toString().length) {
                        isGameFinished = true
                    }
                }
                playerGuess = ""
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = theme.ButtonBackgroundColor,
                contentColor = theme.ButtonContentColor
            ),
            border = BorderStroke(
                width = 1.dp,
                color = theme.ButtonBorderColor
            ),
            enabled = !isGameFinished,
            modifier = Modifier.width(120.dp).height(50.dp)
        ) {
            Text(
                text = "submit",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = montserrat,
                textAlign = TextAlign.Center,
                color = theme.TextColor
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Divider(modifier = Modifier.height(2.dp).width(48.dp), color = theme.CardBorderColor)
        Spacer(modifier = Modifier.height(16.dp))

        WText(
            text = output,
            size = 14.sp,
            fontWeight = FontWeight.Normal
        )
        if (isGameFinished) {
            Spacer(modifier = Modifier.height(12.dp))
            WText(
                text = "You win!\nThe guessed number was : $guessedNumber",
                size = 16.sp,
                fontWeight = FontWeight.Medium,
                color = theme.CorrectTextColor,
            )
        }
    }
}

/**
 * Main segment of the mini-game. Used in the navigation
 */
@Composable
fun BullsAndCowsNumberScreen() {
    var guessedNumber: Int by remember {
        mutableStateOf(0)
    }

    var hasStarted: Boolean by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WText(
            text = "Bulls & Cows",
            size = 32.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(top = 8.dp)
        )
        if (!hasStarted) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxSize()
            ) {
                StartButton(
                    text = "normal",
                    onClick = {
                        guessedNumber = Random.nextInt(1_000, 10_000)
                        hasStarted = true
                    },
                )
                Spacer(modifier = Modifier.width(32.dp))
                StartButton(
                    text = "hard",
                    onClick = {
                        guessedNumber = Random.nextInt(100_000, 1_000_000)
                        hasStarted = true
                    }
                )
            }
        } else {
            Spacer(modifier = Modifier.height(48.dp))
            println("Guessed number: $guessedNumber")
            GameField(guessedNumber = guessedNumber)
        }
    }
}