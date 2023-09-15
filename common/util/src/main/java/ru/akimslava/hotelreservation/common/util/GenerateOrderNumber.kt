package ru.akimslava.hotelreservation.common.util

fun generateOrderNumber(): Int {
    val start = 100_000
    val end = 999_999
    return (start..end).random()
}