package ui.component.molecule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.theme.BaseColor
import ui.theme.BaseIcon
import ui.theme.BaseType
import ui.theme.BaseVector

@Composable
fun TopBar(
    isDark: Boolean,
) = Row(
    modifier = Modifier
        .fillMaxWidth()
        .background(BaseColor.Primary.toColor(isDark))
        .padding(
            horizontal = 24.dp,
            vertical = 8.dp
        ),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        BaseIcon(
            baseVector = BaseVector.Logo,
            modifier = Modifier.size(24.dp),
            tint = BaseColor.Tertiary.toColor(isDark)
        )
        Text(
            "ImageVector Factory",
            style = BaseType.body1
        )
    }
}