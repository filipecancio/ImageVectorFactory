package ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.sp


val projectFontFamily: FontFamily = FontFamily(
    Font("font/fira_code_regular.ttf")
)

val BaseType = Typography(
    body1 = TextStyle(
        fontFamily = projectFontFamily,
        color = BaseColor.Tertiary.toColor(),
        fontSize = 12.sp
    )
)