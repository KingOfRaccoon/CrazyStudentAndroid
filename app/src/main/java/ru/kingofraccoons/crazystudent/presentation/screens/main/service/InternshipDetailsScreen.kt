package ru.kingofraccoons.crazystudent.presentation.screens.main.service

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.kingofraccoons.crazystudent.presentation.elements.BodyText
import ru.kingofraccoons.crazystudent.presentation.elements.Caption1Text
import ru.kingofraccoons.crazystudent.presentation.elements.Title2Text
import ru.kingofraccoons.crazystudent.ui.theme.CrazyStudentTheme


@Composable
fun InternshipDetailsScreen() {
    val internship = data.last()
    val uriHandler = LocalUriHandler.current


    Column(
        Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {


        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier.size(70.dp),
                colors = CardDefaults.cardColors(Color.Transparent),
                shape = RoundedCornerShape(16.dp),
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(internship.icon),
                    contentDescription = "icon",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(0.dp)
                )
            }

            Spacer(Modifier.width(16.dp))
            Column {
                Title2Text(
                    text = internship.name, modifier = Modifier
                )
                Caption1Text(
                    text = internship.company, modifier = Modifier,
                    color = Color.White.copy(alpha = 0.4f)
                )
            }

        }

        Spacer(Modifier.height(16.dp))
        LazyRow(
            userScrollEnabled = true,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(internship.info) { info ->
                Card(
                    colors = CardDefaults.cardColors(Color(0xFF2B2C34)),
                    shape = RoundedCornerShape(24.dp),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(12.dp)
                    ) {
                        BodyText(
                            info, modifier = Modifier, color = Color.White
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(16.dp))


        LazyColumn(
            modifier = Modifier.weight(1f),
        ) {
            items(internship.description.split("\n")) { text ->
                BodyText(text, modifier = Modifier.fillMaxWidth())
            }
        }


        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 12.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFF70DDFF)),
            onClick = { uriHandler.openUri(internship.redirect) }
        ) {
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Title2Text("Откликнуться", modifier = Modifier, color = Color.Black)
                Icon(Icons.Default.ArrowForward, "", tint = Color.Black)
            }
        }
    }
}


@Composable
@Preview
fun InternshipDetailsScreenPreview() {
    CrazyStudentTheme {
        Box(modifier = Modifier.background(Color.Black)) {
            InternshipDetailsScreen()
        }
    }
}