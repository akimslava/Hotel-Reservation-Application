package ru.akimslava.hotelreservation.data.service

import ru.akimslava.hotelreservation.data.service.models.hotel.HotelData
import ru.akimslava.hotelreservation.data.service.models.reservation.ReservationData
import ru.akimslava.hotelreservation.data.service.models.rooms.RoomsData

interface HotelRepository {
    suspend fun getHotel(hotelUrl: String): HotelData
    suspend fun getRooms(roomsUrl: String): RoomsData
    suspend fun getReservation(reservationUrl: String): ReservationData
}

class NetworkHotelRepository(
    private val hotelApiService: HotelApiService,
): HotelRepository {
    override suspend fun getHotel(hotelUrl: String): HotelData =
        hotelApiService.getHotel(hotelUrl)

    override suspend fun getRooms(roomsUrl: String): RoomsData =
        hotelApiService.getRooms(roomsUrl)

    override suspend fun getReservation(reservationUrl: String): ReservationData =
        hotelApiService.getReservation(reservationUrl)
}