package screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.*
import androidx.compose.ui.graphics.vector.ImageVector


/**
 * App Screens are defined here.
 */
enum class Screen(
    val label: String,
    val icon: ImageVector,
) {
    HomeScreen(
        label = "Home",
        icon = Icons.TwoTone.Home
    ),
    TutorialScreen(
        label = "Tutorial",
        icon = Icons.TwoTone.Info
    ),
    WordleScreen(
        label = "Wordge",
        icon = Icons.TwoTone.List
    ),
    SharkmanScreen(
        label = "Sharkman",
        icon = Icons.TwoTone.Face
    ),
    WordsFromWordScreen(
        label = "WFW",
        icon = Icons.TwoTone.AddCircle
    ),
    CreatorsScreen(
        label = "Creators",
        icon = Icons.TwoTone.Person
    )
}