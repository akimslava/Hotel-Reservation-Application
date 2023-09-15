package ru.akimslava.hotelreservation.data.service.models.reservation

import kotlinx.serialization.Serializable

@Serializable
data class ReservationData(
    val id: Int,
    val hotelName: String,
    val hotelAddress: String,
    val hotelRating: Int,
    val ratingName: String,
    val departure: String,
    val arrivalCountry: String,
    val tourDateStart: String,
    val tourDateStop: String,
    val numberOfNights: Int,
    val room: String,
    val nutrition: String,
    val tourPrice: Int,
    val fuelCharge: Int,
    val serviceCharge: Int,
)