package ru.akimslava.hotelreservation.feature.hotelrooms.ui.models

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
import ru.akimslava.hotelreservation.data.service.models.rooms.RoomData
import java.io.IOException

internal sealed interface RoomsUiState {
    data class Success(val rooms: List<RoomData>) : RoomsUiState
    object Loading : RoomsUiState
    object Error : RoomsUiState
}

internal class RoomsViewModel(
    private val hotelRepository: HotelRepository,
    private val roomsUrl: String,
): ViewModel() {
    private val _uiState = MutableStateFlow<RoomsUiState>(RoomsUiState.Loading)
    val uiState: StateFlow<RoomsUiState> = _uiState

    init {
        getRooms()
    }

    internal fun getRooms() {
        viewModelScope.launch {
            _uiState.value = RoomsUiState.Loading
            _uiState.value = try {
                RoomsUiState.Success(hotelRepository.getRooms(roomsUrl).rooms)
            } catch (e: HttpException) {
                RoomsUiState.Error
            } catch (e: IOException) {
                RoomsUiState.Error
            }
        }
    }

    internal class MyProvider(
        private val roomsUrl: String,
    ): ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(
            modelClass: Class<T>,
            extras: CreationExtras,
        ): T {
            return RoomsViewModel(
                hotelRepository = (
                    extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                            as HotelApplication
                ).container.hotelRepository,
                roomsUrl = roomsUrl,
            ) as T
        }
    }
}