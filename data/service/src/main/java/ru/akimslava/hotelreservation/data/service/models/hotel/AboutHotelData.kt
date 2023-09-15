package ru.akimslava.hotelreservation.data.service.models.hotel

import kotlinx.serialization.Serializable

@Serializable
data class AboutHotelData(
    val description: String,
    val peculiarities: List<String>,
)