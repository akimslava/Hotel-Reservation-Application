package ru.akimslava.hotelreservation.common.util

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class UtilUnitTest {
    @Test
    fun formatPrice_IsCorrect_True() {
        assertEquals(formatPrice(0), "0")
        assertEquals(formatPrice(123), "123")
        assertEquals(formatPrice(123_456), "123 456")
        assertEquals(formatPrice(1_234_567), "1 234 567")
    }

    @Test
    fun intToRussianWord_isCorrect_True() {
        assertEquals(intToRussianWord(1), "Первый")
        assertEquals(intToRussianWord(10), "Десятый")
        assertEquals(intToRussianWord(43), "Сорок третий")
        assertEquals(intToRussianWord(76), "Семьдесят шестой")
        assertEquals(intToRussianWord(18), "Восемнадцатый")
        assertEquals(intToRussianWord(92), "Девяносто второй")
        assertEquals(intToRussianWord(61), "Шестьдесят первый")
    }

    @Test
    fun intToRussianWord_isCorrect_False() {
        assertEquals(intToRussianWord(0), "Неизвестный")
        assertEquals(intToRussianWord(-2), "Неизвестный")
        assertEquals(intToRussianWord(1234), "Неизвестный")
    }

    @Test
    fun checkDateCorrection_isCorrect_True() {
        assertTrue(checkDateCorrection(date = "12.01.2000", withoutDots = false))
        assertTrue(checkDateCorrection(date = "29.02.2004", withoutDots = false))
        assertTrue(checkDateCorrection(date = "12.3.1999", withoutDots = false))
        assertTrue(checkDateCorrection(date = "1.1.1983", withoutDots = false))
        assertTrue(checkDateCorrection(date = "31.01.2003", withoutDots = false))
        assertTrue(checkDateCorrection(date = "28.02.2001", withoutDots = false))
        assertTrue(checkDateCorrection(date = "30.05.2005", withoutDots = false))

        assertTrue(checkDateCorrection(date = "12012000", withoutDots = true))
        assertTrue(checkDateCorrection(date = "29022004", withoutDots = true))
        assertTrue(checkDateCorrection(date = "12031999", withoutDots = true))
        assertTrue(checkDateCorrection(date = "01011983", withoutDots = true))
        assertTrue(checkDateCorrection(date = "31012003", withoutDots = true))
        assertTrue(checkDateCorrection(date = "28022001", withoutDots = true))
        assertTrue(checkDateCorrection(date = "30052005", withoutDots = true))
    }

    @Test
    fun checkDateCorrection_isCorrect_False() {
        assertFalse(checkDateCorrection(date = "", withoutDots = false))
        assertFalse(checkDateCorrection(date = "1.1.1", withoutDots = false))
        assertFalse(checkDateCorrection(date = "1..2", withoutDots = false))
        assertFalse(checkDateCorrection(date = "12.12.2000.1", withoutDots = false))
        assertFalse(checkDateCorrection(date = "29.02.2001", withoutDots = false))
        assertFalse(checkDateCorrection(date = "32.12.2000", withoutDots = false))
        assertFalse(checkDateCorrection(date = "-1.1.2000", withoutDots = false))
        assertFalse(checkDateCorrection(date = ".134.13.1", withoutDots = false))
        assertFalse(checkDateCorrection(date = "12.13.2999", withoutDots = false))

        assertFalse(checkDateCorrection(date = "", withoutDots = true))
        assertFalse(checkDateCorrection(date = "111", withoutDots = true))
        assertFalse(checkDateCorrection(date = "12", withoutDots = true))
        assertFalse(checkDateCorrection(date = "121220001", withoutDots = true))
        assertFalse(checkDateCorrection(date = "29022001", withoutDots = true))
        assertFalse(checkDateCorrection(date = "32122000", withoutDots = true))
        assertFalse(checkDateCorrection(date = "-112000", withoutDots = true))
        assertFalse(checkDateCorrection(date = "134131", withoutDots = true))
        assertFalse(checkDateCorrection(date = "12132999", withoutDots = true))
    }
}