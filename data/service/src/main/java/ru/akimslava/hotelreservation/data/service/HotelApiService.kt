package ru.akimslava.hotelreservation.data.service

import retrofit2.http.GET
import retrofit2.http.Url
import ru.akimslava.hotelreservation.data.service.models.hotel.HotelData
import ru.akimslava.hotelreservation.data.service.models.reservation.ReservationData
import ru.akimslava.hotelreservation.data.service.models.rooms.RoomsData

interface HotelApiService {
    @GET
    suspend fun getHotel(@Url hotelUrl: String): HotelData

    @GET
    suspend fun getRooms(@Url roomsUrl: String): RoomsData

    @GET
    suspend fun getReservation(@Url reservationUrl: String): ReservationData
}