package ru.kingofraccoons.crazystudent.presentation.screens.start

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.russhwolf.settings.Settings
import com.russhwolf.settings.set
import ru.kingofraccoons.crazystudent.NavigationFun
import ru.kingofraccoons.crazystudent.R
import ru.kingofraccoons.crazystudent.presentation.elements.HeadlineText
import ru.kingofraccoons.crazystudent.presentation.elements.SubheadText
import ru.kingofraccoons.crazystudent.presentation.elements.Title2Text

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class,
    ExperimentalFoundationApi::class
)
@Composable
fun AuthorizationScreen(
    navigateToRegistration: NavigationFun,
    navigateToMainStudent: NavigationFun,
    navigateToMainEnrollee: NavigationFun,
) {
    val settings = Settings()
    val (name, setName) = remember { mutableStateOf("") }
    val (surname, setSurname) = remember { mutableStateOf("") }
    val (filter, setFilter) = remember { mutableStateOf("") }
    var chips by remember { mutableStateOf(List(10) { "Test $it" }) }
    var image by remember { mutableIntStateOf(getImages()[settings.getInt("image", 0)]) }

    Scaffold(
        Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        containerColor = Color.Black,
        contentColor = Color.Black
    ) {
        Column {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
            ) {
                Image(
                    painterResource(R.drawable.small_logo),
                    "",
                    Modifier.align(Alignment.CenterHorizontally)
                )
            }
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                stickyHeader {

                }
                item {
                    Box(
                        Modifier
                            .fillMaxWidth()
                    ) {
                        Box(
                            Modifier
                                .fillMaxWidth(0.4f)
                                .aspectRatio(1f)
                                .clip(CircleShape)
                                .border(4.dp, Color.White, CircleShape)
                                .padding(8.dp)
                                .align(Alignment.Center)
                        ) {
                            AnimatedContent(image, label = "") {
                                Image(
                                    painterResource(it),
                                    null,
                                    Modifier
                                        .fillMaxSize()
                                        .clip(CircleShape),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                    }
                }
                item {
                    getImages().chunked(5).forEachIndexed { index, drawableRource ->
                        Row(
                            Modifier.fillMaxWidth(),
                            Arrangement.spacedBy(8.dp)
                        ) {
                            drawableRource.forEach {
                                ItemAvatar(it, it == image) {
                                    image = it
                                    settings["image"] = index
                                }
                            }
                        }

                        if (index % 2 == 0) Spacer(Modifier.height(8.dp))
                    }
                }

                item {
                    Title2Text(
                        "Личные данные",
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    )
                }

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
                        placeholder = { HeadlineText("Имя", Modifier, Color(0x99FFFFFF)) },
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
                        surname,
                        setSurname,
                        Modifier
                            .fillMaxWidth()
                            .background(
                                color = Color(0xFF2B2C34),
                                shape = RoundedCornerShape(size = 30.dp)
                            )
                            .clip(RoundedCornerShape(size = 30.dp)),
                        placeholder = { HeadlineText("Фамилия", Modifier, Color(0x99FFFFFF)) },
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
                    FilterDropdownFieldWithInlineSearch(
                        filter,
                        setFilter,
                        listOf("123", "234", "345"),
                        Modifier
                            .fillMaxWidth()
                            .background(
                                color = Color(0xFF2B2C34),
                                shape = RoundedCornerShape(size = 30.dp)
                            )
                            .clip(RoundedCornerShape(size = 30.dp))
                    )
//                    TextField(
//                        filter,
//                        setSurname,
//                        Modifier.fillMaxWidth().padding(top = 4.dp).background(
//                            color = Color(0xFF2B2C34),
//                            shape = RoundedCornerShape(size = 30.dp)
//                        ).clip(RoundedCornerShape(size = 30.dp)),
//                        placeholder = { HeadlineText("Фильтр для расписания", Modifier, Color(0x99FFFFFF)) },
//                        colors = TextFieldDefaults.colors(
//                            unfocusedContainerColor = Color(0xFF2B2C34),
//                            focusedContainerColor = Color(0xFF2B2C34),
//                            focusedIndicatorColor = Color(0xFF2B2C34),
//                            unfocusedIndicatorColor = Color(0xFF2B2C34),
//                            focusedTextColor = Color.White,
//                            unfocusedTextColor = Color.White
//                        )
//                    )
                }

                item {
                    Button(
                        navigateToMainStudent,
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
                            Title2Text("Создать аккаунт", Modifier, color = Color.Black)
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
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterDropdownFieldWithInlineSearch(
    filter: String,                // Текущий выбранный текст/фильтр
    setFilter: (String) -> Unit,   // Функция для обновления фильтра
    items: List<String>,           // Список всех доступных элементов
    modifier: Modifier = Modifier,  // Модификатор для кастомизации
) {
    var expanded by remember { mutableStateOf(false) } // Состояние раскрытия меню
    var searchText by remember { mutableStateOf(filter) } // Текст в поле ввода

    // Фильтрованный список, который обновляется при изменении текста
    val filteredItems by remember(searchText, items) {
        derivedStateOf {
            if (searchText.isEmpty()) {
                items // Показываем все элементы, если поле пустое
            } else {
                items.filter {
                    it.lowercase().contains(searchText.lowercase())
                } // Фильтрация без учета регистра
            }
        }
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }, // Переключение состояния меню
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 4.dp)
            .background(Color(0xFF2B2C34), RoundedCornerShape(30.dp))
            .clip(RoundedCornerShape(30.dp))
    ) {
        // Поле ввода текста
        TextField(
            value = searchText,
            onValueChange = { newText ->
                searchText = newText       // Обновляем текст в поле
                setFilter(newText)         // Обновляем внешний фильтр
                expanded = true            // Открываем меню при вводе
            },
            placeholder = {
                HeadlineText(
                    "Выберите или введите элемент",
                    Modifier.fillMaxWidth(),
                    Color(0x99FFFFFF)
                )
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) // Стрелка раскрытия
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFF2B2C34),
                focusedContainerColor = Color(0xFF2B2C34),
                focusedIndicatorColor = Color(0xFF2B2C34),
                unfocusedIndicatorColor = Color(0xFF2B2C34),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
            modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryEditable, true) // Привязка к меню
        )

        // Выпадающее меню с отфильтрованными элементами
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false } // Закрытие меню при клике вне его
        ) {
            filteredItems.forEach { item ->
                DropdownMenuItem(
                    text = { HeadlineText(item) },
                    onClick = {
                        setFilter(item)    // Устанавливаем выбранный элемент
                        searchText = item  // Обновляем текст в поле
                        expanded = false   // Закрываем меню
                    }
                )
            }
        }
    }
}

@Composable
fun ItemPreference(preference: String, deletePreference: (String) -> Unit) {
    Button(
        { deletePreference(preference) },
        colors = ButtonDefaults.buttonColors(Color(0xFF2B2C34)),
        contentPadding = PaddingValues(12.dp, 8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            SubheadText(preference, Modifier)
            Icon(Icons.Filled.Close, null, Modifier.padding(start = 8.dp), tint = Color.White)
        }
    }
}

@Composable
fun RowScope.ItemAvatar(
    @DrawableRes drawable: Int,
    isCurrent: Boolean,
    updateChoice: () -> Unit,
) {
    Box(
        Modifier
            .weight(1f)
            .aspectRatio(1f)
    ) {
        Image(
            painterResource(drawable),
            null,
            Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(CircleShape)
                .clickable {
                    updateChoice()
                },
            contentScale = ContentScale.Crop
        )

        this@ItemAvatar.AnimatedVisibility(
            isCurrent,
            enter = enterAnimation,
            exit = exitAnimation
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .background(Color.Black.copy(0.5f), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painterResource(R.drawable.check_circle),
                    null,
                    Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    tint = Color(0xFF70DDFF)
                )
            }
        }
    }
}

fun getImages() = listOf(
    R.drawable.avatar_1,
    R.drawable.avatar_2,
    R.drawable.avatar_3,
    R.drawable.avatar_4,
    R.drawable.avatar_5,
    R.drawable.avatar_6,
    R.drawable.avatar_7,
    R.drawable.avatar_8,
    R.drawable.avatar_9,
    R.drawable.avatar_10
)

val enterAnimation = fadeIn()
val exitAnimation = fadeOut()