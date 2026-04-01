package com.example.cheapsharkreader.presentation.screens

import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cheapsharkreader.R
import com.example.cheapsharkreader.domain.model.Game
import com.example.cheapsharkreader.presentation.viewmodel.GameViewModel
import org.koin.androidx.compose.koinViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameListScreen(
    navController: NavController,
    viewModel: GameViewModel = koinViewModel()
) {
    val games by viewModel.games.collectAsState()
    val favorites by viewModel.favorites.collectAsState()

    var query by remember { mutableStateOf("") }
    val displayGames = games

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.game_deals)) }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            TextField(
                value = query,
                onValueChange = {
                    query = it

                    if (it.isNotBlank()) {
                        viewModel.searchGames(it)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(stringResource(R.string.search_games)) }
            )

            if (displayGames.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    Text(stringResource(R.string.no_games_found))
                }
            } else {

                GameGrid(
                    games = displayGames,

                    onGameClick = { game ->
                        navController.navigate(
                            "deals/${game.id}/" +
                                    "${URLEncoder.encode(game.title, StandardCharsets.UTF_8.toString())}/" +
                                    "${URLEncoder.encode(game.image, StandardCharsets.UTF_8.toString())}"
                        )
                    },

                    onFavoriteClick = { game ->
                        viewModel.toggleFavorite(game)
                    },

                    isFavorite = { game ->
                        favorites.any { it.id == game.id }
                    }
                )
            }
        }
    }
}

@Composable
fun GameListContent(
    games: List<Game>,
    query: String,
    onQueryChange: (String) -> Unit,
    onGameClick: (Game) -> Unit,
    onFavoriteClick: (Game) -> Unit,
    isFavorite: (Game) -> Boolean
) {
    Column {
        TextField(
            value = query,
            onValueChange = onQueryChange
        )

        GameGrid(
            games = games,
            onGameClick = onGameClick,
            onFavoriteClick = onFavoriteClick,
            isFavorite = isFavorite
        )
    }
}
@Preview()
@Composable
fun GameListScreenPreview() {
    GameListContent(
        games = listOf(
            Game("1", "Game 1", "3.99", ""),
            Game("2", "Game 2", "5.99", "")
        ),
        query = "",
        onQueryChange = {},
        onGameClick = {},
        onFavoriteClick = {},
        isFavorite = { false }
    )
}