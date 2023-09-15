package ru.akimslava.hotelreservation.feature.hotel.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.akimslava.hotelreservation.common.theme.BackgroundGray
import ru.akimslava.hotelreservation.common.theme.Blue
import ru.akimslava.hotelreservation.common.theme.Gray
import ru.akimslava.hotelreservation.common.theme.HotelReservationTheme
import ru.akimslava.hotelreservation.data.service.models.hotel.AboutHotelData
import ru.akimslava.hotelreservation.data.service.models.hotel.HotelData
import ru.akimslava.hotelreservation.feature.hotel.R
import ru.akimslava.hotelreservation.common.uikit.buttons.BottomBlueNavigateButton
import ru.akimslava.hotelreservation.common.uikit.ImagePager
import ru.akimslava.hotelreservation.common.uikit.PeculiaritiesFlowRow
import ru.akimslava.hotelreservation.common.uikit.text.PriceRow
import ru.akimslava.hotelreservation.common.uikit.RatingCard

@Composable
internal fun SuccessScreen(
    hotel: HotelData,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(state = scrollState)
            .background(color = BackgroundGray),
    ) {
        HotelMainPart(
            hotel = hotel,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp),
        )
        Spacer(modifier = Modifier.height(8.dp))
        HotelDetailsPart(
            hotel = hotel,
            modifier = Modifier.padding(16.dp),
        )
        Spacer(modifier = Modifier.height(12.dp))
        BottomBlueNavigateButton(
            text = stringResource(R.string.choose_room),
            onClick = {
                onClick(hotel.name)
            },
        )
    }
}

@Composable
private fun HotelMainPart(
    hotel: HotelData,
    modifier: Modifier = Modifier,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
    ) {
        Column (modifier = modifier) {
            ImagePager(
                imageUrls = hotel.imageUrls,
                modifier = Modifier.padding(bottom = 16.dp),
            )
            RatingCard(
                rating = hotel.rating,
                ratingName = hotel.ratingName,
                modifier = Modifier.padding(bottom = 8.dp),
            )
            Text(
                text = hotel.name,
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.displayMedium,
            )
            Text(
                text = hotel.address,
                modifier = Modifier.padding(bottom = 16.dp),
                color = Blue,
                style = MaterialTheme.typography.headlineSmall,
            )
            PriceRow(
                price = hotel.minimalPrice,
                priceFormat = stringResource(id = R.string.hotel_price_format),
                pricePer = hotel.priceForIt,
            )
        }
    }
}

@Composable
private fun HotelDetailsPart(
    hotel: HotelData,
    modifier: Modifier = Modifier,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
    ) {
        Column(modifier = modifier) {
            Text(
                text = stringResource(R.string.about_hotel),
                modifier = Modifier.padding(bottom = 16.dp),
                style = MaterialTheme.typography.displayMedium,
            )
            PeculiaritiesFlowRow(
                peculiarities = hotel.aboutTheHotel.peculiarities,
                modifier = Modifier.padding(bottom = 12.dp),
            )
            Text(
                text = hotel.aboutTheHotel.description,
                modifier = Modifier.padding(bottom = 16.dp),
                style = MaterialTheme.typography.bodyMedium,
            )
            HotelInfoButtons(modifier = Modifier.padding(15.dp))
        }
    }
}

@Composable
private fun HotelInfoButtons(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        HotelInfoButton(
            title = stringResource(R.string.facilities),
            description = stringResource(R.string.essentials),
            imageRes = R.drawable.emoji_happy,
        )
        Divider(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .padding(start = 36.dp, end = 8.dp),
        )
        HotelInfoButton(
            title = stringResource(R.string.whats_included),
            description = stringResource(id = R.string.essentials),
            imageRes = R.drawable.tick_square,
        )
        Divider(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .padding(start = 36.dp, end = 8.dp),
        )
        HotelInfoButton(
            title = stringResource(R.string.whats_not_included),
            description = stringResource(id = R.string.essentials),
            imageRes = R.drawable.close_square,
        )
    }
}

@Suppress("SameParameterValue")
@Composable
private fun HotelInfoButton(
    title: String,
    description: String,
    imageRes: Int,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier.padding(end = 12.dp),
            )
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = description,
                    color = Gray,
                    style = MaterialTheme.typography.headlineSmall,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.arrow_forward_ios),
                contentDescription = null,
            )
        }
    }
}

val testHotel = HotelData(
    id = 0,
    name = "Steigenberger Makadi",
    address = "Madinat Makadi, Safaga Road, Makadi Bay, Египет",
    minimalPrice = 134673,
    priceForIt = "за тур с перелетом",
    rating = 5,
    ratingName = "Превосходно",
    imageUrls = listOf(
        "https://www.atorus.ru/sites/default/files/upload/image/News/56149/Club_Priv%C3%A9_by_Belek_Club_House.jpg",
        "https://deluxe.voyage/useruploads/articles/The_Makadi_Spa_Hotel_02.jpg",
        "https://deluxe.voyage/useruploads/articles/article_1eb0a64d00.jpg",
    ),
    aboutTheHotel = AboutHotelData(
        description = "Отель VIP-класса с собственными гольф полями. " +
                "Высокий уровнь сервиса. Рекомендуем для " +
                "респектабельного отдыха. Отель принимает гостей от 18 лет!",
        peculiarities = listOf(
            "Бесплатный Wifi на всей территории отеля",
            "1 км до пляжа",
            "Бесплатный фитнес-клуб",
            "20 км до аэропорта",
        ),
    ),
)

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
private fun SuccessScreenPreview() {
    HotelReservationTheme {
        SuccessScreen(
            hotel = testHotel,
            onClick = {},
            modifier = Modifier.padding(8.dp),
        )
    }
}

@Preview
@Composable
private fun MainHotelPartPreview() {
    HotelReservationTheme {
        HotelMainPart(
            hotel = testHotel,
            modifier = Modifier.padding(8.dp),
        )
    }
}

@Preview
@Composable
private fun HotelDetailsPartPreview() {
    HotelReservationTheme {
        HotelDetailsPart(
            hotel = testHotel,
            modifier = Modifier.padding(8.dp),
        )
    }
}

@Preview
@Composable
private fun HotelInfoButtonsPreview() {
    HotelReservationTheme {
        HotelInfoButtons()
    }
}