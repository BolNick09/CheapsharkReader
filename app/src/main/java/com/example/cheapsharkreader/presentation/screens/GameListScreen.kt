package com.example.cheapsharkreader.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.cheapsharkreader.presentation.viewmodel.GameViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameListScreen(
    navController: NavController,
    viewModel: GameViewModel = koinViewModel()
) {
    val games by viewModel.games.collectAsState()
    var query by remember { mutableStateOf("batman") }

    LaunchedEffect(Unit) {
        viewModel.searchGames(query)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Game Deals") }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // 👈 вот он отступ под тулбар
        ) {

            TextField(
                value = query,
                onValueChange = {
                    query = it
                    viewModel.searchGames(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text("Search games") }
            )

            GameGrid(
                games = games,
                onGameClick = { game ->
                    navController.navigate("deals/${game.id}")
                }
            )
        }
    }
}