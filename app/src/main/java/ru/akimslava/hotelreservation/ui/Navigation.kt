package ru.akimslava.hotelreservation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ru.akimslava.hotelreservation.R
import ru.akimslava.hotelreservation.feature.hotel.HotelScreen
import ru.akimslava.hotelreservation.feature.hotelrooms.HotelRoomsScreen
import ru.akimslava.hotelreservation.feature.paid.PaidScreen
import ru.akimslava.hotelreservation.feature.reservation.ReservationScreen

@Composable
internal fun Navigation(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = HotelReservationScreens.HotelScreen.name,
    ) {
        composable(route = HotelReservationScreens.HotelScreen.name) {
            HotelScreen(
                hotelUrl = "b624ffad-f19e-474b-bfb1-cb98cc8fc0e6",
                onClick = {
                    navController.navigate(
                        route = "${HotelReservationScreens.HotelRoomsScreen.name}/$it",
                    )
                },
            )
        }
        composable(
            route = "${HotelReservationScreens.HotelRoomsScreen.name}/{title}",
            arguments = listOf(
                navArgument(name = "title") {
                    type = NavType.StringType
                },
            ),
        ) {
            HotelRoomsScreen(
                roomsUrl = "ac82835e-7114-434f-af8b-caf2c170b274",
                title = it.arguments
                    ?.getString("title")
                    ?: stringResource(R.string.undefined),
                onClick = {
                    navController.navigate(
                        route = HotelReservationScreens.RoomReservationScreen.name,
                    )
                },
                navigateUp = navController::navigateUp,
            )
        }
        composable(route = HotelReservationScreens.RoomReservationScreen.name) {
            ReservationScreen(
                reservationUrl = "596e03a9-285e-43a1-b007-fb82a19df8bd",
                navigateUp = navController::navigateUp,
                onPayClick = {
                    navController.navigate(
                        route = HotelReservationScreens.PaidScreen.name,
                    )
                },
            )
        }
        composable(route = HotelReservationScreens.PaidScreen.name) {
            PaidScreen(
                onSubmitClick = {
                    navController.navigate(
                        route = HotelReservationScreens.HotelScreen.name,
                    ) {
                        popUpTo(HotelReservationScreens.HotelScreen.name) {
                            inclusive = true
                        }
                    }
                },
                navigateUp = navController::navigateUp,
            )
        }
    }
}