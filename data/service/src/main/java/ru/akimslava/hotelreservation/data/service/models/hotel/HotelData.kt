package ru.akimslava.hotelreservation.data.service.models.hotel

import kotlinx.serialization.Serializable

@Serializable
data class HotelData(
    val id: Int,
    val name: String,
    val address: String,
    val minimalPrice: Int,
    val priceForIt: String,
    val rating: Int,
    val ratingName: String,
    val imageUrls: List<String>,
    val aboutTheHotel: AboutHotelData,
)