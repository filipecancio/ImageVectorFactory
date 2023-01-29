import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.darkColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import domain.SvgPathParser
import domain.UnknownColors
import domain.VectorDrawableParser
import model.SvgData
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
fun MainScreen() {
    MaterialTheme(colors = darkColors()) {
        val clipboardManager = LocalClipboardManager.current
        var currentTabIndex by remember { mutableStateOf(0) }
//        var svgFileTextFieldValue by remember { mutableStateOf(TextFieldValue("")) }
        var vectorDrawableTextFieldValue by remember { mutableStateOf(TextFieldValue("")) }
        var svgPathTextFieldValue by remember { mutableStateOf(TextFieldValue("")) }
        var pathDecomposed by remember { mutableStateOf("") }
        var imageVectorCode by remember { mutableStateOf("") }
        var imageVector by remember { mutableStateOf<ImageVector?>(null) }
        var showImageBlackBackground by remember { mutableStateOf(false) }
        var showIconNameDialog by remember { mutableStateOf(false) }
        var unknownColors by remember { mutableStateOf(emptySet<String>()) }
        var iconName by remember { mutableStateOf(TextFieldValue("Untitled")) }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BaseColor.Primary.toColor(showImageBlackBackground)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            TopBar(showImageBlackBackground) {
                TabButton(
                    text = "Drawable",
                    baseVector = BaseVector.FileXml,
                    isEnabled = currentTabIndex == 0,
                    onClick = {
                        pathDecomposed = ""
                        imageVectorCode = ""
                        imageVector = null
                        currentTabIndex = 0
                    },
                    isDark = showImageBlackBackground
                )
                TabButton(
                    text = "SVG Path",
                    baseVector = BaseVector.FileSvg,
                    isEnabled = currentTabIndex == 1,
                    onClick = {
                        pathDecomposed = ""
                        imageVectorCode = ""
                        imageVector = null
                        currentTabIndex = 1
                    },
                    isDark = showImageBlackBackground
                )
                /*
                TabButton(
                    text = "SVG File",
                    baseVector = BaseVector.FileSvg,
                    isEnabled = currentTabIndex == 2,
                    onClick = {
                        pathDecomposed = ""
                        imageVectorCode = ""
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
                        style = getBaseType(showImageBlackBackground).body1
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
                        isDark = showImageBlackBackground
                    )
                    ActionButton(
                        text = "Convert",
                        onClick = {
                            val svgData = buildSvgData(
                                currentTabIndex = currentTabIndex,
                                vectorDrawableValue = vectorDrawableTextFieldValue.text,
                                svgPathValue = svgPathTextFieldValue.text,
                                onColorsNotFound = { unknownColors = it },
                            ) ?: return@ActionButton

                            imageVectorCode = when (currentTabIndex) {
                                0 -> svgData.toImageVectorCode()
                                else -> svgData.toImageVectorCode()
                            }
                            imageVector = svgData.toImageVector()
                        },
                        isDark = showImageBlackBackground
                    )
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        "The Image Vector code:",
                        modifier = Modifier.width(300.dp),
                        style = getBaseType(showImageBlackBackground).body2
                    )
                    CodeEdit(
                        value = TextFieldValue(imageVectorCode),
                        selected = true,
                        onValueChange = { },
                        isDark = showImageBlackBackground
                    )
                    ActionBar(
                        buttonText = "Copy",
                        onClick = { showIconNameDialog = true },
                        selected = true,
                        value = iconName,
                        onValueChange = { iconName = it },
                        isDark = showImageBlackBackground
                    )
                }
            }
            ImageView(
                isDark = showImageBlackBackground,
                imageVector = imageVector,
                onDarkClick = { showImageBlackBackground = !showImageBlackBackground }
            )
        }
        if (showIconNameDialog) {
            IconNameDialog(
                onValidateClick = {
                    val path = imageVectorCode.replace(
                        "[IconName]",
                        "${it.firstOrNull()?.uppercase()}${it.substring(1)}",
                    ).replace(
                        "[iconName]",
                        "${it.firstOrNull()?.lowercase()}${it.substring(1)}",
                    )
                    clipboardManager.setText(AnnotatedString(path))
                    showIconNameDialog = false
                },
                onCancelClick = { showIconNameDialog = false },
            )
        } else if (unknownColors.isNotEmpty()) {
            AskForValidColorDialog(
                colorsValue = unknownColors,
                onUnknownColorsMapped = { validColors ->
                    UnknownColors.unknownColors.putAll(validColors)
                    unknownColors = emptySet()

                    if (validColors.isNotEmpty()) {
                        val svgData = buildSvgData(
                            currentTabIndex = currentTabIndex,
                            vectorDrawableValue = vectorDrawableTextFieldValue.text,
                            svgPathValue = svgPathTextFieldValue.text,
                            onColorsNotFound = { unknownColors = it },
                        ) ?: return@AskForValidColorDialog

                        pathDecomposed = svgData.toPathDecomposed()
                        imageVectorCode = svgData.toImageVectorCode()
                        imageVector = svgData.toImageVector()
                    }
                },
            )
        }
    }
}

private fun buildSvgData(
    currentTabIndex: Int,
    vectorDrawableValue: String,
    svgPathValue: String,
    onColorsNotFound: (colorValues: Set<String>) -> Unit,
): SvgData? =
//  if (currentTabIndex == 0 && svgFileTextFieldValue.text.isNotBlank()) {
//      XmlParser.model.SvgData()
//  }
    if (currentTabIndex == 0 && vectorDrawableValue.isNotBlank()) {
        VectorDrawableParser.toSvgData(vectorDrawableValue, onColorsNotFound)
    } else if (currentTabIndex == 1 && svgPathValue.isNotBlank()) {
        SvgPathParser.toSvgData(svgPath = svgPathValue)
    } else {
        null
    }
