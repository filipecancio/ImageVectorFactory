package ui.component.atom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ui.theme.*

@Composable
fun TabButton(
    text: String = "TopButton",
    baseVector: BaseVector = BaseVector.Logo,
    isDark: Boolean = false,
    isEnabled: Boolean = false,
    onClick: () -> Unit = {},
) = Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(8.dp),
    modifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .background(if (isEnabled) BaseColor.Blue01.toColor(isDark) else BaseColor.Primary.toColor(isDark))
        .padding(8.dp)
        .selectable(
            selected = isEnabled,
            onClick = onClick
        )
) {
    BaseIcon(
        baseVector = baseVector,
        modifier = Modifier.size(24.dp),
        tint = if (isEnabled) BaseColor.Blue02.toColor(isDark) else BaseColor.Tertiary.toColor(isDark)
    )
    Text(
        text,
        style = if (isEnabled) getBaseType(isDark).body2 else getBaseType(isDark).body1
    )
}