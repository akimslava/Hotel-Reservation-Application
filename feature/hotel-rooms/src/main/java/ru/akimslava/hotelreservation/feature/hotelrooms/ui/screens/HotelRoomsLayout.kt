package ru.akimslava.hotelreservation.feature.hotelrooms.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.akimslava.hotelreservation.common.uikit.screens.DefaultErrorScreen
import ru.akimslava.hotelreservation.common.uikit.screens.DefaultLoadingScreen
import ru.akimslava.hotelreservation.feature.hotelrooms.ui.models.RoomsUiState
import ru.akimslava.hotelreservation.feature.hotelrooms.ui.models.RoomsViewModel

@Composable
internal fun HotelRoomsLayout(
    roomsUrl: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RoomsViewModel = viewModel(
        factory = RoomsViewModel.MyProvider(roomsUrl = roomsUrl),
    ),
) {
    val uiState = viewModel.uiState.collectAsState()
    when (uiState.value) {
        is RoomsUiState.Success -> {
            SuccessScreen(
                rooms = (uiState.value as RoomsUiState.Success).rooms,
                onClick = onClick,
                modifier = modifier,
            )
        }

        is RoomsUiState.Loading -> {
            DefaultLoadingScreen()
        }

        is RoomsUiState.Error -> {
            DefaultErrorScreen(onRefresh = viewModel::getRooms)
        }
    }
}