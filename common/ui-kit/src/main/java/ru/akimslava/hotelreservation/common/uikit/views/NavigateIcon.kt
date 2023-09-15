package ru.akimslava.hotelreservation.common.uikit

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource

@Composable
fun NavigateIcon(
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.arrow_back_ios),
            contentDescription = null,
        )
    }
}