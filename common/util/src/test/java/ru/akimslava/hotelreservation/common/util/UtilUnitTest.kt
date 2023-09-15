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
        assertTrue(checkDateCorrection("12.01.2000"))
        assertTrue(checkDateCorrection("29.02.2004"))
        assertTrue(checkDateCorrection("12.3.1999"))
        assertTrue(checkDateCorrection("1.1.1983"))
        assertTrue(checkDateCorrection("31.01.2003"))
        assertTrue(checkDateCorrection("28.02.2001"))
        assertTrue(checkDateCorrection("30.05.2005"))
    }

    @Test
    fun checkDateCorrection_isCorrect_False() {
        assertFalse(checkDateCorrection(""))
        assertFalse(checkDateCorrection("1.1.1"))
        assertFalse(checkDateCorrection("1..2"))
        assertFalse(checkDateCorrection("12.12.2000.1"))
        assertFalse(checkDateCorrection("29.02.2001"))
        assertFalse(checkDateCorrection("32.12.2000"))
        assertFalse(checkDateCorrection("-1.1.2000"))
        assertFalse(checkDateCorrection(".134.13.1"))
        assertFalse(checkDateCorrection("12.13.2999"))
    }
}