import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.util.*
import kotlin.math.roundToInt
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

@Composable
fun Clock() {
    var second by rememberSaveable {
        mutableStateOf(0f)
    }

    var minute by rememberSaveable {
        mutableStateOf(0f)
    }

    var hour by rememberSaveable {
        mutableStateOf(0f)
    }

    var ampm by rememberSaveable {
        mutableStateOf("AM")
    }

    LaunchedEffect(second) {
        delay(1000)
        ampm = if (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) > 11) "PM" else "AM"
        minute = Calendar.getInstance().get(Calendar.MINUTE) * 6f
        hour = Calendar.getInstance().get(Calendar.HOUR) * 30f
        second = Calendar.getInstance().get(Calendar.SECOND) * 6f
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .requiredSize(clockSize),
            contentAlignment = Alignment.Center
        ) {
            // Hour
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .drawBehind {
                        drawArc(
                            brush = Brush.linearGradient(
                                colors = colors
                            ),
                            startAngle = 270f,
                            sweepAngle = if (hour == 0f) 1f else hour + (((minute / 6) / 60) * 30f),
                            useCenter = false,
                            style = Stroke(
                                width = 15f,
                                cap = StrokeCap.Round
                            )
                        )
                    }
            )

            // Minute
            Box(
                modifier = Modifier
                    .fillMaxSize(0.9f)
                    .drawBehind {
                        drawArc(
                            brush = Brush.linearGradient(
                                colors = colors.reversed()
                            ),
                            startAngle = 270f,
                            sweepAngle = minute + (((second / 6) / 60) * 6),
                            useCenter = false,
                            style = Stroke(
                                width = 15f,
                                cap = StrokeCap.Round
                            )
                        )
                    }
            )

            // Second
            Box(
                modifier = Modifier
                    .fillMaxSize(0.8f)
                    .drawBehind {
                        drawArc(
                            brush = Brush.linearGradient(
                                colors = colors.shuffled()
                            ),
                            startAngle = 270f,
                            sweepAngle = second,
                            useCenter = false,
                            style = Stroke(
                                width = 15f,
                                cap = StrokeCap.Round
                            )
                        )
                    }
            )
        }


        // Text
        Text(
            text = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        color = colors.random(),
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("${hour.roundToInt() / 30}")
                }
                append("\n")
                withStyle(
                    SpanStyle(
                        color = colors.random(),
                        fontSize = 60.sp,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(if ((minute.roundToInt() / 6) < 10) "0${minute.roundToInt() / 6}" else "${minute.roundToInt() / 6}")
                }
                append("\n")
                withStyle(
                    SpanStyle(
                        color = colors.random(),
                        fontSize = 100.sp,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(if ((second.roundToInt() / 6) < 10) " 0${second.roundToInt() / 6}" else " ${second.roundToInt() / 6}")
                }

                withStyle(
                    SpanStyle(
                        color = colors.random(),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        fontFamily = FontFamily.SansSerif
                    )
                ) {
                    append(ampm)
                }

            },
            textAlign = TextAlign.Center
        )
    }
}



private val clockSize = 420.dp

private val colors: List<Color> = listOf(
    Color.Blue,
    Color.Magenta,
    Color.Red
)
