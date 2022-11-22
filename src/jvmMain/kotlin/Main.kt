// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import navigation.NavController
import navigation.NavHost
import navigation.composable
import navigation.rememberNavController

import screens.WordleScreen
import screens.*
import fonts.montserrat
import theming.Theme

val theme: Theme = Theme.LightTheme

@Composable
fun CustomNavigationHost(
    navController: NavController
) {
    NavHost(navController = navController) {
        composable(Screen.HomeScreen.name) {
            HomeScreen()
        }
        composable(Screen.WordleScreen.name) {
            WordleScreen()
        }
        composable(Screen.SharkmanScreen.name) {
            SharkmanScreen()
        }
        composable(Screen.WordsFromWordScreen.name) {
            WordsFromWordScreen()
        }
        composable(Screen.TutorialScreen.name) {
            TutorialScreen(navController = navController)
        }
        composable(Screen.BullsAndCowsNumbers.name) {
            BullsAndCowsNumberScreen()
        }
    }.build()
}

/**
 * Main.kt runs the app
 */
@Composable
fun App() {

    val screens = Screen.values()
    val navController by rememberNavController(Screen.HomeScreen.name)
    val currentScreen by remember {
        navController.currentScreen
    }

    MaterialTheme {
        Surface(
            modifier = Modifier.background(color = Color.White)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = theme.ScreenBackgroundColor),
            ) {
                NavigationRail(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .fillMaxHeight()
                        .width(80.dp),
                    backgroundColor = theme.NavigationRailColor
                ) {
                    screens.forEach { screen ->
                        NavigationRailItem(
                            selected = currentScreen == screen.name,
                            icon = {
                                Image(
                                    painter = painterResource(resourcePath = screen.path),
                                    contentDescription = screen.label,
                                    modifier = Modifier.size(28.dp)
                                )
                            },
                            label = {
                                Text(
                                    text = screen.label,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 12.sp,
                                    fontFamily = montserrat,
                                )
                            },
                            alwaysShowLabel = false,
                            onClick = { navController.navigate(screen.name) },
                            modifier = Modifier
                                .background(color = theme.NavigationRailItemBackgroundColor)
                                .clip(shape = RoundedCornerShape(25)),
                            selectedContentColor = theme.NavigationRailItemSelectedContentColor,
                            unselectedContentColor = theme.NavigationRailItemUnselectedContentColor,
                        )
                    }
                }
                Box(
                    modifier = Modifier.fillMaxHeight()
                ) {
                    CustomNavigationHost(navController = navController)
                }
            }
        }
    }
}


fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Wordmes",
        icon = painterResource(resourcePath = "images/others/icon.png")
    ) {
        App()
    }
}
