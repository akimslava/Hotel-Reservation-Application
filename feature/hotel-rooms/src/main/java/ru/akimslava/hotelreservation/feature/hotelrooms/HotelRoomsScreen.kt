package ru.akimslava.hotelreservation.feature.hotelrooms

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.akimslava.hotelreservation.common.uikit.bars.DefaultTopBar
import ru.akimslava.hotelreservation.feature.hotelrooms.ui.screens.HotelRoomsLayout

@Composable
fun HotelRoomsScreen(
    roomsUrl: String,
    title: String,
    navigateUp: () -> Unit,
    onClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            DefaultTopBar(
                label = title,
                navigateUp = navigateUp,
                canNavigateUp = true,
            )
        },
    ) {
        HotelRoomsLayout(
            roomsUrl = roomsUrl,
            onClick = onClick,
            modifier = Modifier.padding(it),
        )
    }
}