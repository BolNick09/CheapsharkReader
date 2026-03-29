package com.example.cheapsharkreader.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.cheapsharkreader.domain.model.Deal
import com.example.cheapsharkreader.domain.model.Store

@Composable
fun DealItem(
    deal: Deal,
    stores: List<Store>
) {
    val store = stores.find { it.id == deal.storeId }
    val savingsInt = deal.savings.toDoubleOrNull()?.toInt() ?: 0

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp)) {

            // 🖼 Иконка магазина
            AsyncImage(
                model = store?.icon,
                contentDescription = store?.name,
                modifier = Modifier.size(40.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {

                Text(
                    text = store?.name ?: "Unknown store",
                    style = MaterialTheme.typography.titleMedium
                )

                Text("Price: $ ${deal.price}")
                Text("Retail: $ ${deal.retailPrice}")
                Text("Savings: $savingsInt%")
            }
        }
    }
}