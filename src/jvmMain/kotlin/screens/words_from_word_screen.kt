package screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import backend.api.isValidWord
import fonts.montserrat
import fragments.NoInternetConnectionSign
import fragments.StartButton
import fragments.WText
import kotlinx.coroutines.runBlocking
import theme
import kotlin.random.Random


// 100 words
private val words = listOf(
    "anthropomorphic",
    "simplification",
    "questionnaire",
    "anathematizing",
    "representatives",
    "satisfactorily",
    "heuristically",
    "incandescence",
    "cardiopulmonary",
    "insurmountable",
    "huckleberries",
    "confederation",
    "personification",
    "accreditation",
    "contingencies",
    "effervescent",
    "constitutional",
    "justification",
    "insectivorous",
    "hydrocephalus",
    "qualifications",
    "baccalaureate",
    "encouragement",
    "exceptionally",
    "accomplishment",
    "parenthetical",
    "mathematician",
    "uncomfortable",
    "nonabsorbent",
    "automatically",
    "infinitesimal",
    "unforeseeable",
    "extemporaneous",
    "inconsiderate",
    "antihistamine",
    "parliamentary",
    "affectionately",
    "inexpressible",
    "ichthyologists",
    "knowledgeable",
    "unconscionable",
    "zoopraxiscope",
    "establishment",
    "perfectionism",
    "septuagenarian",
    "incarceration",
    "quadrilateral",
    "comparatively",
    "correspondence",
    "consciousness",
    "thermodynamic",
    "approximately",
    "circumnavigate",
    "indescribable",
    "entertainment",
    "concatenation",
    "reconciliation",
    "indefatigable",
    "animadversion",
    "gravitational",
    "transportation",
    "circumference",
    "uncooperative",
    "hieroglyphics",
    "ichthyologist",
    "preternatural",
    "impulsiveness",
    "ventriloquist",
    "sanctification",
    "paraphernalia",
    "assassination",
    "Mediterranean",
    "superintendent",
    "daguerreotype",
    "scintillation",
    "circumstances",
    "responsibility",
    "phosphorylation",
    "accouterments",
    "pusillanimous",
    "manageability",
    "administrator",
    "recrimination",
    "amelioration",
    "efflorescence",
    "dischargeable",
    "international",
    "inefficiency",
    "perspicacious",
    "contradictory",
    "procrastinate",
    "suspiciously",
    "multitudinous",
    "configuration",
    "comprehensive",
    "imponderable",
    "inexperienced",
    "petrochemical",
    "transference",
    "coincidental",
)


private fun isValidLetterCount(
    guess: String,
    goal: String
): Boolean {
    val guessLettersMap: MutableMap<Char, Int> = mutableMapOf()
    val goalLettersMap: MutableMap<Char, Int> = mutableMapOf()

    guess.forEach { guessLettersMap[it] = (guessLettersMap[it] ?: 0) + 1 }
    goal.forEach { goalLettersMap[it] = (goalLettersMap[it] ?: 0) + 1 }

    for(entry in guessLettersMap) {
        val letter: Char = entry.key
        val count: Int = entry.value
        if ( (goalLettersMap[letter] == null) || (goalLettersMap[letter]!! < count) ) return false
    }
    return true
}


@Composable
private fun ChooseDifficultyButton(
    onClick: () -> Unit,
    mode: String
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
            text = mode,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = montserrat,
            textAlign = TextAlign.Center,
            color = theme.TextColor
        )
    }
}

@Composable
private fun WordsFromWord(
    mainWord: String,
    necessaryWordCount: Int = 5,
    mistakeWordCount: Int = 0
) {
    var inputWord: String by remember {
        mutableStateOf("")
    }

    var successfulGuessCount: Int by remember {
        mutableStateOf(0)
    }

    var hasNoInternetConnection: Boolean by remember {
        mutableStateOf(false)
    }

    var isWordValid : Boolean by remember {
        mutableStateOf(false)
    }

    var isInputEnabled: Boolean by remember {
        mutableStateOf(true)
    }

    var list: MutableList<Boolean> by remember {
        mutableStateOf(mutableListOf())
    }

    val guessedWords : MutableList<String> by remember {
        mutableStateOf(
            mutableListOf()
        )
    }

    val mistakeWords: MutableList<String> by remember {
        mutableStateOf(
            mutableListOf()
        )
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        if(isInputEnabled && !hasNoInternetConnection) {
            OutlinedTextField(
                value = inputWord,
                onValueChange = { input ->
                    inputWord = input.filter { it.isLetter() }.lowercase()
                },
                modifier = Modifier.width(360.dp),
                label = {
                    Text(
                        text = "guess",
                        fontFamily = montserrat
                    )
                },
                enabled = isInputEnabled,
                textStyle = TextStyle.Default.copy(fontFamily = montserrat),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = theme.TextColor,
                    backgroundColor = theme.TextFieldColor,
                    focusedIndicatorColor = theme.TextFieldColor,
                    focusedLabelColor = theme.TextColor
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedButton(
                onClick = {
                    runBlocking {
                        list = isValidWord(word = inputWord).toMutableList()
                    }
                    hasNoInternetConnection = list[0]; isWordValid = list[1]
                    println("[$hasNoInternetConnection, $isWordValid]")
                    if (
                        inputWord.length > 1 &&
                        inputWord !in guessedWords &&
                        isValidLetterCount(guess = inputWord, goal = mainWord) &&
                        !hasNoInternetConnection &&
                        isWordValid
                    ) {
                        successfulGuessCount++
                        guessedWords.add(inputWord)
                        println(
                            guessedWords.joinToString(separator = ", ", prefix = "GUESSED WORDS: ", postfix = ".")
                        )
                        println("$inputWord is valid")
                        if (successfulGuessCount == necessaryWordCount) isInputEnabled = false
                    } else if (
                        inputWord.length > 1 &&
                        inputWord !in guessedWords &&
                        isValidLetterCount(guess = inputWord, goal = mainWord) &&
                        !hasNoInternetConnection &&
                        !isWordValid &&
                        successfulGuessCount > 0
                    ) {
                        successfulGuessCount -= mistakeWordCount
                        mistakeWords.add(inputWord)
                        println(
                            mistakeWords.joinToString(separator = ", ", prefix = "MISTAKE WORDS: ", postfix = ".")
                        )
                        println("$inputWord is INVALID")
                    }
                    inputWord = ""
                },
                enabled = isInputEnabled,
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
                    text = "enter",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light,
                    fontFamily = montserrat
                )
            }
        } else if (!isInputEnabled && !hasNoInternetConnection) {
            Text(
                text = "Congratulations. You win!",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = montserrat,
                color = theme.TextColor
            )
        } else if (hasNoInternetConnection) { NoInternetConnectionSign() }
        if (!hasNoInternetConnection) {
            Spacer(modifier = Modifier.height(32.dp))
            Divider(modifier = Modifier.height(2.dp).width(400.dp), color = Color.DarkGray)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Progress: $successfulGuessCount/$necessaryWordCount guessed\n\n\n" +
                        if (guessedWords.size > 0) "Lucky Guesses: ${guessedWords.joinToString(separator = ", ")}"
                        else "",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = montserrat,
                textAlign = TextAlign.Center,
                color = theme.CorrectTextColor
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = if (mistakeWords.size > 0) "Mistaken Guesses: ${mistakeWords.joinToString(separator = ", ")}"
                else "",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = montserrat,
                textAlign = TextAlign.Center,
                color = theme.WrongTextColor
            )
        }
    }
}


@Composable
fun WordsFromWordScreen(){
    var pickedWordCount: Int by remember {
        mutableStateOf(0)
    }

    var mistakeWordCount: Int by remember {
        mutableStateOf(0)
    }

    var hasStarted: Boolean by remember {
        mutableStateOf(false)
    }

    var mainWord: String by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        WText(
            text = "Words from Word",
            size = 24.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(top = 8.dp)
        )
        if(!hasStarted) {
            Row (
                modifier = Modifier.fillMaxSize().padding(horizontal = 180.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                ChooseDifficultyButton ( onClick = {
                    hasStarted = true
                    mainWord = words[Random.nextInt(0, 101)]
                    pickedWordCount = 5
                    mistakeWordCount = 0
                }, mode = "Easy" )
                Spacer(modifier = Modifier.width(24.dp))
                ChooseDifficultyButton ( onClick = {
                    hasStarted = true
                    mainWord = words[Random.nextInt(0, 101)]
                    pickedWordCount = 7
                    mistakeWordCount = 1
                }, mode = "Normal" )
                Spacer(modifier = Modifier.width(24.dp))
                ChooseDifficultyButton ( onClick = {
                    hasStarted = true
                    mainWord = words[Random.nextInt(0, 101)]
                    pickedWordCount = 10
                    mistakeWordCount = 2
                }, mode = "Hard" )
            }
        }
        else {
            Spacer(modifier = Modifier.height(72.dp))
            Text(
                text = mainWord.uppercase(),
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = montserrat,
                textAlign = TextAlign.Center,
                textDecoration = TextDecoration.Underline,
                letterSpacing = 4.sp,
                color = theme.TextColor
            )
            println("PICKED WORD COUNT: $pickedWordCount; MISTAKE WORD COUNT: $mistakeWordCount")
            WordsFromWord(mainWord = mainWord, necessaryWordCount = pickedWordCount, mistakeWordCount = mistakeWordCount)
        }
    }
}