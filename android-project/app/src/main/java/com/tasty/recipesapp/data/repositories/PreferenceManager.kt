package com.tasty.recipesapp.data.repositories

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("user_preferences", Context.MODE_PRIVATE)

    fun saveUserCredentials(name: String, email: String) {
        val editor = sharedPreferences.edit()
        editor.putString(KEY_USER_NAME, name)
        editor.putString(KEY_USER_EMAIL, email)
        editor.apply()
    }

    fun getUserCredentials(): Pair<String?, String?> {
        val name = sharedPreferences.getString(KEY_USER_NAME, null)
        val email = sharedPreferences.getString(KEY_USER_EMAIL, null)
        return Pair(name, email)
    }

    companion object {
        const val KEY_USER_NAME = "user_name"
        const val KEY_USER_EMAIL = "user_email"
    }
}
