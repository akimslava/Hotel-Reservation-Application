package ru.akimslava.hotelreservation.common.util

fun formatPrice(price: Int): String {
    var result = ""
    val strPrice = price.toString()
    for (i in strPrice.indices) {
        result += if (
            (strPrice.length - i - 1) % 3 == 0
            && i != strPrice.lastIndex
            && strPrice.length > 3
        ) {
            "${strPrice[i]} "
        } else {
            strPrice[i]
        }
    }
    return result
}