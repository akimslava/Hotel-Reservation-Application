package ru.akimslava.hotelreservation.feature.hotel.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.akimslava.hotelreservation.common.uikit.screens.DefaultErrorScreen
import ru.akimslava.hotelreservation.common.uikit.screens.DefaultLoadingScreen
import ru.akimslava.hotelreservation.feature.hotel.ui.model.HotelUiState
import ru.akimslava.hotelreservation.feature.hotel.ui.model.HotelViewModel

@Composable
internal fun HotelLayout(
    hotelUrl: String,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HotelViewModel = viewModel(
        factory = HotelViewModel.MyProvider(hotelUrl = hotelUrl),
    ),
) {
    val uiState = viewModel.uiState.collectAsState()
    when (uiState.value) {
        is HotelUiState.Success -> {
            SuccessScreen(
                hotel = (uiState.value as HotelUiState.Success).hotel,
                onClick = onClick,
                modifier = modifier,
            )
        }

        is HotelUiState.Loading -> {
            DefaultLoadingScreen()
        }

        is HotelUiState.Error -> {
            DefaultErrorScreen(onRefresh = viewModel::getHotel)
        }
    }
}