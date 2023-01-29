package ui.component.molecule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import ui.component.atom.ActionButton
import ui.component.atom.CodeEdit
import ui.theme.BaseColor

@Composable
fun ActionBar(
    buttonText: String = "Example",
    selected: Boolean = false,
    isDark: Boolean = false,
    onClick: () -> Unit = {},
    value: TextFieldValue = TextFieldValue("Example"),
    onValueChange: (TextFieldValue) -> Unit,
) = Box(
    modifier = Modifier.width(300.dp),
    contentAlignment = Alignment.TopEnd
) {
    CodeEdit(
        value = value,
        onValueChange = onValueChange,
        modifier = defaultModifier(isDark),
        isDark = isDark
    )
    ActionButton(
        text = buttonText,
        onClick = onClick,
        selected = selected,
        isDark = isDark
    )
}

private fun defaultModifier(isDark: Boolean) = Modifier
    .width(300.dp)
    .clip(RoundedCornerShape(10.dp))
    .background(BaseColor.Secondary.toColor(isDark))
    .padding(
        horizontal = 16.dp,
        vertical = 4.dp
    )