package ru.akimslava.hotelreservation.common.uikit.bars

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.akimslava.hotelreservation.common.uikit.NavigateIcon
import ru.akimslava.hotelreservation.common.uikit.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopBar(
    label: String,
    navigateUp: () -> Unit = {},
    canNavigateUp: Boolean = false,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = if (label.length <= 30) label
                else stringResource(id = R.string.tob_bar_format, label.take(30)),
                style = MaterialTheme.typography.headlineMedium,
            )
        },
        navigationIcon = {
            if (canNavigateUp) NavigateIcon(onClick = navigateUp)
        },
    )
}