package ru.akimslava.hotelreservation.common.uikit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.akimslava.hotelreservation.common.theme.BackgroundLightGray

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PeculiaritiesFlowRow(
    peculiarities: List<String>,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        repeat(peculiarities.size) {
            Box(
                modifier = Modifier.background(
                    color = BackgroundLightGray,
                    shape = RoundedCornerShape(5.dp),
                ),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = peculiarities[it],
                    modifier = Modifier.padding(
                        vertical = 5.dp,
                        horizontal = 10.dp,
                    ),
                    color = Color.Gray,
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        }
    }
}