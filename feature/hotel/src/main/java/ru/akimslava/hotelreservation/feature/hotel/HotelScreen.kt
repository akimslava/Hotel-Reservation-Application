package ru.akimslava.hotelreservation.feature.hotel

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.akimslava.hotelreservation.common.theme.HotelReservationTheme
import ru.akimslava.hotelreservation.common.uikit.bars.DefaultTopBar
import ru.akimslava.hotelreservation.feature.hotel.ui.screens.HotelLayout

@Composable
fun HotelScreen(
    hotelUrl: String,
    onClick: (String) -> Unit,
) {
    Scaffold(
        topBar = { DefaultTopBar(label = stringResource(R.string.Hotel)) },
    ) {
        HotelLayout(
            hotelUrl = hotelUrl,
            onClick = onClick,
            modifier = Modifier.padding(it),
        )
    }
}

@Preview
@Composable
private fun HotelScreenPreview() {
    HotelReservationTheme {
        HotelScreen(
            hotelUrl = "",
            onClick = {},
        )
    }
}