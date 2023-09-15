package ru.akimslava.hotelreservation.common.util

fun checkDateCorrection(date: String): Boolean {
    fun isLeap(year: Int): Boolean = year % 400 == 0
            || year % 4 == 0 && year % 100 != 0
    val dateParts: List<String> = date.split('.')
    if (
        dateParts.size != 3
        || dateParts.any { str -> str.any { it !in '0'..'9' } }
    ) {
        return false
    }
    val year = dateParts[2]
    if (
        year.length != 4
        || year.toInt() < 1940
    ) {
        return false
    }
    val month = dateParts[1]
    if (
        month.length !in 1..2
        || month.toInt() !in 1..12
    ) {
        return false
    }
    val day = dateParts[0]
    if (
        day.length !in 1..2
        || day.toInt() !in 1..31
        || when (month.toInt()) {
            4, 6, 9, 11 -> day.toInt() > 30
            2 -> day.toInt() > if (isLeap(year.toInt())) 29 else 28
            else -> false
        }
    ) {
        return false
    }
    return true
}