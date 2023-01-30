package ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import ui.component.atom.CodeEdit
import ui.component.molecule.CautionDialog
import ui.theme.BaseColor

val validColorValues = mutableMapOf<String, String>()

@ExperimentalMaterialApi
@Composable
fun AskForValidColorDialog(
    colorsValue: Set<String>,
    onUnknownColorsMapped: (validColors: Map<String, String>) -> Unit,
    isDark: Boolean = false
) = CautionDialog(
    title = "Enter a valid color for those unknown colors",
    confirmText = "Validate",
    isDark = isDark,
    onConfirm = { onUnknownColorsMapped(validColorValues) },
    onDismiss = { onUnknownColorsMapped(emptyMap()) }
) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        colorsValue.forEach { colorValue ->
            var validColor by remember { mutableStateOf(TextFieldValue(colorValue)) }
            CodeEdit(
                isDark = isDark,
                value = validColor,
                onValueChange = {
                    validColor = it
                    validColorValues[colorValue] = it.text
                },
                modifier = defaultModifier(isDark)
            )
        }
    }
}

private fun defaultModifier(isDark: Boolean, selected: Boolean = false) = Modifier
    .clip(RoundedCornerShape(10.dp))
    .background(
        if (selected) {
            BaseColor.Blue01.toColor(isDark)
        } else {
            BaseColor.Secondary.toColor(isDark)
        }
    )
    .padding(
        horizontal = 16.dp,
        vertical = 4.dp
    )