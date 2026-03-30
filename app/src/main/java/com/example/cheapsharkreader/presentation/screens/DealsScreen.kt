package com.example.cheapsharkreader.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.cheapsharkreader.presentation.viewmodel.DealsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DealsScreen(
    gameId: String,
    title: String,
    image: String,
    viewModel: DealsViewModel = koinViewModel()
) {
    val deals by viewModel.deals.collectAsState()
    val stores by viewModel.stores.collectAsState()

    LaunchedEffect(gameId) {
        viewModel.load(gameId)
    }

    Column {

        AsyncImage(
            model = image,
            contentDescription = title,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(8.dp)
        )

        LazyColumn {
            items(deals) { deal ->
                DealItem(deal, stores)
            }
        }
    }
}