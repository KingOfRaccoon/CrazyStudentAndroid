package ru.kingofraccoons.crazystudent.presentation.screens.start.registration

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.russhwolf.settings.Settings
import com.russhwolf.settings.set
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.compose.koinInject
import ru.kingofraccoons.crazystudent.NavigationFun
import ru.kingofraccoons.crazystudent.R
import ru.kingofraccoons.crazystudent.presentation.elements.HeadlineText
import ru.kingofraccoons.crazystudent.presentation.elements.Title2Text
import ru.kingofraccoons.crazystudent.presentation.screens.start.FilterDropdownFieldWithInlineSearch
import ru.kingofraccoons.crazystudent.presentation.screens.start.ItemAvatar
import ru.kingofraccoons.crazystudent.presentation.screens.start.getImages
import kotlin.reflect.KFunction1

@Composable
fun MutableStateFlow<String>.getStateAndSetter(): Pair<String, KFunction1<String, Unit>> {
    return (this.collectAsState().value to ::value::set)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RegistrationScreen(
    navigateToAuthentication: NavigationFun,
    navigateToMainStudent: NavigationFun,
    navigateToMainEnrollee: NavigationFun,
    viewModel: RegistrationViewModel = koinInject(),
) {
    val settings = Settings()
    val (login, setLogin) = viewModel.login.getStateAndSetter()
    val (password, setPassword) = viewModel.password.getStateAndSetter()
    val (name, setName) = viewModel.firstName.getStateAndSetter()
    val (surname, setSurname) = viewModel.lastName.getStateAndSetter()
    val (address, setAddress) = viewModel.address.getStateAndSetter()
    val (filterName, setFilterName) = viewModel.filterName.getStateAndSetter()
    val filter by viewModel.filter.collectAsState()
    val filters by viewModel.filters.collectAsState(listOf())
    var image by remember { mutableIntStateOf(getImages()[settings.getInt("image", 0)]) }
    val user by viewModel.user.collectAsState()

    LaunchedEffect(user) {
        if (user.isSuccess())
            navigateToMainStudent()
    }

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
                        login,
                        setLogin,
                        Modifier
                            .fillMaxWidth()
                            .background(
                                color = Color(0xFF2B2C34),
                                shape = RoundedCornerShape(size = 30.dp)
                            )
                            .clip(RoundedCornerShape(size = 30.dp)),
                        placeholder = { HeadlineText("Логин", Modifier, Color(0x99FFFFFF)) },
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
                        password,
                        setPassword,
                        Modifier
                            .fillMaxWidth()
                            .background(
                                color = Color(0xFF2B2C34),
                                shape = RoundedCornerShape(size = 30.dp)
                            )
                            .clip(RoundedCornerShape(size = 30.dp)),
                        placeholder = { HeadlineText("Пароль", Modifier, Color(0x99FFFFFF)) },
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color(0xFF2B2C34),
                            focusedContainerColor = Color(0xFF2B2C34),
                            focusedIndicatorColor = Color(0xFF2B2C34),
                            unfocusedIndicatorColor = Color(0xFF2B2C34),
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White
                        ),
                        visualTransformation = PasswordVisualTransformation()
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
                    FilterDropdownFieldWithInlineSearch(
                        filterName,
                        setFilterName,
                        viewModel::updateFilter,
                        filters,
                        Modifier
                            .fillMaxWidth()
                            .background(
                                color = Color(0xFF2B2C34),
                                shape = RoundedCornerShape(size = 30.dp)
                            )
                            .clip(RoundedCornerShape(size = 30.dp))
                    )
                }

                item {
                    Button(
                        viewModel::registrationUser,
                        Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
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