package ui.component

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue

@ExperimentalMaterialApi
@Composable
fun IconNameDialog(onValidateClick: (iconName: String) -> Unit, onCancelClick: () -> Unit) {
    val iconName = remember { mutableStateOf(TextFieldValue("")) }
    AlertDialog(
        title = { Text("Choose an icon name") },
        text = {
            OutlinedTextField(
                value = iconName.value,
                onValueChange = { iconName.value = it },
                label = { Text("Icon name") },
            )
        },
        buttons = {
            TextButton(
                onClick = { onValidateClick(iconName.value.text) },
            ) {
                Text("Copy code")
            }
        },
        onDismissRequest = onCancelClick,
    )
}
