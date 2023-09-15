package ru.akimslava.hotelreservation.data.service.models.rooms

import kotlinx.serialization.Serializable

@Serializable
data class RoomData(
    val id: Int,
    val name: String,
    val price: Int,
    val pricePer: String,
    val peculiarities: List<String>,
    val imageUrls: List<String>,
)