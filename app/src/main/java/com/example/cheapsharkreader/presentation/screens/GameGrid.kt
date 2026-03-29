package com.example.cheapsharkreader.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.cheapsharkreader.domain.model.Game

@Composable
fun GameGrid(
    games: List<Game>,
    onGameClick: (Game) -> Unit,
    onFavoriteClick: (Game) -> Unit,
    isFavorite: (Game) -> Boolean
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(games) { game ->
            GameItem(
                game = game,
                onClick = { onGameClick(game) },
                onFavoriteClick = { onFavoriteClick(game) },
                isFavorite = isFavorite(game)
            )
        }
    }
}