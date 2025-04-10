package ru.kingofraccoons.crazystudent.presentation.screens.main.schedule.plan

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import org.koin.compose.koinInject
import ru.kingofraccoons.crazystudent.NavigationFun
import ru.kingofraccoons.crazystudent.R
import ru.kingofraccoons.crazystudent.data.util.DataTime
import ru.kingofraccoons.crazystudent.data.util.Duration
import ru.kingofraccoons.crazystudent.data.util.ListItemData
import ru.kingofraccoons.crazystudent.data.util.calculateDifference
import ru.kingofraccoons.crazystudent.domain.entity.response.BreakResponse
import ru.kingofraccoons.crazystudent.domain.entity.response.EventResponse
import ru.kingofraccoons.crazystudent.domain.entity.response.LessonResponse
import ru.kingofraccoons.crazystudent.domain.entity.response.PlanResponse
import ru.kingofraccoons.crazystudent.domain.entity.response.RoadTimeResponse
import ru.kingofraccoons.crazystudent.domain.entity.response.RouteResponse
import ru.kingofraccoons.crazystudent.domain.entity.response.TimetableUnitResponse
import ru.kingofraccoons.crazystudent.domain.util.Resource
import ru.kingofraccoons.crazystudent.presentation.elements.BodyText
import ru.kingofraccoons.crazystudent.presentation.elements.Caption2Text
import ru.kingofraccoons.crazystudent.presentation.elements.LargeTitleText
import ru.kingofraccoons.crazystudent.presentation.elements.SubheadText
import ru.kingofraccoons.crazystudent.presentation.elements.Title2Text
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

@Composable
fun PlanScreen(popOnBackStack: NavigationFun, viewModel: PlanViewModel = koinInject()) {
    val plan = viewModel.planFlow.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadPlan()
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
                    LargeTitleText("План", Modifier)
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
        },
        containerColor = Color.Black
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp)
        ) {
            PlanResourceContent(
                plan.value,
                viewModel::loadPlan,
                viewModel::transformRouteToListItems
            )
        }
    }
}

@Composable
fun PlanResourceContent(
    plan: Resource<PlanResponse>,
    reload: () -> Unit,
    transformRouteToListItems: (RouteResponse) -> List<ListItemData>,
) {
    when (plan) {
        is Resource.Error -> PlanError(plan, reload)
        is Resource.Loading -> LoadingContent()
        is Resource.Success -> PlanContent(plan.data, transformRouteToListItems)
    }
}

@Composable
fun PlanError(error: Resource.Error<PlanResponse>, reload: () -> Unit) {
    Column {
        BodyText(error.message)
        Button(
            reload,
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
                    .padding(4.dp), Arrangement.SpaceBetween
            ) {
                Title2Text("Войти в аккаунт", Modifier, color = Color.Black)
                Image(
                    painterResource(R.drawable.arrow_right),
                    null,
                    Modifier.defaultMinSize(24.dp, 24.dp)
                )
            }
        }
    }
}

@Composable
fun LoadingContent() {
    Row(Modifier.fillMaxWidth(), Arrangement.Center) {
        LinearProgressIndicator(
            Modifier
                .fillMaxWidth(0.33f)
                .aspectRatio(1f)
        )
    }
}

@Composable
fun PlanContent(
    plan: PlanResponse,
    transformRouteToListItems: (RouteResponse) -> List<ListItemData>,
) {
    LazyColumn(Modifier.fillMaxSize()) {
        itemsIndexed(plan.units) { index, it ->
            when (it) {
                is LessonResponse, is EventResponse -> TimetableUnitContent(it)
                is RoadTimeResponse -> {
                    println("it is RoadTimeResponse: ${it.timeStart} ${it.timeEnd}")
                    if (index != 0 && index != plan.units.lastIndex)
                        RoadTimeContentBetweenTU(
                            it,
                            plan.units[index - 1],
                            plan.units[index + 1],
                            transformRouteToListItems
                        )
                    else
                        RoadTimeContent(it, transformRouteToListItems)
                }

                is BreakResponse -> {}
            }
        }
    }
}

@Composable
fun RoadTimeContent(
    roadTime: RoadTimeResponse,
    transformRouteToListItems: (RouteResponse) -> List<ListItemData>,
) {
    TransferTimePicker(
        DataTime.parse(roadTime.timeStart),
        DataTime.parse(roadTime.timeEnd),
        roadTime,
        transformRouteToListItems
    )
}

@Composable
fun RoadTimeContentBetweenTU(
    roadTime: RoadTimeResponse,
    previousTU: TimetableUnitResponse,
    nextTU: TimetableUnitResponse,
    transformRouteToListItems: (RouteResponse) -> List<ListItemData>,
) {
    TransferTimePickerBetweenTU(
        DataTime.parse(previousTU.timeEnd),
        DataTime.parse(nextTU.timeStart),
        roadTime,
        transformRouteToListItems
    )
}

@Composable
fun TimetableUnitContent(timetableUnitResponse: TimetableUnitResponse) {
    Column(Modifier.fillMaxWidth()) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 18.dp),
            Arrangement.spacedBy(4.dp),
            Alignment.CenterVertically
        ) {
            SubheadText(DataTime.parse(timetableUnitResponse.timeStart).getTime(), Modifier)
            Image(painterResource(R.drawable.line_2), null, Modifier.weight(1f))
            SubheadText(DataTime.parse(timetableUnitResponse.timeEnd).getTime(), Modifier)
        }

        Card(
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
                when (timetableUnitResponse) {
                    is LessonResponse -> LessonContent(timetableUnitResponse)
                    is EventResponse -> EventContent(timetableUnitResponse)
                }
            }
        }
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

@Composable
fun TransferTimePickerBetweenTU(
    previousEventEnd: DataTime,
    nextEventStart: DataTime,
    roadTime: RoadTimeResponse,
    transformRouteToListItems: (RouteResponse) -> List<ListItemData>,
) {
    // Вычисляем общее окно между мероприятиями
    val totalWindow: Duration = calculateDifference(previousEventEnd, nextEventStart)
    val route = roadTime.route.first()
    val data = transformRouteToListItems(route)
    val travelTime =
        calculateDifference(
            DataTime.parse(roadTime.timeStart),
            DataTime.parse(roadTime.timeStart).addSeconds(route.totalDuration)
        )
    // Вычисляем доступный интервал для выбора отправления
    val availableDuration = totalWindow.minus(travelTime)

    // Состояние для хранения ширины контейнера (окна переезда) в пикселях
    var containerHeightPx = remember { mutableFloatStateOf(0f) }
    var cardHeightPx = remember { mutableFloatStateOf(0f) }
    // Состояние для текущего горизонтального смещения карточки
    var offsetY = remember { mutableFloatStateOf(0f) }

    // Определяем draggable-состояние для обработки горизонтального перемещения
    val draggableState = remember {
        androidx.compose.foundation.gestures.DraggableState { delta ->
            // Обновляем смещение в пределах контейнера
            offsetY.floatValue =
                (offsetY.floatValue + delta).coerceIn(
                    0f,
                    containerHeightPx.floatValue - cardHeightPx.floatValue
                )
        }
    }

    // Вычисляем коэффициент смещения (0f..1f)
    val fraction =
        if (containerHeightPx.floatValue > 0f) offsetY.floatValue / containerHeightPx.floatValue else 0f
    // Вычисляем количество минут, на которое увеличивается отправление от предыдущего события
    val addedMinutes = (fraction * availableDuration.minutes).roundToInt()

    // Вычисляем время отправления и прибытия
    val departureTime = previousEventEnd.addMinutes(addedMinutes)
    val arrivalTime = departureTime.addMinutes(travelTime.minutes.toInt())

    Column(modifier = Modifier.padding(16.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(max(240f, cardHeightPx.floatValue).dp)
                .border(1.dp, Color.Gray)
                .onSizeChanged { containerHeightPx.floatValue = it.height.toFloat() },
            contentAlignment = Alignment.TopCenter
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset { IntOffset(0, offsetY.floatValue.roundToInt()) }
                    .background(Color.Blue, shape = RoundedCornerShape(8.dp))
                    .draggable(
                        state = draggableState,
                        orientation = Orientation.Vertical
                    )
                    .onSizeChanged { cardHeightPx.floatValue = it.toSize().height / 1.dp.value },
                colors = CardDefaults.cardColors(
                    containerColor = Color.Black,
                    contentColor = Color.Black
                )
            ) {
                Box(Modifier.padding(16.dp)) {
                    InterfaceLayout(data)
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Вывод информации о выбранном времени отправления и прибытия
        BodyText(text = "Отправление: ${departureTime.getTime()}")
        BodyText(text = "Прибытие: ${arrivalTime.getTime()}")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(text = "Доступное окно для выбора отправления: $availableDuration")
    }
}

@Composable
fun TransferTimePicker(
    previousEventEnd: DataTime,
    nextEventStart: DataTime,
    roadTime: RoadTimeResponse,
    transformRouteToListItems: (RouteResponse) -> List<ListItemData>,
) {
    // Вычисляем общее окно между мероприятиями
    val totalWindow: Duration = calculateDifference(previousEventEnd, nextEventStart)
    val route = roadTime.route.first()
    val data = transformRouteToListItems(route)
    val travelTime =
        calculateDifference(
            DataTime.parse(roadTime.timeStart),
            DataTime.parse(roadTime.timeStart).addSeconds(route.totalDuration)
        )
    // Вычисляем доступный интервал для выбора отправления
    val availableDuration = totalWindow.minus(travelTime)

    // Вычисляем время отправления и прибытия
    val departureTime = previousEventEnd
    val arrivalTime = departureTime.addMinutes(travelTime.minutes.toInt())

    Column(modifier = Modifier.padding(16.dp)) {
        // Контейнер, представляющий окно между мероприятиями
        // Перемещаемая карточка, задающая время отправления
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Blue, shape = RoundedCornerShape(8.dp)),
            colors = CardDefaults.cardColors(
                containerColor = Color.Black,
                contentColor = Color.Black
            )
        ) {
            Box(Modifier.padding(16.dp)) {
                InterfaceLayout(data)
            }
//            BodyText(text = "Переезд", color = Color.White)
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Вывод информации о выбранном времени отправления и прибытия
        BodyText(text = "Отправление: ${departureTime.getTime()}")
        BodyText(text = "Прибытие: ${arrivalTime.getTime()}")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText(text = "Доступное окно для выбора отправления: $availableDuration")
    }
}

@Composable
fun InterfaceLayout(data: List<ListItemData> = listOf()) {
    if (data.isEmpty()) return

    Column {
        Box {
            // Рисуем зеленую вертикальную линию
            // Колонка с элементами и промежутками между ними
            Column {
                Header(data.first())
                data.drop(1).dropLast(1).forEach {
                    ExpandableListItem(
                        it.label,
                        it.content
                    )
                }
            }
        }
        FinalRow(data.last())
    }
}

const val lineX = 3.5f

@Composable
fun Header(listItemData: ListItemData) {
    var isExpanded = remember { mutableStateOf(false) }

    Box {
        Canvas(modifier = Modifier.matchParentSize()) {
            drawLine(
                color = Color.Green,
                start = Offset(lineX, lineX * 2),
                end = Offset(lineX, size.height),
                strokeWidth = 2.dp.toPx()
            )
        }

        Column {
            Row(
                Modifier
                    .fillMaxWidth()
                    .clickable { isExpanded.value = !(isExpanded.value) },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .offset(x = (-lineX * 3).dp)
                        .background(Color.Green, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painterResource(R.drawable.ic_start),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
                BodyText(text = listItemData.label, Modifier.weight(1f))

                Icon(
                    imageVector = if (isExpanded.value) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = Color.Green,
                    modifier = Modifier.size(16.dp)
                )
            }

            AnimatedVisibility(isExpanded.value) {
                Text(
                    text = listItemData.content,
                    color = Color.Gray,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(start = 24.dp, top = 4.dp, bottom = 4.dp)
                )
            }
        }
    }
}

@Composable
fun ExpandableListItem(label: String, content: String) {
    // Состояние для управления сворачиванием/разворачиванием конкретного элемента
    var isExpanded = remember { mutableStateOf(false) }

    Box {
        Canvas(modifier = Modifier.matchParentSize()) {
            drawLine(
                color = Color.Green,
                start = Offset(lineX, 0f),
                end = Offset(lineX, size.height + lineX * 3),
                strokeWidth = 2.dp.toPx()
            )
        }
        Column(Modifier.padding(vertical = 8.dp)) {
            // Основная строка (заголовок элемента)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        isExpanded.value = !(isExpanded.value)
                    } // Делаем строку кликабельной
                    .padding(vertical = 4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .offset(x = (-lineX * 1.15).dp)
                        .background(Color.Green, shape = CircleShape)
                )
                BodyText(
                    text = label,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                )
                // Иконка для сворачивания/разворачивания
                Icon(
                    imageVector = if (isExpanded.value) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = Color.Green,
                    modifier = Modifier.size(16.dp)
                )
            }
            // Дополнительное содержимое, отображается только если элемент развернут
            AnimatedVisibility(isExpanded.value) {
                Text(
                    text = content,
                    color = Color.Gray,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(start = 24.dp, top = 4.dp, bottom = 4.dp)
                )
            }
        }
    }
}

@Composable
fun FinalRow(listItemData: ListItemData) {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .offset(x = (-lineX * 3).dp)
                    .background(Color.Green, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.Check,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
            BodyText(text = listItemData.label)
        }

        Text(
            text = listItemData.content,
            color = Color.Gray,
            fontSize = 14.sp,
            modifier = Modifier
                .padding(start = 24.dp, top = 4.dp, bottom = 4.dp)
        )
    }
}