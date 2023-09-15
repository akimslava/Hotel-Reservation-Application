package ru.akimslava.hotelreservation.feature.reservation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.akimslava.hotelreservation.common.uikit.screens.DefaultErrorScreen
import ru.akimslava.hotelreservation.common.uikit.screens.DefaultLoadingScreen
import ru.akimslava.hotelreservation.feature.reservation.ui.models.ReservationUiState
import ru.akimslava.hotelreservation.feature.reservation.ui.models.ReservationViewModel
import ru.akimslava.hotelreservation.feature.reservation.ui.screens.SuccessScreen

@Composable
internal fun ReservationLayout(
    reservationUrl: String,
    onPayClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ReservationViewModel = viewModel(
        factory = ReservationViewModel.MyViewModelProvider(reservationUrl),
    ),
) {
    val uiState = viewModel.uiState.collectAsState()
    when (uiState.value) {
        is ReservationUiState.Success -> {
            SuccessScreen(
                reservation = (
                    uiState.value as ReservationUiState.Success
                ).reservation,
                onPayClick = onPayClick,
                viewModel = viewModel,
                modifier = modifier,
            )
        }

        is ReservationUiState.Loading -> {
            DefaultLoadingScreen()
        }

        is ReservationUiState.Error -> {
            DefaultErrorScreen(onRefresh = viewModel::getReservation)
        }
    }
}