package ru.akimslava.hotelreservation.common.uikit.placeholderstransformation

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import kotlin.math.min

object PhonePlaceholderTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        var format = "+7 (***) ***-**-**"
        for (i in text) {
            format = format.replaceFirst('*', i)
        }
        return TransformedText(
            text = AnnotatedString(text = format),
            offsetMapping = object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    return when (offset) {
                        in 0..2 -> offset + 4
                        in 3..5 -> offset + 6
                        in 6..7 -> offset + 7
                        in 8..10 -> offset + 8
                        else -> format.length
                    }
                }

                override fun transformedToOriginal(offset: Int): Int {
                    return min(
                        a = when (offset) {
                            in 0..4 -> 0
                            in 4..6 -> offset - 4
                            in 7..9 -> 3
                            in 9..11 -> offset - 6
                            12 -> 6
                            in 13..14 -> offset - 7
                            15 -> 8
                            in 16..18 -> offset - 8
                            else -> 10
                        },
                        b = text.length,
                    )
                }
            }
        )
    }
}