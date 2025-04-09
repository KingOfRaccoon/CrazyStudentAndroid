package ru.kingofraccoons.crazystudent

import android.app.Application
import org.koin.core.context.startKoin
import org.koin.dsl.module
import ru.kingofraccoons.crazystudent.data.repository.TimetableRepositoryImpl
import ru.kingofraccoons.crazystudent.data.repository.UserRepositoryImpl
import ru.kingofraccoons.crazystudent.data.source.TimetableService
import ru.kingofraccoons.crazystudent.data.source.UserService
import ru.kingofraccoons.crazystudent.data.util.Postman

class CrazyStudentApplication: Application() {
    val module = module {
        single { Postman() }

        single { TimetableService(get()) }
        single { UserService(get()) }

        single { TimetableRepositoryImpl(get()) }
        single { UserRepositoryImpl(get()) }
    }
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(module)
        }
    }
}