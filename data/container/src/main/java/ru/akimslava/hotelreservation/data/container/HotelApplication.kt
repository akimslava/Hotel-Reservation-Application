package ru.akimslava.hotelreservation.data.container

import android.app.Application

class HotelApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}