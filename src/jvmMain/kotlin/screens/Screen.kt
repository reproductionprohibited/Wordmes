package screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.*
import androidx.compose.ui.graphics.vector.ImageVector


/**
 * App Screens are defined here.
 */
enum class Screen(
    val label: String,
    val path: String,
) {
    HomeScreen(
        label = "Home",
        path = "images/screen_icons/home_icon.png"
    ),
    TutorialScreen(
        label = "Help",
        path = "images/screen_icons/information_icon.png"
    ),
    WordleScreen(
        label = "Wordge",
        path = "images/screen_icons/wordle_icon.png"
    ),
    SharkmanScreen(
        label = "Sharkman",
        path = "images/screen_icons/sharkman_icon.png"
    ),
    WordsFromWordScreen(
        label = "WFW",
        path = "images/screen_icons/words_from_word_icon.png"
    )
}