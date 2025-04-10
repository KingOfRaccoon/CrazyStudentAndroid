package ru.kingofraccoons.crazystudent

import android.app.Application
import org.koin.core.context.startKoin
import org.koin.dsl.module
import ru.kingofraccoons.crazystudent.data.network.TimetableService
import ru.kingofraccoons.crazystudent.data.network.UserService
import ru.kingofraccoons.crazystudent.data.repository.TimetableRepositoryImpl
import ru.kingofraccoons.crazystudent.data.repository.UserRepositoryImpl
import ru.kingofraccoons.crazystudent.data.storage.SharedPreferencesDataSource
import ru.kingofraccoons.crazystudent.data.util.Postman
import ru.kingofraccoons.crazystudent.domain.repository.TimetableRepository
import ru.kingofraccoons.crazystudent.domain.repository.UserRepository
import ru.kingofraccoons.crazystudent.domain.usecase.AddEventUseCase
import ru.kingofraccoons.crazystudent.domain.usecase.LoadUseCase
import ru.kingofraccoons.crazystudent.presentation.MainViewModel
import ru.kingofraccoons.crazystudent.presentation.screens.main.profile.student.ProfileViewModel
import ru.kingofraccoons.crazystudent.presentation.screens.main.schedule.ScheduleViewModel
import ru.kingofraccoons.crazystudent.presentation.screens.main.schedule.addevent.AddEventViewModel
import ru.kingofraccoons.crazystudent.presentation.screens.main.schedule.plan.PlanViewModel
import ru.kingofraccoons.crazystudent.presentation.screens.start.authorization.AuthorizationViewModel
import ru.kingofraccoons.crazystudent.presentation.screens.start.registration.RegistrationViewModel
import ru.kingofraccoons.crazystudent.presentation.screens.start.splash.SplashViewModel

class CrazyStudentApplication : Application() {
    val module = module {
        single { Postman() }

        single { TimetableService(get()) }
        single { UserService(get()) }
        single {
            SharedPreferencesDataSource(
                this@CrazyStudentApplication.getSharedPreferences(TOKEN_DATA, MODE_PRIVATE)
            )
        }

        single<TimetableRepository> { TimetableRepositoryImpl(get()) }
        single<UserRepository> { UserRepositoryImpl(get(), get()) }

        single { LoadUseCase(get()) }
        single { AddEventUseCase(get(), get()) }

        single { SplashViewModel(get()) }
        single { RegistrationViewModel(get(), get()) }
        single { MainViewModel(get()) }
        single { ScheduleViewModel(get(), get()) }
        single { AuthorizationViewModel(get()) }
        single { AddEventViewModel(get(), get(), get()) }
        single { PlanViewModel(get(), get()) }
        single { ProfileViewModel(get()) }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(module)
        }
    }

    companion object {
        const val TOKEN_DATA = "tokens"
    }
}