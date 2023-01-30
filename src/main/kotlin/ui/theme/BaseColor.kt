package ui.theme

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val blue01 = Color(0xFFEBFBFF)
val blue02 = Color(0xFF62B0DC)
val blue03 = Color(0xFF89E7FF)
val blue04 = Color(0xFF0281C8)

val dark01 = Color(0xFF21252B)
val dark02 = Color(0xFF262B32)
val dark03 = Color(0xFF4C6180)

val light01 = Color(0xFFFFFFFF)
val light02 = Color(0xFFF9F9F9)
val light03 = Color(0xFFB7B7B7)

val dark01_80 = Color(0xCC21252B)
val light01_80 = Color(0xCCFFFFFF)
val blue01_80 = Color(0x33EBFBFF)
val blue03_80 = Color(0x3389E7FF)

enum class BaseColor(
    private val ligth:Color,
    private val dark:Color
){

    Primary(light01, dark01),
    Secondary(light02, dark02),
    Tertiary(light03, dark03),
    Blue01(blue01, blue03),
    Blue02(blue02, blue04),

    Primary80(light01_80, dark01_80),
    Blue0180(blue01_80, blue03_80);

    fun toColor(isDark: Boolean = false) = if(isDark) this.ligth else this.dark
}

@Preview
@Composable
fun example(){
    Text("text")
}