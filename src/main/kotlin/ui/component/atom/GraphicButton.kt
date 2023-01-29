package ui.component.atom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ui.theme.BaseColor
import ui.theme.BaseVector

@Composable
fun GraphicButton(
    imageVector: BaseVector = BaseVector.Logo,
    selected: Boolean = false,
    isDark: Boolean = true,
    onClick: () -> Unit = {},
) = Box(
    modifier = Modifier
        .size(36.dp)
        .clip(RoundedCornerShape(10.dp))
        .selectable(
            selected = selected,
            onClick = onClick
        )
        .background(BaseColor.Secondary.toColor(isDark)),
    contentAlignment = Alignment.Center
) {
    Icon(
        imageVector = imageVector.toPath(isDark),
        contentDescription = null,
        modifier = Modifier
            .size(24.dp),
        tint = BaseColor.Tertiary.toColor(isDark)
    )
}