package ru.akimslava.hotelreservation.feature.reservation.ui.screens

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import ru.akimslava.hotelreservation.common.theme.BackgroundBlue
import ru.akimslava.hotelreservation.common.theme.BackgroundGray
import ru.akimslava.hotelreservation.common.theme.BackgroundGrayVariant
import ru.akimslava.hotelreservation.common.theme.Blue
import ru.akimslava.hotelreservation.common.theme.ErrorColor
import ru.akimslava.hotelreservation.common.theme.GrayVariant
import ru.akimslava.hotelreservation.common.uikit.buttons.BottomBlueNavigateButton
import ru.akimslava.hotelreservation.common.uikit.placeholderstransformation.PhonePlaceholderTransformation
import ru.akimslava.hotelreservation.common.uikit.text.PriceInformationRow
import ru.akimslava.hotelreservation.common.uikit.RatingCard
import ru.akimslava.hotelreservation.common.uikit.intents.openMapIntent
import ru.akimslava.hotelreservation.common.uikit.placeholderstransformation.DatePlaceholderTransformation
import ru.akimslava.hotelreservation.common.uikit.text.TourInformationRow
import ru.akimslava.hotelreservation.common.uikit.text.TouristRowTemplate
import ru.akimslava.hotelreservation.common.util.formatPrice
import ru.akimslava.hotelreservation.common.util.intToRussianWord
import ru.akimslava.hotelreservation.data.service.models.reservation.ReservationData
import ru.akimslava.hotelreservation.feature.reservation.R
import ru.akimslava.hotelreservation.feature.reservation.ui.models.ReservationViewModel

@Composable
internal fun SuccessScreen(
    reservation: ReservationData,
    onPayClick: () -> Unit,
    viewModel: ReservationViewModel,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()
    val tourists = viewModel.tourists
    Column(
        modifier = modifier
            .verticalScroll(state = scrollState)
            .background(color = BackgroundGray),
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        HotelDataPart(
            reservation = reservation,
            modifier = Modifier.padding(16.dp),
        )
        Spacer(modifier = Modifier.height(8.dp))
        TourInformation(
            reservation = reservation,
            modifier = Modifier.padding(16.dp),
        )
        Spacer(modifier = Modifier.height(8.dp))
        ConsumerInformation(
            viewModel = viewModel,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(8.dp))
        TouristsInformation(
            tourists = tourists,
            addTourist = viewModel::addTourist,
            isTriedPay = viewModel::isTriedPay,
            updateTourist = viewModel::updateTourist,
        )
        Spacer(modifier = Modifier.height(8.dp))
        PriceInformation(
            reservation = reservation,
            modifier = Modifier.padding(16.dp),
        )
        Spacer(modifier = Modifier.height(8.dp))
        var totalPrice: Int
        reservation.apply {
            totalPrice = tourPrice + fuelCharge + serviceCharge
        }
        BottomBlueNavigateButton(
            text = stringResource(
                id = R.string.pay_button_format,
                formatPrice(totalPrice),
            ),
            onClick = {
                viewModel.tryPay()
                if (viewModel.checkTouristsCorrection()) onPayClick()
            },
        )
    }
}

@Composable
private fun HotelDataPart(
    reservation: ReservationData,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val openMapsIntent = openMapIntent(location = reservation.hotelAddress)
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
    ) {
        Column(
            modifier = modifier,
        ) {
            RatingCard(
                rating = reservation.hotelRating,
                ratingName = reservation.ratingName,
                modifier = Modifier.padding(bottom = 8.dp),
            )
            Text(
                text = reservation.hotelName,
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.displayMedium,
            )
            Text(
                text = reservation.hotelAddress,
                modifier = Modifier.clickable {
                    context.startActivity(openMapsIntent)
                },
                color = Blue,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}

@Composable
private fun TourInformation(
    reservation: ReservationData,
    modifier: Modifier = Modifier,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
    ) {
        Column(
            modifier = modifier,
        ) {
            TourInformationRow(
                title = stringResource(id = R.string.departure_from),
                value = reservation.departure,
                modifier = Modifier.padding(bottom = 16.dp),
            )
            TourInformationRow(
                title = stringResource(id = R.string.country_city),
                value = reservation.arrivalCountry,
                modifier = Modifier.padding(bottom = 16.dp),
            )
            TourInformationRow(
                title = stringResource(id = R.string.dates),
                value = stringResource(
                    id = R.string.tour_dates_format,
                    reservation.tourDateStart,
                    reservation.tourDateStop,
                ),
                modifier = Modifier.padding(bottom = 16.dp),
            )
            TourInformationRow(
                title = stringResource(id = R.string.count_of_nights),
                value = stringResource(
                    id = R.string.number_of_nights_format,
                    reservation.numberOfNights,
                ),
                modifier = Modifier.padding(bottom = 16.dp),
            )
            TourInformationRow(
                title = stringResource(id = R.string.hotel),
                value = reservation.hotelName,
                modifier = Modifier.padding(bottom = 16.dp),
            )
            TourInformationRow(
                title = stringResource(id = R.string.room),
                value = reservation.room,
                modifier = Modifier.padding(bottom = 16.dp),
            )
            TourInformationRow(
                title = stringResource(id = R.string.nutrition),
                value = reservation.nutrition,
            )
        }
    }
}

@Composable
private fun ConsumerInformation(
    viewModel: ReservationViewModel,
    modifier: Modifier = Modifier,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
    ) {
        Column(modifier = modifier) {
            Text(
                text = stringResource(id = R.string.information_about_consumer),
                modifier = Modifier.padding(bottom = 20.dp),
                style = MaterialTheme.typography.displayMedium,
            )
            TextField(
                value = viewModel.getPhoneNumber(),
                onValueChange = viewModel::updatePhoneNumber,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                textStyle = MaterialTheme.typography.bodyMedium,
                label = {
                    Text(
                        text = stringResource(id = R.string.phone_number),
                        color = BackgroundGrayVariant,
                        style = MaterialTheme.typography.titleSmall,
                    )
                },
                isError = !viewModel.isPhoneNumberCorrect(),
                visualTransformation = {
                    PhonePlaceholderTransformation
                        .filter(
                            AnnotatedString(
                                text = viewModel.getPhoneNumber(),
                            ),
                        )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Next,
                ),
                singleLine = true,
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = GrayVariant,
                    unfocusedContainerColor = GrayVariant,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    errorContainerColor = ErrorColor,
                    errorIndicatorColor = Color.Transparent,
                ),
            )
            TouristTextField(
                label = stringResource(id = R.string.email),
                value = viewModel.getUserEmail(),
                onValueChange = {
                    viewModel.updateUserEmail(it)
                    viewModel.updateEmailCorrection()
                },
                modifier = Modifier.padding(bottom = 8.dp)
                    .onFocusChanged {
                        if (it.isFocused) {
                            viewModel.emailFieldClicked()
                        } else {
                            viewModel.emailFieldUnfocused()
                            viewModel.updateEmailCorrection()
                        }
                    },
                isError = viewModel.isEmailInvalid.value,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done,
            )
            Text(
                text = stringResource(id = R.string.mail_privacy_notice),
                style = MaterialTheme.typography.bodySmall,
            )
        }

    }
}

@Composable
private fun TouristsInformation(
    tourists: List<ReservationViewModel.TouristInformation>,
    addTourist: () -> Unit,
    isTriedPay: () -> Boolean,
    updateTourist: (ReservationViewModel.TouristInformation, Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        repeat(tourists.size) {
            TouristCard(
                tourist = tourists[it],
                touristNumber = it,
                isTriedPay = isTriedPay,
                updateTourist = updateTourist,
                isCardExpanded = mutableStateOf(true),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        TouristRowTemplate(
            text = stringResource(id = R.string.add_tourist),
            painter = painterResource(id = R.drawable.add_icon),
            background = Blue,
            onButtonClick = addTourist,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        )
    }
}

@Composable
private fun TouristCard(
    tourist: ReservationViewModel.TouristInformation,
    touristNumber: Int,
    isTriedPay: () -> Boolean,
    updateTourist: (ReservationViewModel.TouristInformation, Int) -> Unit,
    isCardExpanded: MutableState<Boolean>,
    modifier: Modifier = Modifier,
) {
    val isExpanded = remember { isCardExpanded }
    val icon: Int
    val onClick: () -> Unit
    var content: @Composable (AnimatedVisibilityScope.() -> Unit) = {}
    if (isExpanded.value) {
        onClick = { isExpanded.value = false }
        icon = R.drawable.arrow_up_ios
        content = {
            Column {
                TouristTextField(
                    label = stringResource(id = R.string.name),
                    value = tourist.name,
                    onValueChange = {
                        updateTourist(tourist.copy(name = it), touristNumber)
                    },
                    modifier = Modifier.padding(top = 20.dp, bottom = 8.dp),
                    isError = !tourist.isNameCorrect() && isTriedPay(),
                )
                TouristTextField(
                    label = stringResource(id = R.string.surname),
                    value = tourist.surname,
                    onValueChange = {
                        updateTourist(tourist.copy(surname = it), touristNumber)
                    },
                    modifier = Modifier.padding(bottom = 8.dp),
                    isError = !tourist.isSurnameCorrect() && isTriedPay(),
                )
                TouristTextField(
                    label = stringResource(id = R.string.date_of_birth),
                    value = tourist.dateOfBirth,
                    onValueChange = {
                        if (it.isDigitsOnly()) {
                            updateTourist(
                                tourist.copy(dateOfBirth = it.take(8)),
                                touristNumber,
                            )
                        }
                    },
                    modifier = Modifier.padding(bottom = 8.dp),
                    visualTransformation = DatePlaceholderTransformation,
                    isError = !tourist.isDateOfBirthCorrect() && isTriedPay(),
                    keyboardType = KeyboardType.Number,
                )
                TouristTextField(
                    label = stringResource(id = R.string.citizenship),
                    value = tourist.citizenship,
                    onValueChange = {
                        updateTourist(tourist.copy(citizenship = it), touristNumber)
                    },
                    modifier = Modifier.padding(bottom = 8.dp),
                    isError = !tourist.isCitizenshipCorrect() && isTriedPay(),
                )
                TouristTextField(
                    label = stringResource(id = R.string.passport_number),
                    value = tourist.passportNumber,
                    onValueChange = {
                        updateTourist(tourist.copy(passportNumber = it), touristNumber)
                    },
                    modifier = Modifier.padding(bottom = 8.dp),
                    isError = !tourist.isPassportNumberCorrect() && isTriedPay(),
                )
                TouristTextField(
                    label = stringResource(id = R.string.passport_validity),
                    value = tourist.passportValidity,
                    onValueChange = {
                        if (it.isDigitsOnly()) {
                            updateTourist(
                                tourist.copy(passportValidity = it.take(8)),
                                touristNumber,
                            )
                        }
                    },
                    isError = !tourist.isPassportValidityCorrect() && isTriedPay(),
                    visualTransformation = DatePlaceholderTransformation,
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done,
                )
            }
        }
    } else {
        onClick = { isExpanded.value = true }
        icon = R.drawable.arrow_down_ios
    }
    TouristRowTemplate(
        text = stringResource(
            id = R.string.ourist_number_format,
            intToRussianWord(touristNumber + 1),
        ),
        painter = painterResource(id = icon),
        background = BackgroundBlue,
        onButtonClick = onClick,
        modifier = modifier,
        isVisible = isExpanded.value,
        content = content,
    )
}

@Composable
private fun TouristTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
) {
    val focusManager = LocalFocusManager.current
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        textStyle = MaterialTheme.typography.bodyMedium,
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.titleSmall,
            )
        },
        visualTransformation = visualTransformation,
        isError = isError,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction,
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            }
        ),
        singleLine = true,
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = GrayVariant,
            unfocusedContainerColor = GrayVariant,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            errorContainerColor = ErrorColor,
            errorIndicatorColor = Color.Transparent,
        ),
    )
}

@Composable
private fun PriceInformation(
    reservation: ReservationData,
    modifier: Modifier = Modifier,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
    ) {
        Column(modifier = modifier) {
            PriceInformationRow(
                title = stringResource(id = R.string.tour),
                value = stringResource(
                    id = R.string.price_with_ruble,
                    formatPrice(reservation.tourPrice),
                ),
                modifier = Modifier.padding(bottom = 16.dp),
            )
            PriceInformationRow(
                title = stringResource(id = R.string.fuel_charge),
                value = stringResource(
                    id = R.string.price_with_ruble,
                    formatPrice(reservation.fuelCharge),
                ),
                modifier = Modifier.padding(bottom = 16.dp),
            )
            PriceInformationRow(
                title = stringResource(id = R.string.service_charge),
                value = stringResource(
                    id = R.string.price_with_ruble,
                    formatPrice(reservation.serviceCharge),
                ),
                modifier = Modifier.padding(bottom = 16.dp),
            )
            var totalPrice: Int
            reservation.apply {
                totalPrice = tourPrice + fuelCharge + serviceCharge
            }
            PriceInformationRow(
                title = stringResource(id = R.string.total_price),
                value = stringResource(
                    id = R.string.price_with_ruble,
                    formatPrice(totalPrice),
                ),
                valueColor = Blue,
                valueStyle = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}