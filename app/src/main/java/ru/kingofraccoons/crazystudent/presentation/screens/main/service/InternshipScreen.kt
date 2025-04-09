package ru.kingofraccoons.crazystudent.presentation.screens.main.service

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.kingofraccoons.crazystudent.R
import ru.kingofraccoons.crazystudent.presentation.elements.BodyText
import ru.kingofraccoons.crazystudent.presentation.elements.Caption1Text
import ru.kingofraccoons.crazystudent.ui.theme.CrazyStudentTheme


data class Internship(
    val name: String,
    val icon: Int,
    val company: String,
    val type: String,
    val description: String,
    val info: List<String>,
    val redirect: String,
)

val data = listOf(
    Internship(
        name = "Стажер бизнес-аналитик",
        icon = R.drawable.ozon,
        company = "OZON",
        type = "офис",
        description = "",
        info = emptyList(),
        redirect = ("")
    ),
    Internship(
        name = "Стажер-аналитик Big Data",
        icon = R.drawable.beeline,
        company = "Beeline",
        type = "удаленка",
        description = "",
        info = emptyList(),
        redirect = ("")
    ),
    Internship(
        name = "Back-End Developer",
        icon = R.drawable.vk,
        company = "VK company",
        type = "удаленка",
        description = """ESforce Holding — одна из крупнейших киберспортивных организаций в мире и лидер российского компьютерного спорта. Холдинговая компания объединяет все ключевые направления киберспортивного бизнеса: от организации международных турниров, создания и дистрибуции контента до рекламных и инфраструктурных проектов.

Мы ищем Backend-разработчика в Сybersport.ru — крупнейшее русскоязычное СМИ о киберспорте, где собрано все самое интересное и захватывающее из мира киберспорта и видеоигр. У нас небольшая команда, где каждый влияет на продукт. Работать можно и удаленно, и в офисе.

Требования
- практический опыт разработки на Symfony 5+ от трех лет;
- хорошее знание PHP 8+ и Golang (опыт разработки от года);
- опыт использования PostgreSQL;
- владение ООП, понимание принципов SOLID;
- опыт разработки высоконагруженных систем;
- опыт работы с брокером сообщений Kafka;
- понимание DevOps и CI/CD;
- знание Redis;
- базовое использование ClickHouse.""",
        info = listOf("Полный день", "Опыт 1 года", "Офис"),
        redirect = ("https://vk.com/jobs?w=job1119")
    ),
)

@Composable
fun InternshipScreen() {
    val selectedCategories = listOf("Аналитика", "Логистика", "Контент и дизайн")
    val deleteCategory = { category: String -> }
    val vacancies = data

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp),
    ) {
        LazyRow(
            userScrollEnabled = true,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items = selectedCategories) { category ->
                Card(
                    modifier = Modifier.clickable { deleteCategory(category) },
                    colors = CardDefaults.cardColors(Color(0xFF70DDFF)),
                    shape = RoundedCornerShape(24.dp),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(12.dp)
                    ) {
                        BodyText(
                            category, modifier = Modifier, color = Color.Black
                        )
                        Icon(Icons.Default.Clear, "")
                    }
                }
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(vacancies) {
                InternshipCard(
                    internship = it, onClick = {})
            }
        }
    }
}

@Composable
fun InternshipCard(
    internship: Internship,
    onClick: () -> Unit = {},
) {
    Card(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .clickable { onClick() },
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
                modifier = Modifier.size(50.dp),
                colors = CardDefaults.cardColors(),
                shape = RoundedCornerShape(16.dp),
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(internship.icon),
                    contentDescription = "icon",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp)
                )
            }
            Spacer(Modifier.width(16.dp))
            Column {
                BodyText(
                    text = internship.name, modifier = Modifier
                )
                Caption1Text(
                    text = internship.type, modifier = Modifier,
                    color = Color.White.copy(alpha = 0.4f)
                )
            }
        }
    }
}

@Composable
@Preview
fun InternshipScreenPreview() {
    CrazyStudentTheme {
        InternshipScreen()
    }
}