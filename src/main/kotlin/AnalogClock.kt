import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun AnalogClock() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
        content = {
            Box(
                modifier = Modifier
                    .size(size.dp)
                    .drawBehind {

                        drawCircle(
                            brush = Brush.linearGradient(
                                colors = colors
                            ),
                            center = Offset(radius, radius),
                            radius = outerRadius,
                            style = Stroke(
                                width = 10f,
                                cap = StrokeCap.Round
                            )
                        )
                        (1..12).forEach { n ->
                            drawLine(
                                brush = Brush.linearGradient(
                                    colors = colors
                                ),
                                start = Offset(
                                    x = radius,
                                    y = radius
                                ),
                                end = Offset(
                                    x = (cos((n * 30 * PI)/180).toFloat() * radius) + radius,
                                    y = (sin((n * 30 * PI)/180).toFloat() * radius) + radius,
                                ),
                                strokeWidth = 5f,
                                cap = StrokeCap.Round
                            )
                        }
                    }
            )
        }
    )
}


private const val radius = 225f
private const val outerRadius = 235f
private const val size = outerRadius * 2f

private val colors = listOf(
    Color.Magenta,
    Color.Blue,
    Color.Red
)