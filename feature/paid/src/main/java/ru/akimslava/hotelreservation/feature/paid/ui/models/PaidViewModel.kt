package ru.akimslava.hotelreservation.feature.paid.ui.models

import androidx.compose.runtime.IntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import ru.akimslava.hotelreservation.common.util.generateOrderNumber

class PaidViewModel : ViewModel() {
    private val _orderNumber = mutableIntStateOf(-1)
    val orderNumber: IntState = _orderNumber

    init {
        generateNewOrderNumber()
    }

    private fun generateNewOrderNumber() {
        _orderNumber.intValue = generateOrderNumber()
    }
}