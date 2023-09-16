package ru.akimslava.hotelreservation.common.util

private val availableSymbols = '0'..'9'
fun checkDateCorrection(date: String, withoutDots: Boolean): Boolean {
    fun isLeap(year: Int): Boolean = year % 400 == 0
            || year % 4 == 0 && year % 100 != 0
    val year: String
    val month: String
    val day: String
    if (withoutDots) {
        if (
            date.length != 8
            || date.any { it !in availableSymbols }
        ) {
            return false
        }
        year = date.takeLast(4)
        month = date.slice(2..3)
        day = date.take(2)
    } else {
        val dateParts: List<String> = date.split('.')
        if (
            dateParts.size != 3
            || dateParts.any { str -> str.any { it !in availableSymbols } }
        ) {
            return false
        }
        year = dateParts[2]
        month = dateParts[1]
        day = dateParts[0]
    }
    if (
        year.length != 4
        || year.toInt() < 1940
    ) {
        return false
    }
    if (
        month.length !in 1..2
        || month.toInt() !in 1..12
    ) {
        return false
    }
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