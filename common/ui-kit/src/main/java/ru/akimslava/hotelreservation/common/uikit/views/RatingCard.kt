package ru.akimslava.hotelreservation.common.uikit

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.akimslava.hotelreservation.common.theme.HotelReservationTheme
import ru.akimslava.hotelreservation.common.theme.LightOrange
import ru.akimslava.hotelreservation.common.theme.Orange

@Composable
fun RatingCard(
    rating: Int,
    ratingName: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = LightOrange,
        ),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.star_rate),
                contentDescription = null,
                modifier = Modifier
                    .padding(
                        start = 10.dp,
                        end = 2.dp,
                    )
                    .padding(vertical = 5.dp),
                colorFilter = ColorFilter.tint(Orange),
            )
            Text(
                text = stringResource(
                    id = R.string.rating_format,
                    rating,
                    ratingName,
                ),
                modifier = Modifier
                    .padding(end = 10.dp)
                    .padding(vertical = 5.dp),
                color = Orange,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}

@Preview
@Composable
private fun RatingCardPreview() {
    HotelReservationTheme {
        RatingCard(rating = 5, ratingName = "Превосходно")
    }
}