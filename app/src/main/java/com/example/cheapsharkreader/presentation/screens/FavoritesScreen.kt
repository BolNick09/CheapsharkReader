package com.example.cheapsharkreader.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cheapsharkreader.R
import com.example.cheapsharkreader.domain.model.Game
import com.example.cheapsharkreader.presentation.viewmodel.FavoritesViewModel
import org.koin.androidx.compose.koinViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@Composable
fun FavoritesScreen(
    navController: NavController,
    viewModel: FavoritesViewModel = koinViewModel()
) {
    val favorites by viewModel.favorites.collectAsState()

    if (favorites.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(stringResource(R.string.no_favorites_yet))
        }
    } else {

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 150.dp),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(favorites) { game ->

                GameItem(
                    game = game,
                    onClick = {
                        navController.navigate(
                            "deals/${game.id}/" +
                                    "${URLEncoder.encode(game.title, StandardCharsets.UTF_8.toString())}/" +
                                    "${URLEncoder.encode(game.image, StandardCharsets.UTF_8.toString())}"
                        )
                    },
                    onFavoriteClick = {
                        viewModel.removeFromFavorites(game)
                    },
                    isFavorite = true
                )
            }
        }
    }
}

@Composable
fun FavoritesContent(
    games: List<Game>,
    onGameClick: (Game) -> Unit,
    onFavoriteClick: (Game) -> Unit
) {
    GameGrid(
        games = games,
        onGameClick = onGameClick,
        onFavoriteClick = onFavoriteClick,
        isFavorite = { true }
    )
}
@Preview()
@Composable
fun FavoritesScreenPreview() {
    FavoritesContent(
        games = listOf(
            Game("1", "Favorite Game", "4.99", "")
        ),
        onGameClick = {},
        onFavoriteClick = {}
    )
}