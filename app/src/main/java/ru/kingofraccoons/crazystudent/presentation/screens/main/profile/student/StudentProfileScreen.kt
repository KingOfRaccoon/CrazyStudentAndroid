package ru.kingofraccoons.crazystudent.presentation.screens.main.profile.student

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.kingofraccoons.crazystudent.NavigationFun
import ru.kingofraccoons.crazystudent.R
import ru.kingofraccoons.crazystudent.presentation.elements.BodyText
import ru.kingofraccoons.crazystudent.presentation.elements.Caption1Text
import ru.kingofraccoons.crazystudent.ui.theme.CrazyStudentTheme

@Composable
fun StudentProfileScreen(
    navigateToStudyPlane: NavigationFun,
    navigateToAdditionEducation: NavigationFun,
    navigateToRecordBook: NavigationFun,
    navigateToPreferences: NavigationFun,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DoubleBorderedProfileImage(modifier = Modifier.fillMaxWidth(0.5f))

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Василий Петров",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        TaskCard(
            header = "Мероприятия",
            description = "выбери по душе",
            color = Color(0xFF85FFA0),
            icon = Icons.Outlined.DateRange,
            onClick = navigateToRecordBook
        )

        TaskCard(
            header = "Увлечения",
            description = "добавь новые",
            color = Color(0xFF85FFA0),
            icon = Icons.Outlined.Favorite,
            onClick = navigateToPreferences
        )

        TaskCard(
            header = "Учебный план",
            description = "будь в курсе",
            color = Color(0xFF70DDFF),
            icon = Icons.Outlined.Info,
            onClick = navigateToStudyPlane
        )

        TaskCard(
            header = "Калькулятор ЕГЭ",
            description = "подбери направление",
            color = Color(0xFF70DDFF),
            icon = Icons.Outlined.Search,
            onClick = { TODO()}
        )

        TaskCard(
            header = "Цифровые кафедры",
            description = "исследуй новое",
            color = Color(0xFFFFDFF4),
            icon = Icons.Outlined.Place,
            onClick = navigateToAdditionEducation
        )
    }
}

@Composable
fun TaskCard(
    header: String, description: String, color: Color, icon: ImageVector,
    onClick: () -> Unit = {},
) {
    Card(
        modifier = Modifier.padding(vertical = 4.dp).clickable { onClick() },
        colors = CardDefaults.cardColors(Color(0xFF2B2C34)),
        shape = RoundedCornerShape(24.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier
                    .size(50.dp),
                colors = CardDefaults.cardColors(color),
                shape = RoundedCornerShape(16.dp),
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = "icon",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                )
            }
            Spacer(Modifier.width(16.dp))
            Column {
                BodyText(
                    text = header, modifier = Modifier
                )
                Caption1Text(
                    text = description, modifier = Modifier
                )
            }
        }
    }
}

@Preview
@Composable
fun DoubleBorderedProfileImage(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .border(
                width = 3.dp, color = Color.White, shape = CircleShape
            )
            .padding(2.dp)
            .border(
                width = 3.dp, color = Color.Black, shape = CircleShape
            )
            .padding(2.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.avatar_1),
            contentDescription = "Profile image",
            modifier = Modifier
                .fillMaxWidth()
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    CrazyStudentTheme {
        StudentProfileScreen(navigateToStudyPlane = { },
            navigateToAdditionEducation = {},
            navigateToRecordBook = {},
            navigateToPreferences = {})
    }
}