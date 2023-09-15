package ru.akimslava.hotelreservation.common.uikit.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import ru.akimslava.hotelreservation.common.theme.Blue
import ru.akimslava.hotelreservation.common.theme.HotelReservationTheme
import ru.akimslava.hotelreservation.common.theme.Typography
import ru.akimslava.hotelreservation.common.theme.White
import ru.akimslava.hotelreservation.common.uikit.R

@Composable
fun BlueButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(
            dimensionResource(id = R.dimen.default_radius)
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = Blue,
        ),
    ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(
                    vertical = dimensionResource(
                        id = R.dimen.medium_padding
                    ),
                ),
            color = White,
            fontSize = 16.sp,
            style = Typography.labelMedium,
        )
    }
}

@Composable
fun BottomBlueNavigateButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.background(color = Color.White),
        contentAlignment = Alignment.Center,
    ) {
        BlueButton(
            text = text,
            onClick = onClick,
            modifier = Modifier.padding(
                vertical = dimensionResource(id = R.dimen.big_padding),
                horizontal = dimensionResource(id = R.dimen.huge_padding),
            ),
        )
    }
}

@Preview
@Composable
private fun BlueButtonPreview() {
    HotelReservationTheme {
        BlueButton(
            text = "Текст кнопки",
            onClick = {},
        )
    }
}