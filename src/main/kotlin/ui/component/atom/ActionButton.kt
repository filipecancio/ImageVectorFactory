package ui.component.atom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ui.theme.BaseColor
import ui.theme.getBaseType

@Composable
fun ActionButton(
    text: String = "Example",
    selected: Boolean = false,
    isDark: Boolean = false,
    onClick: () -> Unit = {},
) = Text(
    text = text,
    modifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .background(if (selected) BaseColor.Blue01.toColor(isDark) else BaseColor.Secondary.toColor(isDark))
        .selectable(
            selected = selected,
            onClick = onClick
        )
        .padding(
            horizontal = 16.dp,
            vertical = 4.dp
        ),
    style = if (selected) getBaseType(isDark).body2 else getBaseType(isDark).body1
)