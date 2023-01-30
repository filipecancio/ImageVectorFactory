package ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import ui.component.atom.ActionButton
import ui.component.atom.CodeEdit
import ui.component.atom.TabButton
import ui.component.molecule.ActionBar
import ui.component.molecule.ImageView
import ui.component.molecule.TopBar
import ui.screen.caution.CautionScreen
import ui.theme.BaseColor
import ui.theme.getBaseType

@ExperimentalMaterialApi
@Composable
fun HomeScreen() {
    val clipboardManager = LocalClipboardManager.current
    val controller = HomeController(clipboardManager)
    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BaseColor.Primary.toColor(controller.isDark))
                .blur((controller.blur).dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            TopBar(controller.isDark) {
                controller.convertOptions.mapIndexed { index, item ->
                    TabButton(
                        text = item.label,
                        baseVector = item.icon,
                        isEnabled = controller.currentTabIndex == index,
                        onClick = { controller.clearValues(index) },
                        isDark = controller.isDark
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = controller.getCurrentPlaceholder(),
                        modifier = Modifier.width(300.dp),
                        style = getBaseType(controller.isDark).body1
                    )
                    CodeEdit(
                        value = controller.textFieldValue,
                        onValueChange = { controller.textFieldValue = it },
                        isDark = controller.isDark
                    )
                    ActionButton(
                        text = "Convert",
                        onClick = { controller.generateSvgData() },
                        isDark = controller.isDark
                    )
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        "The Image Vector code:",
                        modifier = Modifier.width(300.dp),
                        style = getBaseType(controller.isDark).body2
                    )
                    CodeEdit(
                        value = TextFieldValue(controller.imageVectorCode),
                        selected = true,
                        onValueChange = { },
                        isDark = controller.isDark
                    )
                    ActionBar(
                        buttonText = "Copy",
                        onClick = { controller.copyImageVector() },
                        selected = true,
                        value = controller.iconName,
                        onValueChange = { controller.replaceImageVectorCode(it) },
                        isDark = controller.isDark
                    )
                }
            }
            ImageView(
                isDark = controller.isDark,
                imageVector = controller.imageVector,
                onDarkClick = { controller.isDark = !controller.isDark }
            )
        }
        if (controller.unknownColors.isNotEmpty()) {
            controller.blur = 10F
            CautionScreen(
                isDark = controller.isDark,
                colorsValue = controller.unknownColors,
                onUnknownColorsMapped = { controller.fixUnknownColors(it) },
            )
        }
    }
}
