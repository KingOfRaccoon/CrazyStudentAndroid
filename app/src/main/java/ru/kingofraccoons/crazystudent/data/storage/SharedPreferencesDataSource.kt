package ru.kingofraccoons.crazystudent.data.storage

import android.content.SharedPreferences

class SharedPreferencesDataSource(private val sharedPref: SharedPreferences) {
    fun saveToken(token: String) {
        sharedPref.edit().putString(KEY_TOKEN, token).apply()
    }

    fun getToken(): String {
        return sharedPref.getString(KEY_TOKEN, null).orEmpty()
    }

    companion object {
        private const val KEY_TOKEN = "user_token"
    }
}