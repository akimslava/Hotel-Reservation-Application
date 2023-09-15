package ru.akimslava.hotelreservation.common.uikit

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kotlin.math.abs

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImagePager(
    imageUrls: List<String>,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState(
        pageCount = { imageUrls.size },
    )
    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomCenter,
    ) {
        Card(
            shape = RoundedCornerShape(
                dimensionResource(id = R.dimen.default_radius)
            ),
        ) {
            HorizontalPager(state = pagerState) { photo ->
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageUrls[photo])
                        .crossfade(true)
                        .placeholder(R.drawable.no_image)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier.height(256.dp),
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Crop,
                )
            }
        }
        Card(
            modifier = Modifier
                .padding(
                    bottom = dimensionResource(
                        id = R.dimen.big_padding
                    )
                ),
            shape = RoundedCornerShape(5.dp),
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 5.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                repeat(imageUrls.size) {
                    val alpha = if (pagerState.currentPage == it) {
                        255
                    } else {
                        abs(
                            63 -
                                    abs(pagerState.currentPage - it) *
                                    (63 / imageUrls.size)
                        )
                    }
                    val indicatorColor = Color(
                        red = 0,
                        green = 0,
                        blue = 0,
                        alpha = alpha,
                    )
                    Box(
                        modifier = Modifier
                            .padding(5.dp)
                            .clip(CircleShape)
                            .background(indicatorColor)
                            .size(7.dp),
                    )
                }
            }
        }
    }
}