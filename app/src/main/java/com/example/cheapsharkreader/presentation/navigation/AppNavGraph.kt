package com.example.cheapsharkreader.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cheapsharkreader.presentation.screens.DealsScreen
import com.example.cheapsharkreader.presentation.screens.GameListScreen

@Composable
fun AppNavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "games"
    ) {

        composable("games") {
            GameListScreen(navController)
        }

        composable("deals/{gameId}") { backStackEntry ->
            val gameId = backStackEntry.arguments?.getString("gameId") ?: ""

            DealsScreen(gameId)
        }
    }
}