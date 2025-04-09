package ru.kingofraccoons.crazystudent.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.kingofraccoons.crazystudent.presentation.routes.GroupRoutes
import ru.kingofraccoons.crazystudent.presentation.routes.ScreenRoutes
import ru.kingofraccoons.crazystudent.presentation.screens.main.admission.AdditionalEducationScreen
import ru.kingofraccoons.crazystudent.presentation.screens.main.admission.AdmissionScreen
import ru.kingofraccoons.crazystudent.presentation.screens.main.admission.InfrastructureScreen
import ru.kingofraccoons.crazystudent.presentation.screens.main.calculator.CalculatorScreen
import ru.kingofraccoons.crazystudent.presentation.screens.main.calculator.ProfessionsScreen
import ru.kingofraccoons.crazystudent.presentation.screens.main.calculator.StudyProgramsScreen
import ru.kingofraccoons.crazystudent.presentation.screens.main.profile.enrollee.EnrolleeProfileScreen
import ru.kingofraccoons.crazystudent.presentation.screens.main.profile.enrollee.SetPointsScreen
import ru.kingofraccoons.crazystudent.presentation.screens.main.profile.student.PreferencesScreen
import ru.kingofraccoons.crazystudent.presentation.screens.main.service.SelectHousingScreen

@Composable
fun NavGraphBuilder.EnrolleeMainNavigation(navigator: NavHostController) {
    navigation(ScreenRoutes.Enrollee.Calculator.name, GroupRoutes.Calculator.name) {
        composable(ScreenRoutes.Enrollee.Calculator.name) {
            CalculatorScreen { navigator.navigate(ScreenRoutes.Enrollee.StudyPrograms.name) }
        }

        composable(ScreenRoutes.Enrollee.StudyPrograms.name) {
            StudyProgramsScreen { navigator.navigate(ScreenRoutes.Enrollee.Preferences.name) }
        }

        composable(ScreenRoutes.Enrollee.Professions.name) {
            ProfessionsScreen()
        }
    }

    navigation(ScreenRoutes.Enrollee.Admission.name, GroupRoutes.Admission.name) {
        composable(ScreenRoutes.Enrollee.Admission.name) {
            AdmissionScreen(
                { navigator.navigate(ScreenRoutes.Enrollee.Infrastructure.name) },
                { navigator.navigate(ScreenRoutes.Enrollee.AdditionalEducation.name) },
                { navigator.navigate(ScreenRoutes.Enrollee.SelectHousing.name) }
            )
        }

        composable(ScreenRoutes.Enrollee.Infrastructure.name) {
            InfrastructureScreen()
        }

        composable(ScreenRoutes.Enrollee.AdditionalEducation.name) {
            AdditionalEducationScreen()
        }

        composable(ScreenRoutes.Enrollee.SelectHousing.name) {
            SelectHousingScreen()
        }
    }

    navigation(ScreenRoutes.Enrollee.Profile.name, GroupRoutes.ProfileEnrollee.name) {
        composable(ScreenRoutes.Enrollee.Profile.name) {
            EnrolleeProfileScreen(
                { navigator.navigate(ScreenRoutes.Enrollee.Preferences.name) },
                { navigator.navigate(ScreenRoutes.Enrollee.SetPoints.name) }
            )
        }

        composable(ScreenRoutes.Enrollee.Preferences.name) {
            PreferencesScreen()
        }

        composable(ScreenRoutes.Enrollee.SetPoints.name) {
            SetPointsScreen()
        }
    }
}