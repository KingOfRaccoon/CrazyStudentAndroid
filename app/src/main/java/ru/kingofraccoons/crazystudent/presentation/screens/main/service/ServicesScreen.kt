package ru.kingofraccoons.crazystudent.presentation.screens.main.service

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.kingofraccoons.crazystudent.NavigationFun
import ru.kingofraccoons.crazystudent.R
import ru.kingofraccoons.crazystudent.presentation.elements.BodyText
import ru.kingofraccoons.crazystudent.presentation.elements.HeadlineText
import ru.kingofraccoons.crazystudent.presentation.elements.LargeTitleText
import ru.kingofraccoons.crazystudent.presentation.elements.Title1Text
import ru.kingofraccoons.crazystudent.presentation.elements.Title2Text
import ru.kingofraccoons.crazystudent.ui.theme.CrazyStudentTheme

@Composable
fun ServicesScreen(
    navigateToVacancies: NavigationFun,
    navigateToInternship: NavigationFun,
    navigateToSelectHousing: NavigationFun,
) {
    Scaffold(
        Modifier
            .fillMaxSize()
            .systemBarsPadding(), topBar = {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
                    .padding(horizontal = 16.dp)
            ) {
                Image(
                    painterResource(R.drawable.small_logo),
                    "",
                    Modifier.align(Alignment.CenterHorizontally)
                )
                Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                    LargeTitleText("Сервисы", Modifier)
                    IconButton(
                        {},
                        Modifier.clip(RoundedCornerShape(size = 34.dp)),
                        colors = IconButtonDefaults.iconButtonColors(Color(0xFF70DDFF))
                    ) {
                        Icon(
                            painterResource(R.drawable.sparkles),
                            null,
                            Modifier.padding(11.dp, 8.dp)
                        )
                    }
                }
            }
        }, contentColor = Color.Black, containerColor = Color.Black
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp)
        ) {

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                BodyText("Учеба и карьера", Modifier)
                BodyText("Все", Modifier, color = Color(0xFF70DDFF))
            }
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                Modifier
                    .fillMaxWidth()
                    .height(155.dp), Arrangement.spacedBy(8.dp)
            ) {
                CuteCard(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    text = "Вакансии",
                    onClick = navigateToVacancies,
                    accentColor = Color(0xff85FFA0),
                    icon = ImageVector.vectorResource(R.drawable.bag2),
                    roundedCornerTL = true,
                    roundedCornerTR = false,
                    roundedCornerBL = false,
                    roundedCornerBR = true
                )

                CuteCard(
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxHeight(),
                    text = "Стажировки",
                    onClick = navigateToInternship,
                    accentColor = Color(0xFF70DDFF),
                    icon = ImageVector.vectorResource(R.drawable.clock_circle),
                    roundedCornerTL = false,
                    roundedCornerTR = true,
                    roundedCornerBL = true,
                    roundedCornerBR = false
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            CuteCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(155.dp),
                accentColor = Color(0xff85FFA0),
                text = "Дополнительное образование",
                icon = ImageVector.vectorResource(R.drawable.folder_plus),
                roundedCornerTL = false,
            )


            Spacer(modifier = Modifier.height(24.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                BodyText("Жизнь и досуг", Modifier)
                BodyText("Все", Modifier, color = Color(0xFF70DDFF))
            }
            Spacer(modifier = Modifier.height(16.dp))

            Row(Modifier.fillMaxWidth(), Arrangement.spacedBy(8.dp)) {
                CuteCard(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(115 / 155f),
                    text = "Общежития",
                    onClick = navigateToSelectHousing,
                    accentColor = Color(0xff8287FF),
                    icon = ImageVector.vectorResource(R.drawable.home_2),
                )
                CuteCard(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(115 / 155f),
                    text = "Аренда квартир",
                    accentColor = Color(0xff85FFA0),
                    icon = ImageVector.vectorResource(R.drawable.wallet),
                )
                CuteCard(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(115 / 155f),
                    text = "Клубы",
                    accentColor = Color(0x8f8287FF),
                    icon = ImageVector.vectorResource(R.drawable.message_2),
                )
            }
        }
    }
}

@Composable
fun CuteCard(
    modifier: Modifier = Modifier,
    text: String,
    icon: ImageVector,
    accentColor: Color = Color.Green,
    onClick: NavigationFun = {},
    roundedCornerTL: Boolean = true,
    roundedCornerTR: Boolean = true,
    roundedCornerBL: Boolean = true,
    roundedCornerBR: Boolean = true,
) {
    val brush = Brush.radialGradient(
        colorStops = arrayOf(
            0f to accentColor, 1f to accentColor.copy(alpha = accentColor.alpha * 0.4f)
        ),
    )

    Card(
        modifier.clickable(onClick = onClick),
        colors = CardDefaults.cardColors(Color(0xFF70DDFF), Color(0xFF70DDFF)),
        shape = RoundedCornerShape(
            topStart = if (roundedCornerTL) 24.dp else 4.dp,
            topEnd = if (roundedCornerTR) 24.dp else 4.dp,
            bottomEnd = if (roundedCornerBR) 24.dp else 4.dp,
            bottomStart = if (roundedCornerBL) 24.dp else 4.dp,
        )
    ) {
        Box(
            modifier = Modifier
                .background(brush)
                .fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxHeight()
            ) {
                BodyText(text, Modifier.padding(16.dp), Color.Black)

                Row(horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth().padding(4.dp)
                ) {
                    Icon(icon, "", tint = Color.Black)
                }
            }
        }
    }
}

@Composable
@Preview
fun ServicesScreenPreview() {
    CrazyStudentTheme {
        ServicesScreen(navigateToVacancies = { TODO() },
            navigateToInternship = { TODO() },
            navigateToSelectHousing = { TODO() })
    }
}