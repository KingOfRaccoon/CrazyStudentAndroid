package ru.skittens.prostoleti.presentation.screens.main.schedule

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.koin.compose.koinInject
import ru.kingofraccoons.crazystudent.NavigationFun
import ru.kingofraccoons.crazystudent.domain.util.Resource
import ru.skittens.prostoleti.data.util.DataTime
import ru.kingofraccoons.crazystudent.presentation.MainViewModel
import ru.kingofraccoons.crazystudent.presentation.elements.Caption1Text
import ru.kingofraccoons.crazystudent.presentation.elements.Caption2Text
import ru.kingofraccoons.crazystudent.presentation.elements.LargeTitleText
import ru.kingofraccoons.crazystudent.presentation.elements.SubheadText
import ru.kingofraccoons.crazystudent.presentation.elements.Title2Text
import ru.kingofraccoons.crazystudent.presentation.elements.Title3Text
import ru.kingofraccoons.crazystudent.R

@Composable
fun ScheduleScreen(
    navigateToEvents: NavigationFun,
    navigateToServices: NavigationFun,
    navigateToProfile: NavigationFun,
    mainViewModel: MainViewModel = koinInject()
) {
    val selectedItem = mainViewModel.currentDay.collectAsState()
    val today = DataTime.now()
    val dates = List(7) { today.goToNNextDay(it) }
//    val timetable = mainViewModel.getTimetable()
//        .collectAsState(Resource.Loading())
    var item by remember { mutableIntStateOf(0) }

    Scaffold(Modifier.fillMaxSize().systemBarsPadding(), topBar = {
        Column(Modifier.fillMaxWidth().background(Color.Black).padding(horizontal = 16.dp)) {
            Image(
                painterResource(R.drawable.small_logo),
                "",
                Modifier.align(Alignment.CenterHorizontally)
            )
            Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                LargeTitleText("Расписание", Modifier)
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
    }, contentColor = Color.Black, containerColor = Color.Black) {
        LazyColumn(Modifier.fillMaxSize().padding(it)) {
            item {
                Row(
                    Modifier.fillMaxWidth().padding(top = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    dates.forEach { date ->
                        AnimatedContent(
                            selectedItem.value == date.getIsoFormat(),
                            Modifier.weight(1f)
                        ) {
                            Card(
                                {
                                    mainViewModel.currentDay.value = date.getIsoFormat()
                                    mainViewModel.loadDate(date.getIsoFormat())
                                },
                                Modifier.weight(1f),
                                shape = RoundedCornerShape(19.dp),
                                colors = CardDefaults.cardColors(
                                    if (it)
                                        Color(0xFF70DDFF)
                                    else
                                        Color(0xFF2B2C34)
                                )
                            ) {
                                Column(
                                    Modifier.fillMaxWidth().padding(12.dp, 6.dp),
                                    Arrangement.Center,
                                    Alignment.CenterHorizontally
                                ) {
                                    Caption1Text(
                                        date.getShortcutDayOfWeek(),
                                        Modifier.align(Alignment.CenterHorizontally),
                                        if (it)
                                            Color.Black
                                        else
                                            Color.White
                                    )
                                    Title3Text(
                                        date.dayOfMonth.toString(),
                                        Modifier.align(Alignment.CenterHorizontally),
                                        if (it)
                                            Color.Black
                                        else
                                            Color.White
                                    )
                                }
                            }
                        }
                    }
                }
            }

//            items(timetable.value?.data.orEmpty()) {
//                Column(Modifier.fillMaxWidth()) {
//                    Row(
//                        Modifier.fillMaxWidth().padding(top = 18.dp),
//                        Arrangement.spacedBy(8.dp),
//                        Alignment.CenterVertically
//                    ) {
//                        SubheadText(it.start_time, Modifier)
//                        Image(painterResource(R.drawable.line_2), null, Modifier.weight(1f))
//                        SubheadText(it.end_time, Modifier)
//                    }
//
//                    Card(
//                        Modifier.fillMaxWidth().padding(top = 8.dp),
//                        shape = RoundedCornerShape(24.dp),
//                        colors = CardDefaults.cardColors(
//                            containerColor = if (it.url == null)
//                                Color(0xFF70DDFF)
//                            else
//                                Color(0xFF85FFA0),
//                            contentColor = if (it.url == null)
//                                Color(0xFF70DDFF)
//                            else
//                                Color(0xFF85FFA0)
//                        )
//                    ) {
//                        Column(Modifier.fillMaxWidth().padding(16.dp)) {
//                            Row(
//                                Modifier.fillMaxWidth(),
//                                Arrangement.SpaceBetween,
//                                Alignment.CenterVertically
//                            ) {
//                                Title2Text(it.name, Modifier.weight(1f), Color.Black)
//                                Caption2Text(it.subjectType, Modifier, Color.Black)
//                            }
//
//                            Row(
//                                Modifier.fillMaxWidth(),
//                                Arrangement.spacedBy(4.dp),
//                                Alignment.CenterVertically
//                            ) {
//                                Image(painterResource(R.drawable.location), null)
//                                SubheadText(it.room, Modifier, Color.Black)
//                            }
//                        }
//                    }
//                }
//            }
        }
    }
}