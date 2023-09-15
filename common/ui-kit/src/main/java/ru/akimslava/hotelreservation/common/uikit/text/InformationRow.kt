package ru.akimslava.hotelreservation.common.uikit.text

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import ru.akimslava.hotelreservation.common.theme.Black
import ru.akimslava.hotelreservation.common.theme.Gray
import ru.akimslava.hotelreservation.common.theme.Typography

@Composable
fun TourInformationRow(
    title: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = title,
            modifier = Modifier.weight(1f),
            color = Gray,
            textAlign = TextAlign.Start,
            style = Typography.bodyMedium,
        )
        Text(
            text = value,
            modifier = Modifier.weight(1.5f),
            style = Typography.bodyMedium,
        )
    }
}

@Composable
fun PriceInformationRow(
    title: String,
    value: String,
    modifier: Modifier = Modifier,
    valueColor: Color = Black,
    valueStyle: TextStyle = Typography.bodyMedium,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = title,
            color = Gray,
            style = Typography.bodyMedium,
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = value,
            color = valueColor,
            textAlign = TextAlign.End,
            style = valueStyle,
        )
    }
}