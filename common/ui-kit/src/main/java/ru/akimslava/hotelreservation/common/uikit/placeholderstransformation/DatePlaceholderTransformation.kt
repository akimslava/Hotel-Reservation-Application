package ru.akimslava.hotelreservation.common.uikit.placeholderstransformation

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import kotlin.math.min

object DatePlaceholderTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        var format = "**.**.****"
        for (i in text) {
            format = format.replaceFirst('*', i)
        }
        return TransformedText(
            text = AnnotatedString(text = format),
            offsetMapping = object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    return when (offset) {
                        in 0..1 -> offset
                        in 2..3 -> offset + 1
                        in 4..8 -> offset + 2
                        else -> format.length
                    }
                }

                override fun transformedToOriginal(offset: Int): Int {
                    return min(
                        a = when (offset) {
                            in 0..2 -> offset
                            in 3..5 -> offset - 1
                            in 6..10 -> offset - 2
                            else -> 8
                        },
                        b = text.length,
                    )
                }
            }
        )
    }
}