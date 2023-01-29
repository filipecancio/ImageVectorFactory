package ui.component.molecule

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import ui.component.atom.GraphicButton
import ui.theme.BaseColor
import ui.theme.BaseIcon
import ui.theme.BaseVector

@Composable
fun ImageView(
    isDark: Boolean = false,
    imageVector: ImageVector? = null,
    onDarkClick: () -> Unit = {},
) = Box(
    modifier = Modifier
        .size(
            width = 600.dp,
            height = 300.dp
        ),
    contentAlignment = Alignment.Center
) {
    var sizeRatio by remember { mutableStateOf(1F) }
    imageVector?.let {
        Image(
            modifier = Modifier
                .size(
                    width = it.defaultWidth * sizeRatio,
                    height = it.defaultHeight * sizeRatio,
                ),
            imageVector = it,
            contentDescription = null,
        )
    } ?: BaseIcon(
        baseVector = BaseVector.Compose,
        modifier = Modifier.size(127.dp),
        tint = BaseColor.Secondary.toColor(isDark)
    )
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.align(Alignment.TopStart)
    ) {
        GraphicButton(
            onClick = { sizeRatio /= 1.5F },
            imageVector = BaseVector.Minus,
            isDark = isDark
        )
        GraphicButton(
            onClick = { sizeRatio *= 1.5F },
            imageVector = BaseVector.Plus,
            isDark = isDark
        )
        GraphicButton(
            onClick = onDarkClick,
            imageVector = BaseVector.Moon,
            isDark = isDark
        )
    }
}