package ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import ui.theme.BaseColor
import ui.theme.BaseVector
import ui.theme.getBaseType

@ExperimentalMaterialApi
@Composable
fun MainScreen(controller: MainController) {
    MaterialTheme {
        val clipboardManager = LocalClipboardManager.current
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
                    isEnabled = controller.currentTabIndex == 0,
                    onClick = {
                        controller.pathDecomposed = ""
                        controller.imageVectorCode = ""
                        controller.imageVector = null
                        controller.currentTabIndex = 0
                    },
                    isDark = controller.isDark
                )
                TabButton(
                    text = "SVG Path",
                    baseVector = BaseVector.FileSvg,
                    isEnabled = controller.currentTabIndex == 1,
                    onClick = {
                        controller.pathDecomposed = ""
                        controller.imageVectorCode = ""
                        controller.imageVector = null
                        controller.currentTabIndex = 1
                    },
                    isDark = controller.isDark
                )
                /*
                TabButton(
                    text = "SVG File",
                    baseVector = BaseVector.FileSvg,
                    isEnabled = controller.currentTabIndex == 2,
                    onClick = {
                        controller.pathDecomposed = ""
                        controller.imageVectorCode = ""
                        controller.imageVector = null
                        controller.currentTabIndex = 2
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
                        when (controller.currentTabIndex) {
                            0 -> "Insert Drawable path here:"
                            else -> "Insert SVG path here:"
                        },
                        modifier = Modifier.width(300.dp),
                        style = getBaseType(controller.isDark).body1
                    )
                    CodeEdit(
                        value = when (controller.currentTabIndex) {
                            0 -> controller.vectorDrawableTextFieldValue
                            else -> controller.svgPathTextFieldValue
                        },
                        onValueChange = {
                            when (controller.currentTabIndex) {
                                0 -> controller.vectorDrawableTextFieldValue = it
                                else -> controller.svgPathTextFieldValue = it
                            }
                        },
                        isDark = controller.isDark
                    )
                    ActionButton(
                        text = "Convert",
                        onClick = {
                            val svgData = controller.buildSvgData(
                                currentTabIndex = controller.currentTabIndex,
                                vectorDrawableValue = controller.vectorDrawableTextFieldValue.text,
                                svgPathValue = controller.svgPathTextFieldValue.text,
                                onColorsNotFound = { controller.unknownColors = it },
                            ) ?: return@ActionButton

                            controller.imageVectorCode = when (controller.currentTabIndex) {
                                0 -> svgData.toImageVectorCode()
                                else -> svgData.toImageVectorCode()
                            }
                            controller.imageVector = svgData.toImageVector()
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
                        value = controller.iconName,
                        onValueChange = { controller.iconName = it },
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
        if (controller.showIconNameDialog) {
            IconNameDialog(
                onValidateClick = {
                    clipboardManager.setText(controller.copyImageVector(it))
                },
                onCancelClick = { controller.showIconNameDialog = false },
            )
        } else if (controller.unknownColors.isNotEmpty()) {
            AskForValidColorDialog(
                colorsValue = controller.unknownColors,
                onUnknownColorsMapped = { validColors ->
                    UnknownColors.unknownColors.putAll(validColors)
                    controller.unknownColors = emptySet()

                    if (validColors.isNotEmpty()) {
                        val svgData = controller.buildSvgData(
                            currentTabIndex = controller.currentTabIndex,
                            vectorDrawableValue = controller.vectorDrawableTextFieldValue.text,
                            svgPathValue = controller.svgPathTextFieldValue.text,
                            onColorsNotFound = { controller.unknownColors = it },
                        ) ?: return@AskForValidColorDialog

                        controller.pathDecomposed = svgData.toPathDecomposed()
                        controller.imageVectorCode = svgData.toImageVectorCode()
                        controller.imageVector = svgData.toImageVector()
                    }
                },
            )
        }
    }
}
