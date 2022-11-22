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


/**
 * mini-games categories
 */
private val categories = listOf(
    "wordge",
    "sharkman",
    "w -> w",
    "bulls & cows",
    "about",
)


/**
 * Text that follows the main text of the category.
 *      text: content text
 */
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
 * Information about Wordle mini-game. Contains a navigation Button to navigate the user directly into the game
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
 * Information about Sharkman mini-game. Contains a navigation Button to navigate the user directly into the game
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
                Your goal is to keep the man from being eaten by a hungry shark. You have 8 tries until the shark 
                reaches the guy and eats him immediately.
            """.trimIndent(),
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = montserrat,
            textAlign = TextAlign.Center,
            color = theme.TextColor
        )
        AfterInfoSign(
            text = "Make sure to help the guy!"
        )
        NavigateToGameButton(
            navController = navController,
            route = Screen.SharkmanScreen.name
        )
    }
}


/**
 * Information about Words from a Word mini-game. Contains a navigation Button to navigate the user directly into the game
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
                the letters present in the starting word ( mind the amount of letters ). If you have assembled 5 words,
                you got 1 star, 12 words = 2 stars and, finally, 25 words = 3 stars. If you got that same amount of words,
                you may never come back to this word, but there's no limit, you can keep going.
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
 * Information about Bulls & Cows mini-game. Contains a navigation Button to navigate the user directly into the game
 */
@Composable
private fun InformationBullsAndCows(
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
                In this game, a specific (4 / 6)-digit number is being guessed. Your goal is to guess his number.
                Each step of yours must contain a (4 / 6)-digit number ( depends on which mode you've chosen ).
                After each step you receive information about current step: the amount of bulls and the amount of cows.
                Bulls are digits that are on their positions in the guessed number. Cows are digits that are present
                in the guessed number, but they're not in the position you got them in the guess.
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
            route = Screen.BullsAndCowsNumbers.name
        )
    }
}


/**
 * Information about the app
 */
@Composable
private fun InformationAbout() {
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
 *      onClick: handling onClick action
 *      isEnabled: button is pressed [ false ] and showing the corresponding information; [ true ] - otherwise
 *      category: button content text
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


/**
 * NavigateToGameButton - Button that navigates the user to a specific screen ( route ) from the Help Screen
 * ( TutorialScreen )
 *      navController: NavController that navigates the user around the app
 *      route: Destination Screen
 */
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
 * Screen to help a new user get around the app.
 *      navController: handles navigation inside the app
 */
@Composable
fun TutorialScreen(
    navController: NavController
) {
    /*
        === LIST OF CATEGORIES ===
        1 : wordle
        2 : sharkman
        3 : words from word
        4 : bulls & cows
        5 : about
     */
    var selectedCategory: Int by remember {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WText(
            text = "Help",
            size = 32.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(top = 8.dp)
        )
        Spacer(modifier = Modifier.height(124.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            categories.forEachIndexed { i, _ ->
                when (i) {
                    0 -> {
                        // select `wordle` button
                        SelectCategoryButton(
                            onClick = {
                                selectedCategory = 1
                            },
                            isEnabled = selectedCategory != 1,
                            category = categories[0]
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                    }
                    1 -> {
                        // select `sharkman` button
                        SelectCategoryButton(
                            onClick = {
                                selectedCategory = 2
                            },
                            isEnabled = selectedCategory != 2,
                            category = categories[1]
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                    }
                    2 -> {
                        // select `words from word` button
                        SelectCategoryButton(
                            onClick = {
                                selectedCategory = 3
                            },
                            isEnabled = selectedCategory != 3,
                            category = categories[2]
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                    }
                    3 -> {
                        // select `Bulls & Cows` button
                        SelectCategoryButton(
                            onClick = {
                                selectedCategory = 4
                            },
                            isEnabled = selectedCategory != 4,
                            category = categories[3]
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                    }
                    4 -> {
                        // select `About` button
                        SelectCategoryButton(
                            onClick = {
                                selectedCategory = 5
                            },
                            isEnabled = selectedCategory != 5,
                            category = categories[4]
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        when (selectedCategory) {
            1 -> {
                InformationWordle(navController = navController)
            }
            2 -> {
                InformationSharkman(navController = navController)
            }
            3 -> {
                InformationWordsFromWord(navController = navController)
            }
            4 -> {
                InformationBullsAndCows(navController = navController)
            }
            5 -> {
                InformationAbout()
            }
        }
    }
}