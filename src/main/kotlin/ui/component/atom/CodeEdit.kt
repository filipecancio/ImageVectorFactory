package ui.component.atom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import ui.theme.BaseColor
import ui.theme.getBaseType

@Composable
fun CodeEdit(
    value: TextFieldValue = TextFieldValue("Example"),
    onValueChange: (TextFieldValue) -> Unit,
    isDark: Boolean = false,
    textStyle: TextStyle =  getBaseType(isDark).body1,
) = BasicTextField(
    value = value,
    onValueChange = onValueChange,
    modifier = Modifier
        .size(
            width = 300.dp,
            height = 100.dp
        )
        .clip(RoundedCornerShape(10.dp))
        .background(BaseColor.Secondary.toColor(isDark))
        .padding(16.dp),
    textStyle = textStyle
)