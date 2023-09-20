package ru.akimslava.hotelreservation

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import ru.akimslava.hotelreservation.common.theme.HotelReservationTheme
import ru.akimslava.hotelreservation.ui.HotelReservationScreens
import ru.akimslava.hotelreservation.ui.Navigation

class HotelReservationScreenNavigationTest {
    @get:Rule
    internal val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var navController: TestNavHostController
    private val timeoutMillis = 10_000L

    @Before
    fun setupHotelReservationNavController() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            HotelReservationTheme {
                Navigation(navController = navController)
            }
        }
    }

    @Test
    fun hotelNavHost_verifyStartDestination() {
        navController.assertCurrentRouteName(HotelReservationScreens.HotelScreen.name)
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun hotelNavHost_verifyBackNavigationNotShownOnHotelScreen() {
        composeTestRule.waitUntilAtLeastOneExists(
            matcher = hasText(
                text = composeTestRule.activity.getString(
                    ru.akimslava.hotelreservation.feature.hotel.R.string.Hotel,
                ),
            ),
            timeoutMillis = timeoutMillis,
        )
        composeTestRule.onNodeWithText(
            text = composeTestRule.activity.getString(
                ru.akimslava.hotelreservation.feature.hotel.R.string.Hotel,
            ),
        ).assertExists()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun hotelNavHost_clickChooseRoom_navigatesToRoomsScreen() {
        composeTestRule.waitUntilExactlyOneExists(
            matcher = hasText(
                text = composeTestRule.activity.getString(
                        ru.akimslava.hotelreservation.feature.hotel.R.string.about_hotel,
                ),
            ),
            timeoutMillis = timeoutMillis,
        )
        composeTestRule.onNodeWithText(
            text = composeTestRule.activity.getString(
                ru.akimslava.hotelreservation.feature.hotel.R.string.choose_room,
            ),
        ).assertHasClickAction().performScrollTo().performClick()
        composeTestRule.waitUntilAtLeastOneExists(
            matcher = hasText(
                text = composeTestRule.activity.getString(
                    ru.akimslava.hotelreservation.feature.hotelrooms.R.string.more_room_details,
                ),
            ),
            timeoutMillis = timeoutMillis,
        )
        navController.assertCurrentRouteName(
            expectedRouteName = "${HotelReservationScreens.HotelRoomsScreen.name}/{title}",
        )
    }

    @OptIn(ExperimentalTestApi::class)
    fun goToRoomsScreen() {
        composeTestRule.waitUntilExactlyOneExists(
            matcher = hasText(
                text = composeTestRule.activity.getString(
                    ru.akimslava.hotelreservation.feature.hotel.R.string.about_hotel,
                ),
            ),
            timeoutMillis = timeoutMillis,
        )
        composeTestRule.onNodeWithText(
            text = composeTestRule.activity.getString(
                ru.akimslava.hotelreservation.feature.hotel.R.string.choose_room,
            ),
        ).assertHasClickAction().performScrollTo().performClick()
        composeTestRule.waitUntilAtLeastOneExists(
            matcher = hasText(
                text = composeTestRule.activity.getString(
                    ru.akimslava.hotelreservation.feature.hotelrooms.R.string.more_room_details,
                ),
            ),
            timeoutMillis = timeoutMillis,
        )
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun hotelNavHost_clickChooseRoomAtRooms_navigatesToReservationScreen() {
        goToRoomsScreen()
        composeTestRule.onAllNodes(
            matcher = hasText(
                text = composeTestRule.activity.getString(
                    ru.akimslava.hotelreservation.feature.hotelrooms.R.string.choose_room,
                ),
            )
        )[0].assertExists().performScrollTo().performClick()
        composeTestRule.waitUntilAtLeastOneExists(
            matcher = hasText(
                text = composeTestRule.activity.getString(
                    ru.akimslava.hotelreservation.feature.reservation.R.string.hotel,
                ),
            ),
            timeoutMillis = timeoutMillis,
        )
        navController.assertCurrentRouteName(
            expectedRouteName = HotelReservationScreens.RoomReservationScreen.name,
        )
    }

    @OptIn(ExperimentalTestApi::class)
    fun goToReservation() {
        goToRoomsScreen()
        composeTestRule.onAllNodes(
            matcher = hasText(
                text = composeTestRule.activity.getString(
                    ru.akimslava.hotelreservation.feature.hotelrooms.R.string.choose_room,
                ),
            )
        )[0].assertExists().performScrollTo().performClick()
        composeTestRule.waitUntilAtLeastOneExists(
            matcher = hasText(
                text = composeTestRule.activity.getString(
                    ru.akimslava.hotelreservation.feature.reservation.R.string.hotel,
                ),
            ),
            timeoutMillis = timeoutMillis,
        )
    }

    @Test
    fun hotelNavHost_navigateBack_navigateToRoomsScreen() {
        goToReservation()
        composeTestRule.runOnUiThread { navController.navigateUp() }
        navController.assertCurrentRouteName(
            expectedRouteName = "${HotelReservationScreens.HotelRoomsScreen.name}/{title}",
        )
    }
}