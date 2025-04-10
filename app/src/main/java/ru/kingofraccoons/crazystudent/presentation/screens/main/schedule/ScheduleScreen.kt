package ru.kingofraccoons.crazystudent.presentation.screens.main.schedule

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.LifecycleResumeEffect
import kotlinx.coroutines.flow.update
import org.koin.compose.koinInject
import ru.kingofraccoons.crazystudent.NavigationFun
import ru.kingofraccoons.crazystudent.R
import ru.kingofraccoons.crazystudent.domain.entity.response.EventResponse
import ru.kingofraccoons.crazystudent.domain.entity.response.LessonResponse
import ru.kingofraccoons.crazystudent.domain.entity.response.TimetableUnitResponse
import ru.kingofraccoons.crazystudent.presentation.elements.BodyText
import ru.kingofraccoons.crazystudent.presentation.elements.Caption1Text
import ru.kingofraccoons.crazystudent.presentation.elements.Caption2Text
import ru.kingofraccoons.crazystudent.presentation.elements.LargeTitleText
import ru.kingofraccoons.crazystudent.presentation.elements.SubheadText
import ru.kingofraccoons.crazystudent.presentation.elements.Title1Text
import ru.kingofraccoons.crazystudent.presentation.elements.Title2Text
import ru.kingofraccoons.crazystudent.presentation.elements.Title3Text
import ru.kingofraccoons.crazystudent.data.util.DataTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleScreen(
    navigateToAddEvent: NavigationFun,
    navigateToPlan: NavigationFun,
    navigateToProfile: NavigationFun,
    viewModel: ScheduleViewModel = koinInject(),
) {
    val selectedItem = viewModel.dateFlow.collectAsState()
    val timetable = viewModel.timetableFlow.collectAsState()
    val today = DataTime.now()
    val dates = List(7) { today.goToNNextDay(it) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    var selectedTU by remember { mutableStateOf<TimetableUnitResponse?>(null) }
    var isSheetOpen by remember { mutableStateOf(false) }

    LifecycleResumeEffect(Unit) {
        viewModel.loadTimetable()
        onPauseOrDispose {  }
    }

    Scaffold(
        Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        topBar = {
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
                Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween, Alignment.CenterVertically) {
                    LargeTitleText("Расписание", Modifier)
                    IconButton(
                        navigateToPlan,
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
        },
        containerColor = Color.Black,
    ) {
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp)
        ) {
            item {
                LazyRow(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    items(dates) { date ->
                        AnimatedContent(
                            selectedItem.value.getIsoFormat() == date.getIsoFormat(),
                        ) {
                            Card(
                                {
                                    viewModel.dateFlow.update { date }
                                    viewModel.loadTimetable(date.getIsoFormat())
                                },
                                shape = RoundedCornerShape(19.dp),
                                colors = CardDefaults.cardColors(
                                    if (it) Color(0xFF70DDFF)
                                    else Color(0xFF2B2C34)
                                )
                            ) {
                                Column(
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(12.dp, 6.dp),
                                    Arrangement.Center,
                                    Alignment.CenterHorizontally
                                ) {
                                    Caption1Text(
                                        date.getShortcutDayOfWeek(),
                                        Modifier.align(Alignment.CenterHorizontally),
                                        if (it) Color.Black
                                        else Color.White
                                    )
                                    Title3Text(
                                        date.dayOfMonth.toString(),
                                        Modifier.align(Alignment.CenterHorizontally),
                                        if (it) Color.Black
                                        else Color.White
                                    )
                                }
                            }
                        }
                    }
                }
            }

            items(timetable.value.data?.getUnits().orEmpty()) {
                Column(Modifier.fillMaxWidth()) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 18.dp),
                        Arrangement.spacedBy(4.dp),
                        Alignment.CenterVertically
                    ) {
                        SubheadText(DataTime.parse(it.timeStart).getTime(), Modifier)
                        Image(painterResource(R.drawable.line_2), null, Modifier.weight(1f))
                        SubheadText(DataTime.parse(it.timeEnd).getTime(), Modifier)
                    }

                    Card(
                        { isSheetOpen = true; selectedTU = it },
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF85FFA0), contentColor = Color(0xFF85FFA0)
                        )
                    ) {
                        Column(
                            Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            TimetableContent(it)
                        }
                    }
                }
            }

            item {
                Spacer(Modifier.height(16.dp))
                Button(
                    navigateToAddEvent,
                    Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(size = 59.dp))
                        .shadow(
                            5.dp,
                            RoundedCornerShape(size = 59.dp),
                            true,
                            Color(0x8CFFFFFF),
                            Color(0x8CFFFFFF)
                        ),
                    colors = ButtonDefaults.buttonColors(Color(0xFF70DDFF))
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        Arrangement.SpaceBetween
                    ) {
                        Title2Text("Добавить мероприятие", Modifier, color = Color.Black)
                        Image(
                            painterResource(R.drawable.arrow_right),
                            null,
                            Modifier.defaultMinSize(24.dp, 24.dp)
                        )
                    }
                }
            }
        }

        if (isSheetOpen && selectedTU != null)
            TimetableUnitBottomSheet(selectedTU!!, sheetState) {
                isSheetOpen = false
            }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimetableUnitBottomSheet(
    timetableUnitResponse: TimetableUnitResponse,
    sheetState: SheetState,
    onClose: () -> Unit,
) {
    ModalBottomSheet(
        onClose,
        sheetState = sheetState,
        containerColor = Color(0xFF1C1C1C),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(24.dp, 0.dp, 24.dp, 16.dp), Arrangement.spacedBy(12.dp)
        ) {
            TimetableContentBottomSheet(timetableUnitResponse, onClose)
        }
    }
}

@Composable
fun TimetableContentBottomSheet(timetableUnitResponse: TimetableUnitResponse, onClose: () -> Unit) {
    when (timetableUnitResponse) {
        is LessonResponse -> LessonContentBottomSheet(timetableUnitResponse, onClose)
        is EventResponse -> EventContentBottomSheet(timetableUnitResponse, onClose)
    }
}

@Composable
fun LessonContentBottomSheet(lessonResponse: LessonResponse, onClose: () -> Unit) {
    Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween, Alignment.CenterVertically) {
        Title1Text(lessonResponse.disc)

        IconButton(onClick = onClose) {
            Icon(
                imageVector = Icons.Default.Close, contentDescription = "Close", tint = Color.White
            )
        }
    }

    BodyText("Тип: ${lessonResponse.typeLesson}")
    BodyText("Корпус: ${lessonResponse.build}")
    BodyText("Аудитория: ${lessonResponse.rooms}")
    BodyText("Группы: ${lessonResponse.groupsText}")

    HorizontalDivider(color = Color.White, thickness = 2.dp)

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.avatar_1),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = lessonResponse.prepsText, fontSize = 16.sp, color = Color.White
        )
    }
}

@Composable
fun EventContentBottomSheet(eventResponse: EventResponse, onClose: () -> Unit) {
    Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween, Alignment.CenterVertically) {
        Title1Text(eventResponse.name)

        IconButton(onClick = onClose) {
            Icon(
                imageVector = Icons.Default.Close, contentDescription = "Close", tint = Color.White
            )
        }
    }

    BodyText("Описание: ${eventResponse.description}")
    BodyText("Место проведения: ${eventResponse.place.name}")
    BodyText("Адрес: ${eventResponse.place.address}")
}

@Composable
fun TimetableContent(timetableUnitResponse: TimetableUnitResponse) {
    when (timetableUnitResponse) {
        is LessonResponse -> LessonContent(timetableUnitResponse)
        is EventResponse -> EventContent(timetableUnitResponse)
    }
}

@Composable
fun LessonContent(lessonResponse: LessonResponse) {
    Row(
        Modifier.fillMaxWidth(), Arrangement.SpaceBetween, Alignment.CenterVertically
    ) {
        Title2Text(lessonResponse.disc, Modifier.weight(1f), Color.Black)
        Caption2Text(lessonResponse.typeLesson, Modifier, Color.Black)
    }

    Row(
        Modifier.fillMaxWidth(), Arrangement.spacedBy(4.dp), Alignment.CenterVertically
    ) {
        Image(painterResource(R.drawable.location), null)
        SubheadText("${lessonResponse.build} - ${lessonResponse.rooms}", Modifier, Color.Black)
    }
}

@Composable
fun EventContent(eventResponse: EventResponse) {
    Row(
        Modifier.fillMaxWidth(), Arrangement.SpaceBetween, Alignment.CenterVertically
    ) {
        Title2Text(eventResponse.name, Modifier.weight(1f), Color.Black)
        Caption2Text(eventResponse.description, Modifier, Color.Black)
    }

    Row(
        Modifier.fillMaxWidth(), Arrangement.spacedBy(4.dp), Alignment.CenterVertically
    ) {
        Image(painterResource(R.drawable.location), null)
        SubheadText(eventResponse.place.address, Modifier, Color.Black)
    }
}