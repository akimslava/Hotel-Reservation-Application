package ru.akimslava.hotelreservation.data.service.models.rooms

import kotlinx.serialization.Serializable

@Serializable
data class RoomsData(
    val rooms: List<RoomData>,
)