package com.example.cheapsharkreader.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

    val priceText = if (deal.price == "0.00" || deal.price == "0") {
        "Free"
    } else {
        "$${deal.price}"
    }

    val savingsInt = deal.savings.toFloatOrNull()?.toInt() ?: 0

    val savingsText = when {
        savingsInt == 100 -> "Discount"
        savingsInt == 0 -> "Full Price"
        else -> "$savingsInt%"
    }

    val savingsColor = when {
        savingsInt == 100 -> Color.Green
        savingsInt == 0 -> Color.Red
        else -> Color.Unspecified
    }

    val priceColor = if (priceText == "Free") Color.Green else Color.Unspecified

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        AsyncImage(
            model = store?.icon,
            contentDescription = store?.name,
            modifier = Modifier
                .size(48.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column {

            Text(text = store?.name ?: "Unknown store")

            Text(
                text = "Price: $priceText",
                color = priceColor
            )

            Text(text = "Retail: $${deal.retailPrice}")

            Text(
                text = "Savings: $savingsText",
                color = savingsColor
            )
        }
    }
}