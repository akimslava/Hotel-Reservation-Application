package ru.akimslava.hotelreservation.feature.paid.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.akimslava.hotelreservation.common.theme.Gray
import ru.akimslava.hotelreservation.common.theme.GrayVariant
import ru.akimslava.hotelreservation.common.theme.HotelReservationTheme
import ru.akimslava.hotelreservation.common.uikit.buttons.BottomBlueNavigateButton
import ru.akimslava.hotelreservation.feature.paid.R
import ru.akimslava.hotelreservation.feature.paid.ui.models.PaidViewModel

@Composable
internal fun PaidLayout(
    onSubmitClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PaidViewModel = viewModel(),
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.weight(0.5f))
        PicturePart(modifier = Modifier.padding(bottom = 32.dp))
        Text(
            text = stringResource(id = R.string.order_accepted),
            modifier = Modifier.padding(bottom = 20.dp),
            style = MaterialTheme.typography.displayMedium,
        )
        Text(
            text = stringResource(
                id = R.string.order_acception_info,
                viewModel.orderNumber.intValue,
            ),
            modifier = Modifier.padding(horizontal = 24.dp),
            color = Gray,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
        )
        Spacer(modifier = Modifier.weight(1f))
        Divider(modifier = Modifier.fillMaxWidth().background(Gray))
        BottomBlueNavigateButton(
            text = stringResource(id = R.string.super_text),
            onClick = onSubmitClick,
        )
    }
}

@Composable
private fun PicturePart(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(100))
            .background(GrayVariant)
            .size(94.dp),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.party_popper),
            contentDescription = null,
            modifier = Modifier.padding(25.dp).size(44.dp),
        )
    }
}

@Preview
@Composable
private fun PaidLayoutPreview() {
    HotelReservationTheme {
        PaidLayout(
            onSubmitClick = {},
        )
    }
}