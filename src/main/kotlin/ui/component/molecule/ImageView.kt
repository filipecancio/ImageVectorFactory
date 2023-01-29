package ui.component.molecule

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import ui.component.atom.GraphicButton
import ui.theme.BaseColor
import ui.theme.BaseVector

@Composable
fun ImageView(
    isDark: Boolean = false,
    imageVector: ImageVector? = null,
    onDarkClick: () -> Unit = {},
) = BoxWithConstraints(
    modifier = Modifier
        .size(
            width = 600.dp,
            height = 300.dp
        ),
    contentAlignment = Alignment.Center
) {
    var sizeRatio by remember { mutableStateOf(1F) }
    if (imageVector != null) {
        Image(
            modifier = Modifier
                .size(
                    width = imageVector.defaultWidth * sizeRatio,
                    height = imageVector.defaultHeight * sizeRatio,
                ),
            imageVector = imageVector,
            contentScale = ContentScale.Crop,
            contentDescription = null,
        )
    } else {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .horizontalScroll(rememberScrollState())
        ) {
            Image(
                imageVector = BaseVector.Compose.toPath(isDark,BaseColor.Secondary),
                modifier = Modifier.size(127.dp * sizeRatio),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        }
    }
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