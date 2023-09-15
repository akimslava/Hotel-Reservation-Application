package ru.akimslava.hotelreservation.common.util

fun intToRussianWord(number: Int): String {
    val converter = mapOf(
        1 to "Первый",
        2 to "Второй",
        3 to "Третий",
        4 to "Четвертый",
        5 to "Пятый",
        6 to "Шестой",
        7 to "Седьмой",
        8 to "Восьмой",
        9 to "Девятый",
        10 to "Десятый",
        11 to "Одиннадцатый",
        12 to "Двенадцатый",
        13 to "Тринадцатый",
        14 to "Четырнадцатый",
        15 to "Пятнадцатый",
        16 to "Шестнадцатый",
        17 to "Семнадцатый",
        18 to "Восемнадцатый",
        19 to "Девятнадцатый",
        20 to "Двадцатый",
        -20 to "Двадцать",
        30 to "Тридцатый",
        -30 to "Тридцать",
        40 to "Сороковой",
        -40 to "Сорок",
        50 to "Пятидесятый",
        -50 to "Пятьдесят",
        60 to "Шестидесятый",
        -60 to "Шестьдесят",
        70 to "Семидесятый",
        -70 to "Семьдесят",
        80 to "Восьмидесятый",
        -80 to "Восемьдесят",
        90 to "Девяностый",
        -90 to "Девяносто",
    )
    if (number < 1 || number > 99) return "Неизвестный"
    return if (number < 20 || number % 10 == 0) {
        converter[number]!!
    } else {
        "${converter[-(number / 10) * 10]!!} ${converter[number % 10]!!.lowercase()}"
    }
}