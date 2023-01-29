package ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import domain.SvgPathParser
import domain.VectorDrawableParser
import model.SvgData
import ui.theme.BaseVector

class MainController(
    private val clipboardManager: ClipboardManager
) {
    var isDark by mutableStateOf(false)
    var currentTabIndex by mutableStateOf(0)
    var textFieldValue by mutableStateOf(TextFieldValue(""))
    var imageVectorCode by mutableStateOf("")
    var pathDecomposed by mutableStateOf("")
    var imageVector by mutableStateOf<ImageVector?>(null)
    var unknownColors by mutableStateOf(emptySet<String>())
    var iconName by mutableStateOf(TextFieldValue("untitled"))

    val convertOptions = listOf(
        ConvertOptions.DrawablePath,
        ConvertOptions.SvgPath
    )

    fun clearValues(index: Int) {
        pathDecomposed = ""
        imageVectorCode = ""
        imageVector = null
        currentTabIndex = index
    }

    fun getCurrentPlaceholder() = convertOptions[currentTabIndex].placeholder

    fun copyImageVector() {
        clipboardManager.setText(AnnotatedString(imageVectorCode))
    }

    fun replaceCodeName(newName: TextFieldValue) {
        val oldName = iconName
        iconName = newName
        val path = imageVectorCode.replace(
            "${oldName.text.firstOrNull()?.uppercase()}${oldName.text.substring(1)}",
            "${iconName.text.firstOrNull()?.uppercase()}${iconName.text.substring(1)}",
        ).replace(
            "${oldName.text.firstOrNull()?.lowercase()}${oldName.text.substring(1)}",
            "${iconName.text.firstOrNull()?.lowercase()}${iconName.text.substring(1)}",
        )
        imageVectorCode = path
    }

    fun buildSvgData(): SvgData? = if (currentTabIndex == 0 && textFieldValue.text.isNotBlank()) {
        VectorDrawableParser.toSvgData(textFieldValue.text) { unknownColors = it }
    } else if (currentTabIndex == 1 && textFieldValue.text.isNotBlank()) {
        SvgPathParser.toSvgData(svgPath = textFieldValue.text)
    } else {
        null
    }

    fun updateImageVectorCode(svgData: SvgData){
        imageVectorCode = when (currentTabIndex) {
            0 -> svgData.toImageVectorCode()
            else -> svgData.toImageVectorCode()
        }
        imageVector = svgData.toImageVector()
    }

    enum class ConvertOptions(
        val label: String,
        val placeholder: String,
        val icon: BaseVector
    ) {
        DrawablePath(
            label = "Drawable",
            icon = BaseVector.FileXml,
            placeholder = "Insert Drawable path here:"
        ),
        SvgPath(
            label = "SVG Path",
            icon = BaseVector.FileSvg,
            placeholder = "Insert SVG path here:"
        ),
        SvgFile(
            label = "SVG File",
            icon = BaseVector.FileSvg,
            placeholder = "Insert SVG file here:"
        )
    }
}