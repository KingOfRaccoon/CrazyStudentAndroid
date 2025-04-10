package ru.kingofraccoons.crazystudent.presentation.elements

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun LargeTitleText(
    text: String,
    modifier: Modifier,
    color: Color = Color.White,
    textAlign: TextAlign = TextAlign.Start
) = Text(
    text,
    modifier,
    color,
    textAlign = textAlign,
    style = MaterialTheme.typography.displayLarge
)

@Composable
fun Title1Text(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.White,
    textAlign: TextAlign = TextAlign.Start
) = Text(
    text,
    modifier,
    color,
    textAlign = textAlign,
    style = MaterialTheme.typography.titleLarge
)

@Composable
fun Title2Text(
    text: String,
    modifier: Modifier,
    color: Color = Color.White,
    textAlign: TextAlign = TextAlign.Start
) = Text(
    text,
    modifier,
    color,
    textAlign = textAlign,
    style = MaterialTheme.typography.titleMedium
)

@Composable
fun Title3Text(
    text: String,
    modifier: Modifier,
    color: Color = Color.White,
    textAlign: TextAlign = TextAlign.Start
) = Text(
    text,
    modifier,
    color,
    textAlign = textAlign,
    style = MaterialTheme.typography.titleSmall
)

@Composable
fun HeadlineText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.White,
    textAlign: TextAlign = TextAlign.Start
) = Text(
    text,
    modifier,
    color,
    textAlign = textAlign,
    style = MaterialTheme.typography.headlineMedium
)

@Composable
fun BodyText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.White,
    textAlign: TextAlign = TextAlign.Start,
    minLines: Int = 1
) = Text(
    text,
    modifier,
    color,
    textAlign = textAlign,
    style = MaterialTheme.typography.bodyMedium,
    minLines = minLines
)

@Composable
fun SubheadText(
    text: String,
    modifier: Modifier,
    color: Color = Color.White,
    textAlign: TextAlign = TextAlign.Start
) = Text(
    text,
    modifier,
    color,
    textAlign = textAlign,
    style = MaterialTheme.typography.bodySmall
)

@Composable
fun Caption1Text(
    text: String,
    modifier: Modifier,
    color: Color = Color.White,
    textAlign: TextAlign = TextAlign.Start
) = Text(
    text,
    modifier,
    color,
    textAlign = textAlign,
    style = MaterialTheme.typography.labelMedium
)

@Composable
fun Caption2Text(
    text: String,
    modifier: Modifier,
    color: Color = Color.White,
    textAlign: TextAlign = TextAlign.Start
) = Text(
    text,
    modifier,
    color,
    textAlign = textAlign,
    style = MaterialTheme.typography.labelSmall
)