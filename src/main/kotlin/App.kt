import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import ui.screen.MainScreen

@ExperimentalMaterialApi
fun main() = application {
    val windowState = rememberWindowState(placement = WindowPlacement.Floating)
    Window(
        title = "Svg2Compose",
        icon = painterResource("icon.png"),
        state = windowState,
        onCloseRequest = ::exitApplication,
    ) {
        MainScreen()
    }
}
