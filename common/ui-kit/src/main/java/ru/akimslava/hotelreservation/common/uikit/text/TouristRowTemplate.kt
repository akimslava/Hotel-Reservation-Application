package ru.akimslava.hotelreservation.common.uikit.text

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.akimslava.hotelreservation.common.theme.Blue
import ru.akimslava.hotelreservation.common.uikit.R

@Composable
fun TouristRowTemplate(
    text: String,
    painter: Painter,
    background: Color,
    onExpandClick: () -> Unit,
    modifier: Modifier = Modifier,
    isVisible: Boolean = false,
    content: @Composable (AnimatedVisibilityScope.() -> Unit) = {},
    canDelete: Boolean = false,
    onDeleteClick: () -> Unit = {},
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
    ) {
        Column(
            modifier = modifier,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.displayMedium,
                )
                Spacer(modifier = Modifier.weight(1f))
                if (canDelete) {
                    IconButton(
                        onClick = onDeleteClick,
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .size(32.dp),
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.delete),
                            contentDescription = null,
                            tint = Blue,
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(
                            color = background,
                            shape = RoundedCornerShape(8.dp)
                        ).clickable(onClick = onExpandClick),
                    contentAlignment = Alignment.Center,
                ) {
                    Image(
                        painter = painter,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
                }
            }
            AnimatedVisibility(
                visible = isVisible,
                enter = expandVertically(),
                exit = shrinkVertically(),
            ) {
                content()
            }
        }
    }
}