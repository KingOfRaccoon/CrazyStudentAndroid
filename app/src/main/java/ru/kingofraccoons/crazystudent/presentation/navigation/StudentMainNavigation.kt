package ru.kingofraccoons.crazystudent.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import ru.kingofraccoons.crazystudent.R
import ru.kingofraccoons.crazystudent.presentation.routes.GroupRoutes
import ru.kingofraccoons.crazystudent.presentation.routes.ScreenRoutes
import ru.kingofraccoons.crazystudent.presentation.screens.main.profile.student.AdditionalEducationScreen
import ru.kingofraccoons.crazystudent.presentation.screens.main.profile.student.PreferencesScreen
import ru.kingofraccoons.crazystudent.presentation.screens.main.profile.student.RecordBookScreen
import ru.kingofraccoons.crazystudent.presentation.screens.main.profile.student.StudentProfileScreen
import ru.kingofraccoons.crazystudent.presentation.screens.main.profile.student.StudyPlaneScreen
import ru.kingofraccoons.crazystudent.presentation.screens.main.schedule.EventsScreen
import ru.skittens.prostoleti.presentation.screens.main.schedule.ScheduleScreen
import ru.kingofraccoons.crazystudent.presentation.screens.main.service.InternshipScreen
import ru.kingofraccoons.crazystudent.presentation.screens.main.service.SelectHousingScreen
import ru.kingofraccoons.crazystudent.presentation.screens.main.service.ServicesScreen
import ru.kingofraccoons.crazystudent.presentation.screens.main.vacancy.VacanciesScreen

fun NavGraphBuilder.StudentMainNavigation(navigator: NavHostController) {
    navigation(ScreenRoutes.Student.Schedule.name, GroupRoutes.ScheduleStudent.name) {
        composable(ScreenRoutes.Student.Schedule.name) {
            ScheduleScreen(
                { navigator.navigate(ScreenRoutes.Student.Events.name) },
                { navigator.navigate(GroupRoutes.ServicesStudent.name) },
                { navigator.navigate(GroupRoutes.ProfileStudent.name) })
        }

        composable(ScreenRoutes.Student.Events.name) {
            EventsScreen()
        }
    }

    navigation(ScreenRoutes.Student.Profile.name, GroupRoutes.ProfileStudent.name) {
        composable(ScreenRoutes.Student.Profile.name) {
            StudentProfileScreen(
                { navigator.navigate(ScreenRoutes.Student.StudyPlane.name) },
                { navigator.navigate(ScreenRoutes.Student.AdditionalEducation.name) },
                { navigator.navigate(ScreenRoutes.Student.RecordBook.name) },
                { navigator.navigate(ScreenRoutes.Student.Preferences.name) }
            )
        }

        composable(ScreenRoutes.Student.StudyPlane.name) {
            StudyPlaneScreen()
        }

        composable(ScreenRoutes.Student.AdditionalEducation.name) {
            AdditionalEducationScreen()
        }

        composable(ScreenRoutes.Student.RecordBook.name) {
            RecordBookScreen()
        }

        composable(ScreenRoutes.Student.Preferences.name) {
            PreferencesScreen()
        }
    }

    navigation(ScreenRoutes.Student.Services.name, GroupRoutes.ServicesStudent.name) {
        composable(ScreenRoutes.Student.Services.name) {
            ServicesScreen(
                { navigator.navigate(ScreenRoutes.Student.Vacancies.name) },
                { navigator.navigate(ScreenRoutes.Student.Internship.name) },
                { navigator.navigate(ScreenRoutes.Student.SelectHousing.name) },
            )
        }

        composable(ScreenRoutes.Student.Internship.name) {
            InternshipScreen()
        }

        composable(ScreenRoutes.Student.Vacancies.name){
            VacanciesScreen()
        }

        composable(ScreenRoutes.Student.SelectHousing.name) {
            SelectHousingScreen()
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavGraphBuilder.MainNavigation(navigator: NavHostController) {
    val currentEntry = navigator.currentDestination?.route.orEmpty()

    Scaffold(
        contentColor = Color.Black,
        containerColor = Color.Black,
        modifier = Modifier.fillMaxSize().systemBarsPadding(),
        bottomBar = {
            NavigationBar(containerColor = Color.Black, contentColor = Color.Black) {
                NavigationBarItem(
                    currentEntry == GroupRoutes.ScheduleStudent.name,
                    { navigator.navigate(GroupRoutes.ScheduleStudent.name) },
                    { Image(painterResource(R.drawable.calendar), null) }
                )
                NavigationBarItem(
                    currentEntry == GroupRoutes.ServicesStudent.name,
                    { navigator.navigate(GroupRoutes.ServicesStudent.name) },
                    { Image(painterResource(R.drawable.category), null) }
                )
                NavigationBarItem(
                    currentEntry == GroupRoutes.ProfileStudent.name,
                    { navigator.navigate(GroupRoutes.ProfileStudent.name) },
                    { Image(painterResource(R.drawable.user_2), null) }
                )
            }
        }
    ) {
        NavHost(navigator, GroupRoutes.ScheduleStudent.name) {
            StudentMainNavigation(navigator)
        }
    }
}