package ru.akimslava.hotelreservation.feature.paid

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.akimslava.hotelreservation.common.uikit.bars.DefaultTopBar
import ru.akimslava.hotelreservation.feature.paid.ui.PaidLayout

@Composable
fun PaidScreen(
    navigateUp: () -> Unit,
    onSubmitClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            DefaultTopBar(
                label = stringResource(R.string.order_paid),
                navigateUp = navigateUp,
                canNavigateUp = true,
            )
        },
    ) {
        PaidLayout(
            onSubmitClick = onSubmitClick,
            modifier = Modifier.padding(it),
        )
    }
}