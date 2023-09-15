package ru.akimslava.hotelreservation.common.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val sfProDisplayFamily = FontFamily(
    Font(R.font.sf_pro_display_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.sf_pro_display_bolditalic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.sf_pro_display_semibold, FontWeight.SemiBold, FontStyle.Normal),
    Font(R.font.sf_pro_display_semibolditalic, FontWeight.SemiBold, FontStyle.Italic),
    Font(R.font.sf_pro_display_medium, FontWeight.Medium, FontStyle.Normal),
    Font(R.font.sf_pro_display_mediumitalic, FontWeight.Medium, FontStyle.Italic),
    Font(R.font.sf_pro_display_light, FontWeight.Light, FontStyle.Normal),
    Font(R.font.sf_pro_display_lightitalic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.sf_pro_display_thin, FontWeight.Thin, FontStyle.Normal),
    Font(R.font.sf_pro_display_thinitalic, FontWeight.Thin, FontStyle.Italic),
    Font(R.font.sf_pro_display_ultralight, FontWeight.ExtraLight, FontStyle.Normal),
    Font(R.font.sf_pro_display_ultralightitalic, FontWeight.ExtraLight, FontStyle.Italic),
    Font(R.font.sf_pro_display_black, FontWeight.Black, FontStyle.Normal),
    Font(R.font.sf_pro_display_blackitalic, FontWeight.Black, FontStyle.Italic),
    Font(R.font.sf_pro_display_regular, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.sf_pro_display_regularitalic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.sf_pro_display_heavy, FontWeight.ExtraBold, FontStyle.Normal),
    Font(R.font.sf_pro_display_heavyitalic, FontWeight.ExtraBold, FontStyle.Italic),
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontSize = 30.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = sfProDisplayFamily,
    ),
    displayMedium = TextStyle(
        fontSize = 22.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = sfProDisplayFamily,
    ),
    headlineMedium = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = sfProDisplayFamily,
    ),
    headlineSmall = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = sfProDisplayFamily,
    ),
    titleMedium = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = sfProDisplayFamily,
    ),
    titleSmall = TextStyle(
        color = BackgroundGrayVariant,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = sfProDisplayFamily,
    ),
    bodyLarge = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = sfProDisplayFamily,
    ),
    bodyMedium = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = sfProDisplayFamily,
    ),
    bodySmall = TextStyle(
        color = Gray,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = sfProDisplayFamily,
    ),
    labelLarge = TextStyle(
        fontSize = 17.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = sfProDisplayFamily,
    ),
    labelMedium = TextStyle(
        color = White,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = sfProDisplayFamily,
    ),
)