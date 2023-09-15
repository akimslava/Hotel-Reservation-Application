package ru.akimslava.hotelreservation.feature.hotel.ui.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import ru.akimslava.hotelreservation.data.service.HotelRepository
import ru.akimslava.hotelreservation.data.container.HotelApplication
import ru.akimslava.hotelreservation.data.service.models.hotel.HotelData
import java.io.IOException

internal sealed interface HotelUiState {
    data class Success(val hotel: HotelData): HotelUiState
    object Loading : HotelUiState
    object Error : HotelUiState
}

internal class HotelViewModel(
    private val hotelRepository: HotelRepository,
    private val hotelUrl: String,
): ViewModel() {
    private val _uiState = MutableStateFlow<HotelUiState>(HotelUiState.Loading)
    val uiState: StateFlow<HotelUiState> = _uiState

    init {
        getHotel()
    }

    internal fun getHotel() {
        viewModelScope.launch {
            _uiState.value = HotelUiState.Loading
            _uiState.value = try {
                HotelUiState.Success(hotelRepository.getHotel(hotelUrl))
            } catch (e: IOException) {
                HotelUiState.Error
            } catch (e: HttpException) {
                HotelUiState.Error
            }
        }
    }

    internal class MyProvider(
        private val hotelUrl: String,
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(
            modelClass: Class<T>,
            extras: CreationExtras,
        ): T {
            return HotelViewModel(
                hotelRepository = (
                    extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                            as HotelApplication
                ).container.hotelRepository,
                hotelUrl = hotelUrl,
            ) as T
        }
    }
}