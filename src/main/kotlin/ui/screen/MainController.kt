package ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import domain.SvgPathParser
import domain.VectorDrawableParser
import model.SvgData

class MainController {
    var isDark by mutableStateOf(false)
    var currentTabIndex by mutableStateOf(0)
    var imageVectorCode by mutableStateOf("")
    var showIconNameDialog by mutableStateOf(false)
    var vectorDrawableTextFieldValue by   mutableStateOf(TextFieldValue(""))
    var svgPathTextFieldValue by mutableStateOf(TextFieldValue(""))
    var pathDecomposed by mutableStateOf("")
    var imageVector by mutableStateOf<ImageVector?>(null)
    var unknownColors by mutableStateOf(emptySet<String>())
    var iconName by mutableStateOf(TextFieldValue("Untitled"))

    fun copyImageVector(name: String): AnnotatedString {
        val path = imageVectorCode.replace(
            "[IconName]",
            "${name.firstOrNull()?.uppercase()}${name.substring(1)}",
        ).replace(
            "[iconName]",
            "${name.firstOrNull()?.lowercase()}${name.substring(1)}",
        )
        showIconNameDialog = false
        return AnnotatedString(path)
    }

    fun buildSvgData(
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
}