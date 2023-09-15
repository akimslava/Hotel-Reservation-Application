package ru.akimslava.hotelreservation.feature.hotelrooms.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.akimslava.hotelreservation.common.theme.BackgroundBlue
import ru.akimslava.hotelreservation.common.theme.BackgroundGray
import ru.akimslava.hotelreservation.common.theme.Blue
import ru.akimslava.hotelreservation.common.uikit.buttons.BlueButton
import ru.akimslava.hotelreservation.common.uikit.ImagePager
import ru.akimslava.hotelreservation.common.uikit.PeculiaritiesFlowRow
import ru.akimslava.hotelreservation.common.uikit.text.PriceRow
import ru.akimslava.hotelreservation.data.service.models.rooms.RoomData
import ru.akimslava.hotelreservation.feature.hotelrooms.R

@Composable
internal fun SuccessScreen(
    rooms: List<RoomData>,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.background(color = BackgroundGray),
    ) {
        items(count = rooms.size) {
            if (it == 0) {
                Spacer(modifier = Modifier.height(8.dp))
            }
            RoomView(
                room = rooms[it],
                onClick = onClick,
                modifier = Modifier.padding(16.dp),
            )
            if (it != rooms.size) {
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
private fun RoomView(
    room: RoomData,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
    ) {
        Column(
            modifier = modifier,
        ) {
            ImagePager(
                imageUrls = room.imageUrls,
                modifier = Modifier.padding(bottom = 8.dp),
            )
            Text(
                text = room.name,
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.displayMedium,
            )
            PeculiaritiesFlowRow(
                peculiarities = room.peculiarities,
                modifier = Modifier.padding(bottom = 8.dp),
            )
            MoreRoomDetails(modifier = Modifier.padding(bottom = 16.dp))
            PriceRow(
                price = room.price,
                priceFormat = stringResource(R.string.room_price_format),
                pricePer = room.pricePer,
                modifier = Modifier.padding(bottom = 16.dp),
            )
            BlueButton(
                text = stringResource(R.string.choose_room),
                onClick = onClick,
            )
        }
    }
}

@Composable
private fun MoreRoomDetails(
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = BackgroundBlue,
        ),
    ) {
        Row(
            modifier = Modifier
                .padding(
                    start = 10.dp,
                    end = 2.dp,
                ).padding(vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(R.string.more_room_details),
                modifier = Modifier.padding(end = 2.dp),
                color = Blue,
                style = MaterialTheme.typography.titleMedium,
            )
            Icon(
                painter = painterResource(id = R.drawable.arrow_forward_ios),
                contentDescription = null,
                tint = Blue,
            )
        }
    }
}