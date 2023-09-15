package ru.akimslava.hotelreservation.feature.reservation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.akimslava.hotelreservation.common.uikit.bars.DefaultTopBar
import ru.akimslava.hotelreservation.feature.reservation.ui.ReservationLayout

@Composable
fun ReservationScreen(
    reservationUrl: String,
    navigateUp: () -> Unit,
    onPayClick: () -> Unit,
) {
    Scaffold(
        topBar = {
             DefaultTopBar(
                 label = stringResource(id = R.string.reservation),
                 navigateUp = navigateUp,
                 canNavigateUp = true,
             )
        },
    ) {
        ReservationLayout(
            reservationUrl = reservationUrl,
            onPayClick = onPayClick,
            modifier = Modifier.padding(it),
        )
    }
}