package ui.component

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.text.input.TextFieldValue

@ExperimentalMaterialApi
@Composable
fun AskForValidColorDialog(
    colorsValue: Set<String>,
    onUnknownColorsMapped: (validColors: Map<String, String>) -> Unit,
) {
    val validColorValues = mutableMapOf<String, String>()
    AlertDialog(
        modifier = Modifier.focusable(true).focusTarget(),
        title = { Text("Enter a valid color for those unknown colors") },
        text = {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                colorsValue.forEach { colorValue ->
                    var validColor by remember { mutableStateOf(TextFieldValue("")) }
                    Text(colorValue)
                    OutlinedTextField(
                        value = validColor,
                        onValueChange = {
                            validColor = it
                            validColorValues[colorValue] = it.text
                        },
                        label = { Text("Hexadecimal color") },
                        placeholder = { Text("#FF00FF") },
                    )
                }
            }
        },
        buttons = {
            TextButton(onClick = { onUnknownColorsMapped(validColorValues) }) {
                Text("Valid those colors")
            }
        },
        onDismissRequest = { onUnknownColorsMapped(emptyMap()) },
    )
}