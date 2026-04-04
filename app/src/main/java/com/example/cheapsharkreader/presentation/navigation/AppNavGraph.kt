package com.example.cheapsharkreader.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Games
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cheapsharkreader.R
import com.example.cheapsharkreader.presentation.screens.DealsScreen
import com.example.cheapsharkreader.presentation.screens.FavoritesScreen
import com.example.cheapsharkreader.presentation.screens.GameListScreen
import java.net.URLDecoder

@Composable
fun AppNavGraph() {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {

                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("favorites") },
                    icon = {
                        Icon(Icons.Default.Favorite, null)
                    },
                    label = { Text(stringResource(R.string.favorites)) }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("games") },
                    icon = {
                        Icon(Icons.Default.Games, null)
                    },
                    label = { Text(stringResource(R.string.games)) }
                )
            }
        }
    ) { padding ->

        NavHost(
            navController = navController,
            startDestination = "favorites",
            modifier = Modifier.padding(padding)
        ) {

            composable("favorites") {
                FavoritesScreen(navController)
            }

            composable("games") {
                GameListScreen(navController)
            }

            composable(
                "deals/{gameId}/{title}/{image}"
            ) { backStackEntry ->

                val gameId = backStackEntry.arguments?.getString("gameId") ?: ""
                val rawTitle = backStackEntry.arguments?.getString("title") ?: ""
                val title = URLDecoder.decode(rawTitle, "UTF-8")

                val rawImage = backStackEntry.arguments?.getString("image") ?: ""
                val image = URLDecoder.decode(rawImage, "UTF-8")

                DealsScreen(
                    gameId = gameId,
                    title = title,
                    image = image,
                    navController = navController
                )
            }
        }
    }
}