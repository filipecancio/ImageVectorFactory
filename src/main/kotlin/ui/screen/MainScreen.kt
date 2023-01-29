import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import domain.UnknownColors
import ui.component.AskForValidColorDialog
import ui.component.IconNameDialog
import ui.component.atom.ActionButton
import ui.component.atom.CodeEdit
import ui.component.atom.TabButton
import ui.component.molecule.ActionBar
import ui.component.molecule.ImageView
import ui.component.molecule.TopBar
import ui.screen.MainController
import ui.theme.BaseColor
import ui.theme.BaseVector
import ui.theme.getBaseType

@ExperimentalMaterialApi
@Composable
fun MainScreen(controller: MainController) {
    MaterialTheme {
        val clipboardManager = LocalClipboardManager.current
        var currentTabIndex by remember { mutableStateOf(0) }
//        var svgFileTextFieldValue by remember { mutableStateOf(TextFieldValue("")) }
        var vectorDrawableTextFieldValue by remember { mutableStateOf(TextFieldValue("")) }
        var svgPathTextFieldValue by remember { mutableStateOf(TextFieldValue("")) }
        var pathDecomposed by remember { mutableStateOf("") }
        var imageVector by remember { mutableStateOf<ImageVector?>(null) }
        var unknownColors by remember { mutableStateOf(emptySet<String>()) }
        var iconName by remember { mutableStateOf(TextFieldValue("Untitled")) }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BaseColor.Primary.toColor(controller.isDark)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            TopBar(controller.isDark) {
                TabButton(
                    text = "Drawable",
                    baseVector = BaseVector.FileXml,
                    isEnabled = currentTabIndex == 0,
                    onClick = {
                        pathDecomposed = ""
                        controller.imageVectorCode = ""
                        imageVector = null
                        currentTabIndex = 0
                    },
                    isDark = controller.isDark
                )
                TabButton(
                    text = "SVG Path",
                    baseVector = BaseVector.FileSvg,
                    isEnabled = currentTabIndex == 1,
                    onClick = {
                        pathDecomposed = ""
                        controller.imageVectorCode = ""
                        imageVector = null
                        currentTabIndex = 1
                    },
                    isDark = controller.isDark
                )
                /*
                TabButton(
                    text = "SVG File",
                    baseVector = BaseVector.FileSvg,
                    isEnabled = currentTabIndex == 2,
                    onClick = {
                        pathDecomposed = ""
                        controller.imageVectorCode = ""
                        imageVector = null
                        currentTabIndex = 2
                    }
                )
                */
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        when (currentTabIndex) {
                            0 -> "Insert Drawable path here:"
                            else -> "Insert SVG path here:"
                        },
                        modifier = Modifier.width(300.dp),
                        style = getBaseType(controller.isDark).body1
                    )
                    CodeEdit(
                        value = when (currentTabIndex) {
                            0 -> vectorDrawableTextFieldValue
                            else -> svgPathTextFieldValue
                        },
                        onValueChange = {
                            when (currentTabIndex) {
                                0 -> vectorDrawableTextFieldValue = it
                                else -> svgPathTextFieldValue = it
                            }
                        },
                        isDark = controller.isDark
                    )
                    ActionButton(
                        text = "Convert",
                        onClick = {
                            val svgData = controller.buildSvgData(
                                currentTabIndex = currentTabIndex,
                                vectorDrawableValue = vectorDrawableTextFieldValue.text,
                                svgPathValue = svgPathTextFieldValue.text,
                                onColorsNotFound = { unknownColors = it },
                            ) ?: return@ActionButton

                            controller.imageVectorCode = when (currentTabIndex) {
                                0 -> svgData.toImageVectorCode()
                                else -> svgData.toImageVectorCode()
                            }
                            imageVector = svgData.toImageVector()
                        },
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
                        onClick = { controller.showIconNameDialog = true },
                        selected = true,
                        value = iconName,
                        onValueChange = { iconName = it },
                        isDark = controller.isDark
                    )
                }
            }
            ImageView(
                isDark = controller.isDark,
                imageVector = imageVector,
                onDarkClick = { controller.isDark = !controller.isDark }
            )
        }
        if (controller.showIconNameDialog) {
            IconNameDialog(
                onValidateClick = {
                    clipboardManager.setText(controller.copyImageVector(it))
                },
                onCancelClick = { controller.showIconNameDialog = false },
            )
        } else if (unknownColors.isNotEmpty()) {
            AskForValidColorDialog(
                colorsValue = unknownColors,
                onUnknownColorsMapped = { validColors ->
                    UnknownColors.unknownColors.putAll(validColors)
                    unknownColors = emptySet()

                    if (validColors.isNotEmpty()) {
                        val svgData = controller.buildSvgData(
                            currentTabIndex = currentTabIndex,
                            vectorDrawableValue = vectorDrawableTextFieldValue.text,
                            svgPathValue = svgPathTextFieldValue.text,
                            onColorsNotFound = { unknownColors = it },
                        ) ?: return@AskForValidColorDialog

                        pathDecomposed = svgData.toPathDecomposed()
                        controller.imageVectorCode = svgData.toImageVectorCode()
                        imageVector = svgData.toImageVector()
                    }
                },
            )
        }
    }
}
