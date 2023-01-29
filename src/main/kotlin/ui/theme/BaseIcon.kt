package ui.theme

import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.icons.materialIcon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.path

@Composable
fun BaseIcon(
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
    baseVector: BaseVector = BaseVector.Logo
) = Icon(
    imageVector = baseVector.toPath(),
    contentDescription = "",
    modifier = modifier,
    tint = tint
)

enum class BaseVector {
    Plus,
    Moon,
    Minus,
    Logo,
    FileXml,
    FileSvg,
    Compose;

    fun toPath(isDark: Boolean = false, color: BaseColor = BaseColor.Primary) = when (this) {
        Compose -> composePath(color, isDark)
        Plus -> plusPath(color, isDark)
        Moon -> moonPath(color, isDark)
        Minus -> minusPath(color, isDark)
        Logo -> logoPath(color, isDark)
        FileXml -> fileXmlPath(color, isDark)
        FileSvg -> fileSvgPath(color, isDark)
    }
}

private fun plusPath(
    baseColor: BaseColor = BaseColor.Primary,
    isDark: Boolean = false
) = materialIcon(name = "Plus") {
    path(
        fill = SolidColor(baseColor.toColor(isDark)),
        fillAlpha = 1.0F,
        strokeAlpha = 1.0F,
        strokeLineWidth = 0.0F,
        strokeLineCap = StrokeCap.Butt,
        strokeLineJoin = StrokeJoin.Miter,
        strokeLineMiter = 4.0F,
        pathFillType = PathFillType.NonZero,
    ) {
        moveTo(15.6F, 5.6F)
        curveTo(15.6F, 3.612F, 13.988F, 2.0F, 12.0F, 2.0F)
        curveTo(10.012F, 2.0F, 8.4F, 3.612F, 8.4F, 5.6F)
        verticalLineTo(8.4F)
        horizontalLineTo(5.6F)
        curveTo(3.612F, 8.4F, 2.0F, 10.012F, 2.0F, 12.0F)
        curveTo(2.0F, 13.988F, 3.612F, 15.6F, 5.6F, 15.6F)
        horizontalLineTo(8.4F)
        verticalLineTo(18.4F)
        curveTo(8.4F, 20.388F, 10.012F, 22.0F, 12.0F, 22.0F)
        curveTo(13.988F, 22.0F, 15.6F, 20.388F, 15.6F, 18.4F)
        verticalLineTo(15.6F)
        horizontalLineTo(18.4F)
        curveTo(20.388F, 15.6F, 22.0F, 13.988F, 22.0F, 12.0F)
        curveTo(22.0F, 10.012F, 20.388F, 8.4F, 18.4F, 8.4F)
        horizontalLineTo(15.6F)
        verticalLineTo(5.6F)
        close()
    }
}

private fun moonPath(
    baseColor: BaseColor = BaseColor.Primary,
    isDark: Boolean = false
) = materialIcon(name = "Moon") {
    path(
        fill = SolidColor(baseColor.toColor(isDark))
    ) {
        moveTo(9.297F, 2.0F)
        curveTo(6.131F, 3.85F, 4.0F, 7.317F, 4.0F, 11.288F)
        curveTo(4.0F, 17.204F, 8.729F, 22.0F, 14.563F, 22.0F)
        curveTo(16.642F, 22.0F, 18.582F, 21.391F, 20.216F, 20.338F)
        curveTo(13.746F, 18.198F, 9.07F, 12.03F, 9.07F, 4.754F)
        curveTo(9.07F, 3.815F, 9.148F, 2.895F, 9.297F, 2.0F)
        close()
    }
}

private fun minusPath(
    baseColor: BaseColor = BaseColor.Primary,
    isDark: Boolean = false
) = materialIcon(name = "Minus") {
    path(
        fill = SolidColor(baseColor.toColor(isDark))
    ) {
        moveTo(2.0F, 11.6F)
        curveTo(2.0F, 9.612F, 3.612F, 8.0F, 5.6F, 8.0F)
        horizontalLineTo(18.4F)
        curveTo(20.388F, 8.0F, 22.0F, 9.612F, 22.0F, 11.6F)
        curveTo(22.0F, 13.588F, 20.388F, 15.2F, 18.4F, 15.2F)
        horizontalLineTo(5.6F)
        curveTo(3.612F, 15.2F, 2.0F, 13.588F, 2.0F, 11.6F)
        close()
    }
}

private fun logoPath(
    baseColor: BaseColor = BaseColor.Primary,
    isDark: Boolean = false
) = materialIcon(name = "Logo") {
    path(
        fill = SolidColor(baseColor.toColor(isDark))
    ) {
        moveTo(2.0F, 6.23F)
        curveTo(2.0F, 7.461F, 2.997F, 8.459F, 4.226F, 8.459F)
        curveTo(5.189F, 8.459F, 6.009F, 7.847F, 6.319F, 6.99F)
        curveTo(7.357F, 7.156F, 8.313F, 7.287F, 9.208F, 7.383F)
        verticalLineTo(8.689F)
        curveTo(8.64F, 8.997F, 8.118F, 9.387F, 7.659F, 9.847F)
        curveTo(7.034F, 10.472F, 6.539F, 11.214F, 6.201F, 12.031F)
        curveTo(5.864F, 12.845F, 5.691F, 13.718F, 5.689F, 14.599F)
        horizontalLineTo(3.894F)
        curveTo(3.299F, 14.599F, 2.817F, 15.082F, 2.817F, 15.678F)
        lineTo(2.817F, 19.921F)
        curveTo(2.817F, 20.517F, 3.299F, 21.0F, 3.894F, 21.0F)
        horizontalLineTo(8.131F)
        curveTo(8.726F, 21.0F, 9.208F, 20.517F, 9.208F, 19.921F)
        lineTo(9.208F, 15.678F)
        curveTo(9.208F, 15.082F, 8.726F, 14.599F, 8.131F, 14.599F)
        horizontalLineTo(6.445F)
        curveTo(6.446F, 13.817F, 6.6F, 13.043F, 6.899F, 12.321F)
        curveTo(7.199F, 11.596F, 7.639F, 10.937F, 8.193F, 10.382F)
        curveTo(8.501F, 10.073F, 8.842F, 9.799F, 9.208F, 9.566F)
        verticalLineTo(9.996F)
        curveTo(9.208F, 10.592F, 9.691F, 11.075F, 10.285F, 11.075F)
        lineTo(14.522F, 11.075F)
        curveTo(15.117F, 11.075F, 15.6F, 10.592F, 15.6F, 9.996F)
        verticalLineTo(9.555F)
        curveTo(15.973F, 9.791F, 16.319F, 10.068F, 16.633F, 10.382F)
        curveTo(17.187F, 10.937F, 17.627F, 11.596F, 17.927F, 12.321F)
        curveTo(18.226F, 13.043F, 18.38F, 13.817F, 18.381F, 14.599F)
        horizontalLineTo(16.677F)
        curveTo(16.082F, 14.599F, 15.6F, 15.082F, 15.6F, 15.678F)
        verticalLineTo(19.921F)
        curveTo(15.6F, 20.517F, 16.082F, 21.0F, 16.677F, 21.0F)
        horizontalLineTo(20.914F)
        curveTo(21.509F, 21.0F, 21.991F, 20.517F, 21.991F, 19.921F)
        verticalLineTo(15.678F)
        curveTo(21.991F, 15.082F, 21.509F, 14.599F, 20.914F, 14.599F)
        horizontalLineTo(19.136F)
        curveTo(19.135F, 13.718F, 18.961F, 12.845F, 18.625F, 12.031F)
        curveTo(18.287F, 11.214F, 17.792F, 10.472F, 17.167F, 9.847F)
        curveTo(16.703F, 9.382F, 16.175F, 8.989F, 15.6F, 8.679F)
        verticalLineTo(7.425F)
        curveTo(16.299F, 7.344F, 17.0F, 7.234F, 17.721F, 7.094F)
        curveTo(18.059F, 7.896F, 18.851F, 8.459F, 19.774F, 8.459F)
        curveTo(21.003F, 8.459F, 22.0F, 7.461F, 22.0F, 6.23F)
        curveTo(22.0F, 4.998F, 21.003F, 4.0F, 19.774F, 4.0F)
        curveTo(18.544F, 4.0F, 17.548F, 4.998F, 17.548F, 6.23F)
        curveTo(17.548F, 6.267F, 17.549F, 6.304F, 17.55F, 6.34F)
        curveTo(16.888F, 6.468F, 16.243F, 6.57F, 15.6F, 6.646F)
        verticalLineTo(5.753F)
        curveTo(15.6F, 5.157F, 15.117F, 4.674F, 14.522F, 4.674F)
        lineTo(10.285F, 4.674F)
        curveTo(9.691F, 4.674F, 9.208F, 5.157F, 9.208F, 5.753F)
        verticalLineTo(6.605F)
        curveTo(8.354F, 6.512F, 7.442F, 6.386F, 6.452F, 6.229F)
        curveTo(6.452F, 4.998F, 5.456F, 4.0F, 4.226F, 4.0F)
        curveTo(2.997F, 4.0F, 2.0F, 4.998F, 2.0F, 6.23F)
        close()
    }
}

private fun fileXmlPath(
    baseColor: BaseColor = BaseColor.Primary,
    isDark: Boolean = false
) = materialIcon(name = "FileXml") {
    path(
        fill = SolidColor(baseColor.toColor(isDark)),
        fillAlpha = 1.0F,
        strokeAlpha = 1.0F,
        strokeLineWidth = 0.0F,
        strokeLineCap = StrokeCap.Butt,
        strokeLineJoin = StrokeJoin.Miter,
        strokeLineMiter = 4.0F,
        pathFillType = PathFillType.EvenOdd,
    ) {
        moveTo(9.598F, 2.0F)
        horizontalLineTo(13.041F)
        curveTo(15.101F, 2.0F, 16.771F, 3.721F, 16.771F, 5.845F)
        verticalLineTo(13.239F)
        horizontalLineTo(18.492F)
        curveTo(19.601F, 13.239F, 20.5F, 14.166F, 20.5F, 15.31F)
        verticalLineTo(19.155F)
        curveTo(20.5F, 20.298F, 19.601F, 21.225F, 18.492F, 21.225F)
        horizontalLineTo(16.184F)
        curveTo(15.521F, 22.293F, 14.361F, 23.0F, 13.041F, 23.0F)
        horizontalLineTo(6.73F)
        curveTo(4.67F, 23.0F, 3.0F, 21.278F, 3.0F, 19.155F)
        verticalLineTo(7.915F)
        curveTo(3.0F, 7.816F, 3.002F, 7.718F, 3.007F, 7.62F)
        curveTo(3.012F, 7.516F, 3.02F, 7.413F, 3.03F, 7.311F)
        curveTo(3.314F, 4.424F, 5.611F, 2.151F, 8.451F, 2.007F)
        lineTo(9.598F, 2.0F)

        moveTo(14.664F, 21.225F)
        horizontalLineTo(9.311F)
        curveTo(8.202F, 21.225F, 7.303F, 20.298F, 7.303F, 19.155F)
        verticalLineTo(15.31F)
        curveTo(7.303F, 14.166F, 8.202F, 13.239F, 9.311F, 13.239F)
        horizontalLineTo(15.623F)
        verticalLineTo(5.845F)
        curveTo(15.623F, 4.375F, 14.467F, 3.183F, 13.041F, 3.183F)
        horizontalLineTo(9.598F)
        verticalLineTo(5.845F)
        curveTo(9.598F, 7.479F, 8.314F, 8.803F, 6.73F, 8.803F)
        horizontalLineTo(4.148F)
        verticalLineTo(19.155F)
        curveTo(4.148F, 20.625F, 5.304F, 21.817F, 6.73F, 21.817F)
        horizontalLineTo(13.041F)
        curveTo(13.656F, 21.817F, 14.22F, 21.595F, 14.664F, 21.225F)

        moveTo(8.451F, 3.192F)
        curveTo(6.205F, 3.335F, 4.397F, 5.142F, 4.171F, 7.432F)
        curveTo(4.165F, 7.494F, 4.16F, 7.557F, 4.156F, 7.62F)
        horizontalLineTo(6.73F)
        curveTo(7.68F, 7.62F, 8.451F, 6.825F, 8.451F, 5.845F)
        verticalLineTo(3.192F)

        moveTo(10.172F, 15.075F)
        horizontalLineTo(9.237F)
        lineTo(10.198F, 16.85F)
        lineTo(9.155F, 18.859F)
        horizontalLineTo(10.076F)
        lineTo(10.714F, 17.415F)
        lineTo(11.363F, 18.859F)
        horizontalLineTo(12.298F)
        lineTo(11.233F, 16.812F)
        lineTo(12.208F, 15.075F)
        horizontalLineTo(11.292F)
        lineTo(10.725F, 16.29F)
        lineTo(10.172F, 15.075F)

        moveTo(15.398F, 18.859F)
        lineTo(15.192F, 15.075F)
        horizontalLineTo(14.299F)
        lineTo(13.913F, 17.521F)
        lineTo(13.505F, 15.075F)
        horizontalLineTo(12.612F)
        lineTo(12.406F, 18.859F)
        horizontalLineTo(13.15F)
        lineTo(13.177F, 17.254F)
        curveTo(13.18F, 17.052F, 13.18F, 16.874F, 13.177F, 16.721F)
        curveTo(13.175F, 16.569F, 13.169F, 16.423F, 13.161F, 16.285F)
        curveTo(13.152F, 16.144F, 13.14F, 15.995F, 13.126F, 15.837F)
        lineTo(13.571F, 18.215F)
        horizontalLineTo(14.228F)
        lineTo(14.641F, 15.837F)
        curveTo(14.628F, 16.006F, 14.618F, 16.159F, 14.609F, 16.295F)
        curveTo(14.6F, 16.43F, 14.594F, 16.571F, 14.591F, 16.719F)
        curveTo(14.589F, 16.866F, 14.591F, 17.04F, 14.596F, 17.24F)
        lineTo(14.641F, 18.859F)
        horizontalLineTo(15.398F)

        moveTo(15.983F, 18.859F)
        verticalLineTo(15.075F)
        horizontalLineTo(16.851F)
        verticalLineTo(18.152F)
        horizontalLineTo(18.443F)
        lineTo(18.353F, 18.859F)
        horizontalLineTo(15.983F)
        close()
    }
    path(
        fill = SolidColor(baseColor.toColor(isDark))
    ) {
        moveTo(9.598F, 2.0F)
        lineTo(8.461F, 2.007F)
        curveTo(8.553F, 2.002F, 8.645F, 2.0F, 8.738F, 2.0F)
        horizontalLineTo(9.598F)
        close()
    }
}

private fun fileSvgPath(
    baseColor: BaseColor = BaseColor.Primary,
    isDark: Boolean = false
) = materialIcon(name = "FileSvg") {
    path(
        fill = SolidColor(baseColor.toColor(isDark)),
        fillAlpha = 1.0F,
        strokeAlpha = 1.0F,
        strokeLineWidth = 0.0F,
        strokeLineCap = StrokeCap.Butt,
        strokeLineJoin = StrokeJoin.Miter,
        strokeLineMiter = 4.0F,
        pathFillType = PathFillType.EvenOdd,
    ) {
        moveTo(9.598F, 2.0F)
        horizontalLineTo(13.041F)
        curveTo(15.101F, 2.0F, 16.771F, 3.721F, 16.771F, 5.845F)
        verticalLineTo(13.239F)
        horizontalLineTo(18.492F)
        curveTo(19.601F, 13.239F, 20.5F, 14.166F, 20.5F, 15.31F)
        verticalLineTo(19.155F)
        curveTo(20.5F, 20.298F, 19.601F, 21.225F, 18.492F, 21.225F)
        horizontalLineTo(16.184F)
        curveTo(15.521F, 22.293F, 14.361F, 23.0F, 13.041F, 23.0F)
        horizontalLineTo(6.73F)
        curveTo(4.67F, 23.0F, 3.0F, 21.278F, 3.0F, 19.155F)
        verticalLineTo(7.915F)
        curveTo(3.0F, 7.816F, 3.002F, 7.718F, 3.007F, 7.62F)
        curveTo(3.012F, 7.516F, 3.02F, 7.413F, 3.03F, 7.311F)
        curveTo(3.314F, 4.424F, 5.611F, 2.151F, 8.451F, 2.007F)
        lineTo(9.598F, 2.0F)

        moveTo(14.664F, 21.225F)
        horizontalLineTo(9.311F)
        curveTo(8.202F, 21.225F, 7.303F, 20.298F, 7.303F, 19.155F)
        verticalLineTo(15.31F)
        curveTo(7.303F, 14.166F, 8.202F, 13.239F, 9.311F, 13.239F)
        horizontalLineTo(15.623F)
        verticalLineTo(5.845F)
        curveTo(15.623F, 4.375F, 14.467F, 3.183F, 13.041F, 3.183F)
        horizontalLineTo(9.598F)
        verticalLineTo(5.845F)
        curveTo(9.598F, 7.479F, 8.314F, 8.803F, 6.73F, 8.803F)
        horizontalLineTo(4.148F)
        verticalLineTo(19.155F)
        curveTo(4.148F, 20.625F, 5.304F, 21.817F, 6.73F, 21.817F)
        horizontalLineTo(13.041F)
        curveTo(13.656F, 21.817F, 14.22F, 21.595F, 14.664F, 21.225F)

        moveTo(8.451F, 3.192F)
        curveTo(6.205F, 3.335F, 4.397F, 5.142F, 4.171F, 7.432F)
        curveTo(4.165F, 7.494F, 4.16F, 7.557F, 4.156F, 7.62F)
        horizontalLineTo(6.73F)
        curveTo(7.68F, 7.62F, 8.451F, 6.825F, 8.451F, 5.845F)
        verticalLineTo(3.192F)

        moveTo(11.964F, 18.376F)
        curveTo(12.084F, 18.196F, 12.144F, 17.988F, 12.144F, 17.753F)
        curveTo(12.144F, 17.52F, 12.1F, 17.327F, 12.012F, 17.175F)
        curveTo(11.925F, 17.022F, 11.799F, 16.895F, 11.633F, 16.795F)
        curveTo(11.469F, 16.693F, 11.271F, 16.606F, 11.04F, 16.533F)
        curveTo(10.846F, 16.469F, 10.694F, 16.414F, 10.585F, 16.367F)
        curveTo(10.475F, 16.317F, 10.398F, 16.265F, 10.354F, 16.211F)
        curveTo(10.31F, 16.156F, 10.288F, 16.089F, 10.288F, 16.009F)
        curveTo(10.288F, 15.887F, 10.338F, 15.795F, 10.439F, 15.733F)
        curveTo(10.54F, 15.669F, 10.673F, 15.637F, 10.839F, 15.637F)
        curveTo(10.987F, 15.637F, 11.133F, 15.664F, 11.276F, 15.717F)
        curveTo(11.419F, 15.768F, 11.552F, 15.847F, 11.676F, 15.954F)
        lineTo(12.086F, 15.468F)
        curveTo(11.927F, 15.312F, 11.739F, 15.192F, 11.522F, 15.108F)
        curveTo(11.305F, 15.022F, 11.054F, 14.979F, 10.77F, 14.979F)
        curveTo(10.518F, 14.979F, 10.286F, 15.023F, 10.076F, 15.111F)
        curveTo(9.866F, 15.196F, 9.698F, 15.321F, 9.573F, 15.485F)
        curveTo(9.449F, 15.648F, 9.388F, 15.846F, 9.388F, 16.077F)
        curveTo(9.388F, 16.365F, 9.478F, 16.6F, 9.658F, 16.784F)
        curveTo(9.84F, 16.966F, 10.145F, 17.125F, 10.574F, 17.259F)
        curveTo(10.83F, 17.339F, 11.005F, 17.419F, 11.098F, 17.497F)
        curveTo(11.194F, 17.575F, 11.241F, 17.683F, 11.241F, 17.822F)
        curveTo(11.241F, 17.969F, 11.185F, 18.084F, 11.072F, 18.166F)
        curveTo(10.959F, 18.246F, 10.811F, 18.286F, 10.63F, 18.286F)
        curveTo(10.434F, 18.286F, 10.254F, 18.253F, 10.089F, 18.188F)
        curveTo(9.925F, 18.12F, 9.775F, 18.025F, 9.639F, 17.904F)
        lineTo(9.2F, 18.406F)
        curveTo(9.353F, 18.561F, 9.547F, 18.691F, 9.78F, 18.796F)
        curveTo(10.014F, 18.9F, 10.305F, 18.952F, 10.651F, 18.952F)
        curveTo(10.963F, 18.952F, 11.231F, 18.901F, 11.453F, 18.799F)
        curveTo(11.676F, 18.697F, 11.846F, 18.556F, 11.964F, 18.376F)

        moveTo(14.609F, 15.075F)
        lineTo(13.918F, 18.16F)
        lineTo(13.232F, 15.075F)
        horizontalLineTo(12.311F)
        lineTo(13.364F, 18.859F)
        horizontalLineTo(14.44F)
        lineTo(15.499F, 15.075F)
        horizontalLineTo(14.609F)

        moveTo(17.214F, 18.952F)
        curveTo(16.693F, 18.952F, 16.295F, 18.783F, 16.02F, 18.444F)
        curveTo(15.746F, 18.104F, 15.609F, 17.609F, 15.609F, 16.959F)
        curveTo(15.609F, 16.529F, 15.685F, 16.168F, 15.837F, 15.875F)
        curveTo(15.989F, 15.58F, 16.194F, 15.357F, 16.451F, 15.206F)
        curveTo(16.709F, 15.055F, 16.997F, 14.979F, 17.315F, 14.979F)
        curveTo(17.583F, 14.979F, 17.809F, 15.022F, 17.993F, 15.108F)
        curveTo(18.176F, 15.193F, 18.34F, 15.306F, 18.483F, 15.446F)
        lineTo(18.027F, 15.932F)
        curveTo(17.916F, 15.83F, 17.809F, 15.755F, 17.707F, 15.706F)
        curveTo(17.604F, 15.657F, 17.488F, 15.632F, 17.357F, 15.632F)
        curveTo(17.193F, 15.632F, 17.046F, 15.677F, 16.917F, 15.769F)
        curveTo(16.79F, 15.858F, 16.69F, 16.001F, 16.616F, 16.197F)
        curveTo(16.542F, 16.392F, 16.504F, 16.648F, 16.504F, 16.964F)
        curveTo(16.504F, 17.294F, 16.53F, 17.556F, 16.581F, 17.751F)
        curveTo(16.634F, 17.945F, 16.714F, 18.086F, 16.819F, 18.171F)
        curveTo(16.926F, 18.255F, 17.06F, 18.297F, 17.222F, 18.297F)
        curveTo(17.309F, 18.297F, 17.39F, 18.287F, 17.466F, 18.267F)
        curveTo(17.542F, 18.245F, 17.612F, 18.217F, 17.677F, 18.182F)
        verticalLineTo(17.317F)
        horizontalLineTo(17.217F)
        lineTo(17.132F, 16.689F)
        horizontalLineTo(18.499F)
        verticalLineTo(18.57F)
        curveTo(18.315F, 18.686F, 18.117F, 18.779F, 17.905F, 18.848F)
        curveTo(17.695F, 18.917F, 17.465F, 18.952F, 17.214F, 18.952F)
        close()
    }
    path(
        fill = SolidColor(baseColor.toColor(isDark)),
        fillAlpha = 1.0F,
        strokeAlpha = 1.0F,
        strokeLineWidth = 0.0F,
        strokeLineCap = StrokeCap.Butt,
        strokeLineJoin = StrokeJoin.Miter,
        strokeLineMiter = 4.0F,
        pathFillType = PathFillType.NonZero,
    ) {
        moveTo(9.598F, 2.0F)
        lineTo(8.461F, 2.007F)
        curveTo(8.553F, 2.002F, 8.645F, 2.0F, 8.738F, 2.0F)
        horizontalLineTo(9.598F)
        close()
    }
}

private fun composePath(
    baseColor: BaseColor = BaseColor.Primary,
    isDark: Boolean = false
) = materialIcon(name = "Compose") {
    path(
        fill = SolidColor(baseColor.toColor(isDark)),
        fillAlpha = 1.0F,
        strokeAlpha = 1.0F,
        strokeLineWidth = 0.0F,
        strokeLineCap = StrokeCap.Butt,
        strokeLineJoin = StrokeJoin.Miter,
        strokeLineMiter = 4.0F,
        pathFillType = PathFillType.EvenOdd,
    ) {
        moveTo(13.847F, 1.495F)
        curveTo(12.704F, 0.835F, 11.296F, 0.835F, 10.153F, 1.495F)
        lineTo(3.848F, 5.135F)
        curveTo(2.704F, 5.795F, 2.0F, 7.015F, 2.0F, 8.335F)
        verticalLineTo(15.616F)
        curveTo(2.0F, 16.936F, 2.704F, 18.156F, 3.848F, 18.816F)
        lineTo(10.153F, 22.456F)
        curveTo(11.296F, 23.116F, 12.704F, 23.116F, 13.847F, 22.456F)
        lineTo(20.153F, 18.816F)
        curveTo(21.296F, 18.156F, 22.0F, 16.936F, 22.0F, 15.616F)
        verticalLineTo(8.335F)
        curveTo(22.0F, 7.015F, 21.296F, 5.795F, 20.153F, 5.135F)
        lineTo(13.847F, 1.495F)

        moveTo(12.604F, 7.68F)
        curveTo(12.116F, 7.395F, 11.514F, 7.395F, 11.026F, 7.68F)
        lineTo(8.332F, 9.25F)
        curveTo(7.843F, 9.535F, 7.543F, 10.061F, 7.543F, 10.63F)
        verticalLineTo(13.771F)
        curveTo(7.543F, 14.34F, 7.843F, 14.866F, 8.332F, 15.151F)
        lineTo(11.026F, 16.721F)
        curveTo(11.514F, 17.006F, 12.116F, 17.006F, 12.604F, 16.721F)
        lineTo(15.298F, 15.151F)
        curveTo(15.786F, 14.866F, 16.087F, 14.34F, 16.087F, 13.771F)
        verticalLineTo(10.63F)
        curveTo(16.087F, 10.061F, 15.786F, 9.535F, 15.298F, 9.25F)
        lineTo(12.604F, 7.68F)
        close()
    }
}