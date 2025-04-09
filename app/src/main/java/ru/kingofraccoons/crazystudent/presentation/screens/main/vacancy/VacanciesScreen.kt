package ru.kingofraccoons.crazystudent.presentation.screens.main.vacancy

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.kamel.core.config.KamelConfig
import io.kamel.core.config.httpFetcher
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import io.kamel.image.config.LocalKamelConfig
import io.ktor.client.engine.cio.CIO
import org.koin.compose.koinInject
import ru.kingofraccoons.crazystudent.presentation.MainViewModel
import ru.kingofraccoons.crazystudent.presentation.elements.SubheadText
import ru.kingofraccoons.crazystudent.presentation.elements.Title1Text
import ru.kingofraccoons.crazystudent.presentation.elements.Title2Text

@Composable
fun VacanciesScreen(mainViewModel: MainViewModel = koinInject()) {

    Scaffold(Modifier.fillMaxSize().systemBarsPadding(), topBar = {
        Column(Modifier.fillMaxWidth().background(Color.Black).padding(horizontal = 16.dp)) {
            Title1Text("Вакансии", Modifier.align(Alignment.CenterHorizontally))
        }
    }, contentColor = Color.Black, containerColor = Color.Black) {
        LazyColumn(
            Modifier.fillMaxSize().padding(it).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
//            items(vacancies.value.data.orEmpty()) {
//                Card(
//                    Modifier.fillMaxWidth(),
//                    RoundedCornerShape(24.dp),
//                    CardDefaults.cardColors(Color(0xFF2B2C34), Color(0xFF2B2C34))
//                ) {
//                    Row(Modifier.fillMaxWidth().padding(16.dp), Arrangement.spacedBy(12.dp)) {
//                        CompositionLocalProvider(LocalKamelConfig provides KamelConfig {
//                            this.httpFetcher(CIO.create { }) {}
//                        }) {
//                            KamelImage(
//                                asyncPainterResource(it.organization.logo.url).also {
//                                    println("image: $it")
//                                },
//                                null,
//                                Modifier.weight(1f).aspectRatio(1f).clip(
//                                    RoundedCornerShape(16.dp)
//                                )
//                            )
//                        }
//                        Column(Modifier.weight(4f), Arrangement.Center) {
//                            Title2Text(it.name, Modifier)
//                            SubheadText(
//                                it.schedule.joinToString { it.name },
//                                Modifier.padding(top = 2.dp)
//                            )
//                        }
//                    }
//                }
//            }
        }
    }
}