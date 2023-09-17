package ru.akimslava.hotelreservation.common.uikit.intents

import android.content.Intent
import android.net.Uri

fun openMapIntent(location: String): Intent {
    val gMapsUri = "http://maps.google.co.in/maps?q=$location"
    return Intent(Intent.ACTION_VIEW, Uri.parse(gMapsUri))
}