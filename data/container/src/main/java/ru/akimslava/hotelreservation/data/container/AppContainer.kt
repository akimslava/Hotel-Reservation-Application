package ru.akimslava.hotelreservation.data.container

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy
import retrofit2.Retrofit
import okhttp3.MediaType.Companion.toMediaType
import ru.akimslava.hotelreservation.data.service.HotelApiService
import ru.akimslava.hotelreservation.data.service.HotelRepository
import ru.akimslava.hotelreservation.data.service.NetworkHotelRepository

interface AppContainer {
    val hotelRepository: HotelRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://mocki.io/v1/"

    @OptIn(ExperimentalSerializationApi::class)
    private val jsonConverter = Json {
        ignoreUnknownKeys = true
        namingStrategy = JsonNamingStrategy.SnakeCase
    }

    private val hotelRetrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(
            jsonConverter.asConverterFactory(
                "application/media".toMediaType()
            )
        )
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: HotelApiService by lazy {
        hotelRetrofit.create(HotelApiService::class.java)
    }

    override val hotelRepository: HotelRepository by lazy {
        NetworkHotelRepository(retrofitService)
    }
}