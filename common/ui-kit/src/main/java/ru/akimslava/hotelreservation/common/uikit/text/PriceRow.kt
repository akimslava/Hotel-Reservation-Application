package ru.akimslava.hotelreservation.common.uikit.text

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.akimslava.hotelreservation.common.theme.Gray
import ru.akimslava.hotelreservation.common.util.formatPrice

@Composable
fun PriceRow(
    price: Int,
    priceFormat: String,
    pricePer: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom,
    ) {
        Text(
            text = priceFormat.format(formatPrice(price)),
            modifier = Modifier.padding(end = 8.dp),
            style = MaterialTheme.typography.displayLarge,
        )
        Text(
            text = pricePer.lowercase(),
            modifier = Modifier.padding(bottom = 3.dp),
            color = Gray,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}