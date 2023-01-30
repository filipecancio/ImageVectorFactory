package ui.component.molecule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ui.component.atom.ActionButton
import ui.theme.BaseColor
import ui.theme.getBaseType

@Composable
fun CautionDialog(
    title: String = "",
    confirmText: String = "",
    isDark: Boolean = false,
    onConfirm: () -> Unit = {},
    onDismiss: () -> Unit = {},
    content: @Composable ColumnScope.() -> Unit
) = Box(
    modifier = Modifier
        .fillMaxSize()
        .background(BaseColor.Blue0180.toColor(isDark)),
    contentAlignment = Alignment.Center
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(BaseColor.Primary.toColor(isDark))
            .padding(8.dp),
    ) {
        Text(
            title,
            style = getBaseType(isDark).body1,
            modifier = Modifier.width(225.dp)
        )

        content()
        Box(
            modifier = Modifier
                .width(225.dp)
                .padding(top = 16.dp),
            contentAlignment = Alignment.BottomEnd
        ){
            ActionButton(
                isDark= isDark,
                text = confirmText,
                selected = true
            ) {  onConfirm() }
        }
    }
}