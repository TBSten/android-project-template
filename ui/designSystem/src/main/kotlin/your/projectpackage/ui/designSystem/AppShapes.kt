package your.projectpackage.ui.designSystem

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import your.projectpackage.common.lazyWithReceiver

interface AppShapes {
    /* Add your shapes */
    val medium: Shape
    val full: Shape
}

internal object DefaultAppShapes : AppShapes {
    override val medium: Shape = RoundedCornerShape(16.dp)
    override val full: Shape = RoundedCornerShape(100)
}

/**
 * AppShapes を MaterialTheme.shapes に変換する拡張関数。
 */
internal val AppShapes.asMaterial: androidx.compose.material3.Shapes by lazyWithReceiver {
    androidx.compose.material3.Shapes(
        medium = this.medium as RoundedCornerShape,
        extraLarge = this.full as RoundedCornerShape,
    )
}
