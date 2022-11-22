package screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import backend.api.isValidWord
import fonts.montserrat
import fragments.NoInternetConnectionSign
import fragments.WText
import fragments.WTextField
import theme


/**
 * 100 words used as levels
 */
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
    "mediterranean",
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

/**
 * Generates a char map from a string, containing pairs (letter, count).
 * Used when checking player guess based on the word at a certain level
 */
private fun charMapFromString(
    string: String
): Map<Char, Int> {
    val mp: MutableMap<Char, Int> = mutableMapOf()
    string.forEach { mp[it] = (mp[it] ?: 0) + 1 }
    return mp
}

/**
 * Check whether the player guess can be assembled with the letters of the player guess
 */
private fun isGuessValidLetterCount(
    guess: String,
    answerMap: Map<Char, Int>,
): Boolean {
    val guessMap: MutableMap<Char, Int> = mutableMapOf()
    guess.forEach { guessMap[it] = (guessMap[it] ?: 0) + 1 }
    guessMap.forEach { (key, value) ->
        if (value > (answerMap[key] ?: -1)) { return false }
    }
    return true
}


/**
 * Arrow-like icon buttons handling level choosing process
 */
@Composable
private fun ChooseLevelIconButton(
    onClick: () -> Unit,
    resourcePath: String,
    contentDescription: String,
    modifier: Modifier = Modifier,
    angle: Float
) {
    IconButton(onClick = onClick) {
        Icon(
            painter = painterResource(resourcePath = resourcePath),
            contentDescription = contentDescription,
            modifier = modifier.size(32.dp).rotate(angle)
        )
    }
}

/**
 * Custom button with the theme colors out of the box
 */
@Composable
private fun WButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    isEnabled: Boolean = true,
    text: String
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
            width = 1.dp,
            color = theme.ButtonBorderColor
        )
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            fontFamily = montserrat,
            color = theme.TextColor
        )
    }
}


/**
 * Display how many stars does a player have on each level
 */
@Suppress("SameParameterValue")
@Composable
private fun StarRow(
    filledStars: Int,
    modifier: Modifier = Modifier,
    verticalAlignment: Alignment.Vertical
) {
    var filledStarsCount: Int by remember {
        mutableStateOf(filledStars % 4)
    }

    Row(
        verticalAlignment = verticalAlignment,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        for(i in 0 .. 2) {
            if (filledStarsCount > 0) {
                Image(
                    painter = painterResource(resourcePath = "images/words_from_words/star_fill.png"),
                    contentDescription = "star_fill",
                    modifier = Modifier.size(32.dp)
                )
                filledStarsCount--
            } else {
                Image(
                    painter = painterResource(resourcePath = "images/words_from_words/star_blank.png"),
                    contentDescription = "star_blank",
                    modifier = Modifier.size(32.dp)
                )
            }
            if (i != 2) { Spacer(modifier = Modifier.width(18.dp)) }
        }
    }
}

/**
 * Display the player guesses
 */
@Composable
private fun GuessColumn(
    title: String,
    guessList: List<String>,
    color: Color
) {
    WText(
        text = guessList.takeLast(7).joinToString(separator = "\n", prefix = "$title\n${guessList.size}\n"),
        color = color,
        size = 18.sp,
        fontWeight = FontWeight.Light,
        modifier = Modifier.height(240.dp)
    )
}


/**
 * The code of game process of Words from Words
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun WordsFromWordGame(
    levelWord: String
) {
    var playerGuess: String by remember {
        mutableStateOf("")
    }

    val successfulGuessList: MutableList<String> by remember {
        mutableStateOf(mutableListOf())
    }

    val unsuccessfulGuessList: MutableList<String> by remember {
        mutableStateOf(mutableListOf())
    }

    var hasNoInternetConnection: Boolean by remember {
        mutableStateOf(false)
    }

    var isPlayerGuessValid: Boolean by remember {
        mutableStateOf(false)
    }

    val levelWordMap: Map<Char, Int> by remember {
        mutableStateOf(charMapFromString(string = levelWord))
    }

    var starCount: Int by remember {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (!hasNoInternetConnection) {
            WText(
                text = levelWord,
                size = 24.sp,
                fontWeight = FontWeight.Normal
            )
            Spacer(modifier = Modifier.height(8.dp))
            Divider(modifier = Modifier.height(1.dp).width(240.dp), color = theme.ButtonBorderColor)
            Spacer(modifier = Modifier.height(8.dp))
            WTextField(
                value = playerGuess,
                onValueChange = { input ->
                    playerGuess = input.take(levelWord.length).lowercase()
                },
                alpha = 0.6f,
                singleLine = true,
                modifier = Modifier.onKeyEvent { event ->
                    if ((event.key == Key.Enter || event.key == Key.NumPadEnter) && playerGuess.length > 1) {
                        val ivwBoolList: List<Boolean> = isValidWord(word = playerGuess)
                        hasNoInternetConnection = ivwBoolList[0]
                        isPlayerGuessValid = ivwBoolList[1]
                        if (isGuessValidLetterCount(
                                guess = playerGuess,
                                answerMap = levelWordMap
                            ) && isPlayerGuessValid) {
                            if (playerGuess !in successfulGuessList) { successfulGuessList.add(playerGuess)}
                            if (successfulGuessList.size >= 5) starCount = 1
                            if (successfulGuessList.size >= 12) starCount = 2
                            if (successfulGuessList.size >= 25) starCount = 3
                            println(starCount)
                        } else {
                            if (playerGuess !in unsuccessfulGuessList) { unsuccessfulGuessList.add(playerGuess)}
                        }
                        playerGuess = ""
                    }
                    true
                }
            )
            Spacer(modifier = Modifier.height(48.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                GuessColumn(
                    title = "Successful Guesses:",
                    guessList = successfulGuessList,
                    color = theme.CorrectTextColor
                )
                Spacer(modifier = Modifier.width(24.dp))
                GuessColumn(
                    title = "Unsuccessful Guesses:",
                    guessList = unsuccessfulGuessList,
                    color = theme.WrongTextColor
                )
            }
            when(starCount) {
                0 -> {
                    StarRow(
                        filledStars = 0,
                        verticalAlignment = Alignment.Bottom,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }
                1 -> {
                    StarRow(
                        filledStars = 1,
                        verticalAlignment = Alignment.Bottom,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }
                2 -> {
                    StarRow(
                        filledStars = 2,
                        verticalAlignment = Alignment.Bottom,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }
                3 -> {
                    StarRow(
                        filledStars = 3,
                        verticalAlignment = Alignment.Bottom,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    WText(
                        text = "You're mastered this one. Well done!",
                        fontWeight = FontWeight.Normal,
                        size = 14.sp,
                        color = theme.CorrectTextColor
                    )
                }
            }
        }
        else {
            NoInternetConnectionSign()
        }

    }
}


/**
 * Words from a Word Screen ( used when navigating )
 */
@Composable
fun WordsFromWordScreen() {
    var isGameStarted: Boolean by remember {
        mutableStateOf(false)
    }

    var level: Int by remember {
        mutableStateOf(1)
    }

    var levelWord: String by remember {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        WText(
            text = "Words from Word",
            size = 32.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.align(Alignment.TopCenter).padding(top = 8.dp)
        )
        if (!isGameStarted) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    ChooseLevelIconButton(
                        onClick = { level--; if (level == 0) level = 99 },
                        resourcePath = "images/words_from_words/arrow.png",
                        contentDescription = "level_arrow_left",
                        angle = 180f
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    WText(
                        text = words[level - 1],
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Normal,
                        size = 16.sp,
                        modifier = Modifier.width(240.dp).clickable { isGameStarted = true; levelWord = words[level - 1] }
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    ChooseLevelIconButton(
                        onClick = { level++; if (level == 100) level = 1 },
                        resourcePath = "images/words_from_words/arrow.png",
                        contentDescription = "level_arrow_right",
                        angle = 0f
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Divider(modifier = Modifier.height(1.dp).width(80.dp), color = theme.ButtonBorderColor)
            }
        }
        else {
            WordsFromWordGame(levelWord = levelWord)
        }
    }
}