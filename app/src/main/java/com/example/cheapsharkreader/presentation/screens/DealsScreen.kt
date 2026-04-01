package com.example.cheapsharkreader.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.cheapsharkreader.domain.model.Deal
import com.example.cheapsharkreader.domain.model.Store
import com.example.cheapsharkreader.presentation.viewmodel.DealsViewModel
import org.koin.androidx.compose.koinViewModel
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DealsScreen(
    gameId: String,
    title: String,
    image: String,
    navController: NavController,
    viewModel: DealsViewModel = koinViewModel()
) {
    val deals by viewModel.deals.collectAsState()
    val stores by viewModel.stores.collectAsState()

    LaunchedEffect(gameId) {
        viewModel.load(gameId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier.padding(padding)
        ) {

            AsyncImage(
                model = image,
                contentDescription = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            LazyColumn {
                items(deals) { deal ->
                    DealItem(deal, stores)
                }
            }
        }
    }
}

@Composable
fun DealsContent(
    title: String,
    image: String,
    deals: List<Deal>,
    stores: List<Store>
) {
    Column {
        AsyncImage(
            model = image,
            contentDescription = title,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Text(title)

        LazyColumn {
            items(deals) {
                DealItem(it, stores)
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DealsScreenPreview() {
    DealsContent(
        title = "Cyberpunk 2077",
        image = "",
        deals = listOf(
            Deal("1", "3.99", "19.99", "80", "1")
        ),
        stores = listOf(
            Store("1", "Steam", "")
        )
    )
}