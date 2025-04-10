package ru.kingofraccoons.crazystudent.presentation.screens.main.schedule.addevent

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.koin.compose.koinInject
import ru.kingofraccoons.crazystudent.R
import ru.kingofraccoons.crazystudent.presentation.elements.BodyText
import ru.kingofraccoons.crazystudent.presentation.elements.HeadlineText
import ru.kingofraccoons.crazystudent.presentation.elements.LargeTitleText
import ru.kingofraccoons.crazystudent.presentation.elements.Title2Text
import ru.kingofraccoons.crazystudent.presentation.screens.start.registration.getStateAndSetter
import ru.kingofraccoons.crazystudent.data.util.DataTime
import java.util.Calendar

@Composable
fun AddEventsScreen(popToBackStack: () -> Unit, viewModel: AddEventViewModel = koinInject()) {
    val (name, setName) = viewModel.name.getStateAndSetter()
    val (description, setDescription) = viewModel.description.getStateAndSetter()
    val (timeStart, setTimeStart) = viewModel.timeStart.getStateAndSetter()
    val (timeEnd, setTimeEnd) = viewModel.timeEnd.getStateAndSetter()
    val (address, setAddress) = viewModel.address.getStateAndSetter()
    val addEventStatus = viewModel.addEventStatus.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(addEventStatus.value) {
        if (addEventStatus.value.isSuccess()) {
            popToBackStack()
            Toast.makeText(context, "Мероприятие успешно добавлено", Toast.LENGTH_SHORT).show()
        } else if (addEventStatus.value.isError()) {
            Toast.makeText(context, addEventStatus.value.message, Toast.LENGTH_LONG).show()
        }
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
                LargeTitleText("Добавить мероприятие", Modifier)
            }
        },
        containerColor = Color.Black,
    ) {
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 16.dp, vertical = 4.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            item {
                TextField(
                    name,
                    setName,
                    Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color(0xFF2B2C34),
                            shape = RoundedCornerShape(size = 30.dp)
                        )
                        .clip(RoundedCornerShape(size = 30.dp)),
                    placeholder = { HeadlineText("Название", Modifier, Color(0x99FFFFFF)) },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color(0xFF2B2C34),
                        focusedContainerColor = Color(0xFF2B2C34),
                        focusedIndicatorColor = Color(0xFF2B2C34),
                        unfocusedIndicatorColor = Color(0xFF2B2C34),
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    )
                )
            }

            item {
                TextField(
                    description,
                    setDescription,
                    Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color(0xFF2B2C34),
                            shape = RoundedCornerShape(size = 30.dp)
                        )
                        .clip(RoundedCornerShape(size = 30.dp)),
                    placeholder = { HeadlineText("Описание", Modifier, Color(0x99FFFFFF)) },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color(0xFF2B2C34),
                        focusedContainerColor = Color(0xFF2B2C34),
                        focusedIndicatorColor = Color(0xFF2B2C34),
                        unfocusedIndicatorColor = Color(0xFF2B2C34),
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    )
                )
            }

            item {
                Row(Modifier.fillMaxWidth()) {
                    DateTimePickerButton(
                        "Выберите время начала",
                        Modifier.weight(1f),
                        timeStart,
                        setTimeStart
                    )
                    Spacer(Modifier.weight(0.5f))
                    DateTimePickerButton(
                        "Выберите время конца",
                        Modifier.weight(1f),
                        timeEnd,
                        setTimeEnd
                    )
                }
            }

            item {
                TextField(
                    address,
                    setAddress,
                    Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color(0xFF2B2C34),
                            shape = RoundedCornerShape(size = 30.dp)
                        )
                        .clip(RoundedCornerShape(size = 30.dp)),
                    placeholder = { HeadlineText("Адрес", Modifier, Color(0x99FFFFFF)) },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color(0xFF2B2C34),
                        focusedContainerColor = Color(0xFF2B2C34),
                        focusedIndicatorColor = Color(0xFF2B2C34),
                        unfocusedIndicatorColor = Color(0xFF2B2C34),
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    )
                )
            }

            item {
                Button(
                    viewModel::addEvent,
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
                        Title2Text("Добавить", Modifier, color = Color.Black)
                        Image(
                            painterResource(R.drawable.arrow_right),
                            null,
                            Modifier.defaultMinSize(24.dp, 24.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DateTimePickerButton(
    defaultText: String,
    modifier: Modifier = Modifier,
    selectedDataTime: DataTime?,
    setSelectedDataTime: (DataTime) -> Unit,
) {
    // Состояние для хранения выбранной даты и времени
    val context = LocalContext.current

    Button(onClick = {
        // Создаем календарь для текущей даты и времени
        val calendar = Calendar.getInstance()

        // Создаем и показываем диалог выбора даты
        val datePickerDialog = DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)

                // После выбора даты показываем диалог выбора времени
                val timePickerDialog = TimePickerDialog(
                    context,
                    { it, hourOfDay, minute ->
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                        calendar.set(Calendar.MINUTE, minute)

                        setSelectedDataTime(DataTime(calendar))
                    },
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    true // Используем 24-часовой формат
                )
                timePickerDialog.show()
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }, modifier) {
        BodyText(
            if (selectedDataTime == null) defaultText else selectedDataTime.let { it.getIsoFormat() + "\n" + it.getTime() },
            color = Color.Black,
            minLines = 2
        )
    }
}