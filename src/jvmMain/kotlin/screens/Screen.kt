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
        label = "home",
        path = "images/screen_icons/home_icon.png"
    ),
    TutorialScreen(
        label = "help",
        path = "images/screen_icons/information_icon.png"
    ),
    WordleScreen(
        label = "wordle",
        path = "images/screen_icons/wordle_icon.png"
    ),
    SharkmanScreen(
        label = "sharkman",
        path = "images/screen_icons/sharkman_icon.png"
    ),
    WordsFromWordScreen(
        label = "W -> w",
        path = "images/screen_icons/words_from_word_icon.png"
    ),
    BullsAndCowsNumbers(
        label = "numbers",
        path = "images/screen_icons/bulls_and_cows_num_icon.png"
    )
}