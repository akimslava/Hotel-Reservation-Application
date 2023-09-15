package ru.akimslava.hotelreservation.feature.reservation.ui.models

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import ru.akimslava.hotelreservation.common.util.checkDateCorrection
import ru.akimslava.hotelreservation.data.container.HotelApplication
import ru.akimslava.hotelreservation.data.service.HotelRepository
import ru.akimslava.hotelreservation.data.service.models.reservation.ReservationData
import java.io.IOException

internal sealed interface ReservationUiState {
    data class Success(val reservation: ReservationData) : ReservationUiState
    object Loading : ReservationUiState
    object Error : ReservationUiState
}

private const val MAX_PHONE_NUMBER_LENGTH = 10
private val GENERAL_EMAIL_REGEX = """(?:[a-z0-9!#${'$'}%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#${'$'}%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)])""".trimIndent().toRegex()

internal class ReservationViewModel(
    private val hotelRepository: HotelRepository,
    private val reservationUrl: String,
) : ViewModel() {
    private val _uiState = MutableStateFlow<ReservationUiState>(ReservationUiState.Loading)
    val uiState: StateFlow<ReservationUiState> = _uiState

    init {
        getReservation()
    }

    internal fun getReservation() {
        viewModelScope.launch {
            _uiState.value = ReservationUiState.Loading
            _uiState.value = try {
                ReservationUiState.Success(
                    hotelRepository.getReservation(reservationUrl)
                )
            } catch (e: HttpException) {
                ReservationUiState.Error
            } catch (e: IOException) {
                ReservationUiState.Error
            }
        }
    }

    private val _phoneNumber = mutableStateOf("")
    private val _userEmail = mutableStateOf("")
    private val _isEmailClicked = mutableStateOf(false)
    private val _isEmailUnfocused = mutableStateOf(false)

    internal val isEmailInvalid = mutableStateOf(false)
    private var _tourists = mutableStateListOf(TouristInformation())
    val tourists: List<TouristInformation> = _tourists

    private val _triedPay = mutableStateOf(false)

    internal fun getPhoneNumber(): String = _phoneNumber.value

    internal fun updatePhoneNumber(newPhoneNumber: String) {
        if (!newPhoneNumber.isDigitsOnly()) return
        _phoneNumber.value = newPhoneNumber.take(MAX_PHONE_NUMBER_LENGTH)
    }

    internal fun isPhoneNumberCorrect(): Boolean = !_triedPay.value
            || _phoneNumber.value.length == MAX_PHONE_NUMBER_LENGTH

    internal fun getUserEmail(): String = _userEmail.value
    internal fun emailFieldClicked() { _isEmailClicked.value = true }
    internal fun emailFieldUnfocused() {
        _isEmailUnfocused.value = _isEmailClicked.value
    }

    internal fun updateUserEmail(newUserEmail: String) {
        _userEmail.value = newUserEmail
    }

    internal fun updateEmailCorrection() {
        isEmailInvalid.value = (
                    _isEmailClicked.value
                    && _isEmailUnfocused.value
                    || _triedPay.value
                ) && !GENERAL_EMAIL_REGEX.matches(_userEmail.value)
    }
    internal fun addTourist() {
        _tourists.add(TouristInformation())
    }

    internal fun updateTourist(tourist: TouristInformation, index: Int) {
        if (index > _tourists.lastIndex) return
        _tourists[index] = _tourists[index].copy(
            name = tourist.name,
            surname = tourist.surname,
            dateOfBirth = tourist.dateOfBirth,
            citizenship = tourist.citizenship,
            passportNumber = tourist.passportNumber,
            passportValidity = tourist.passportValidity,
        )
    }

    internal fun isTriedPay(): Boolean = _triedPay.value

    internal fun tryPay() {
        tourists[0].isNameCorrect()
        _triedPay.value = true
        updateEmailCorrection()
    }

    private fun isTouristCorrect(tourist: TouristInformation): Boolean =
        tourist.isNameCorrect()
                && tourist.isSurnameCorrect()
                && tourist.isDateOfBirthCorrect()
                && tourist.isCitizenshipCorrect()
                && tourist.isPassportNumberCorrect()
                && tourist.isPassportValidityCorrect()

    private fun areTouristsCorrect(): Boolean =
        tourists.all { tourist -> isTouristCorrect(tourist) }

    internal fun checkTouristsCorrection(): Boolean {
        return isPhoneNumberCorrect() && !isEmailInvalid.value && areTouristsCorrect()
    }

    internal data class TouristInformation(
        val name: String = "",
        val surname: String = "",
        val dateOfBirth: String = "",
        val citizenship: String = "",
        val passportNumber: String = "",
        val passportValidity: String = "",
    ) {
        internal fun isNameCorrect(): Boolean =
            this.name.isNotBlank()

        internal fun isSurnameCorrect(): Boolean =
            this.surname.isNotBlank()

        internal fun isDateOfBirthCorrect(): Boolean =
            checkDateCorrection(this.dateOfBirth)

        internal fun isCitizenshipCorrect(): Boolean =
            this.citizenship.isNotBlank()

        internal fun isPassportNumberCorrect(): Boolean =
            this.passportNumber.isNotBlank()

        internal fun isPassportValidityCorrect(): Boolean =
            checkDateCorrection(this.passportValidity)
    }

    internal class MyViewModelProvider(
        private val reservationUrl: String,
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(
            modelClass: Class<T>,
            extras: CreationExtras,
        ): T {
            return ReservationViewModel(
                hotelRepository = (
                    extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                        as HotelApplication
                ).container.hotelRepository,
                reservationUrl = reservationUrl,
            ) as T
        }
    }
}

