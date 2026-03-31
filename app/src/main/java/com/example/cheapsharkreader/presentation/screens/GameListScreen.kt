package com.example.cheapsharkreader.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cheapsharkreader.R
import com.example.cheapsharkreader.domain.repository.FavoritesRepository
import com.example.cheapsharkreader.presentation.viewmodel.GameViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.getKoin
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameListScreen(
    navController: NavController,
    viewModel: GameViewModel = koinViewModel()
) {
    val games by viewModel.games.collectAsState()

    var query by remember { mutableStateOf("") }

    val favoritesRepo: FavoritesRepository = getKoin().get()
    val favorites by favoritesRepo.favorites.collectAsState(initial = emptyList())

    val displayGames = games

    val scope = rememberCoroutineScope()

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
                        scope.launch {
                            if (favorites.any { it.id == game.id }) {
                                favoritesRepo.remove(game)
                            } else {
                                favoritesRepo.add(game)
                            }
                        }
                    },

                    isFavorite = { game ->
                        favorites.any { it.id == game.id }
                    }
                )
            }
        }
    }
}