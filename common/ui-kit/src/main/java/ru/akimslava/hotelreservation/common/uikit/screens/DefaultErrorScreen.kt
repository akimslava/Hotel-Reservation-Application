package ru.akimslava.hotelreservation.common.uikit.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.akimslava.hotelreservation.common.uikit.buttons.BlueButton
import ru.akimslava.hotelreservation.common.uikit.R

@Composable
fun DefaultErrorScreen(onRefresh: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.cloud_off),
            contentDescription = null,
            modifier = Modifier.size(128.dp)
                .padding(bottom = 8.dp),
        )
        Text(
            text = stringResource(id = R.string.error_message),
            modifier = Modifier.padding(bottom = 16.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
        )
        BlueButton(
            text = stringResource(id = R.string.refresh),
            onClick = onRefresh,
            modifier = Modifier.width(128.dp),
        )
    }
}