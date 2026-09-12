package com.example.ehailing.data.local

import android.content.Context

class ProfileStore(context: Context) {
    private val prefs = context.getSharedPreferences("ehailing_profile", Context.MODE_PRIVATE)

    fun saveProfile(name: String, email: String, phone: String, gender: String) {
        prefs.edit()
            .putString(KEY_NAME, name)
            .putString(KEY_EMAIL, email)
            .putString(KEY_PHONE, phone)
            .putString(KEY_GENDER, gender)
            .putBoolean(KEY_LOGGED_IN, true)
            .apply()
    }

    fun isLoggedIn(): Boolean = prefs.getBoolean(KEY_LOGGED_IN, false)
    fun getName(): String = prefs.getString(KEY_NAME, "") ?: ""
    fun getEmail(): String = prefs.getString(KEY_EMAIL, "") ?: ""
    fun getPhone(): String = prefs.getString(KEY_PHONE, "") ?: ""
    fun getGender(): String = prefs.getString(KEY_GENDER, "") ?: ""
    fun logout() { prefs.edit().clear().apply() }

    companion object {
        private const val KEY_NAME = "name"
        private const val KEY_EMAIL = "email"
        private const val KEY_PHONE = "phone"
        private const val KEY_GENDER = "gender"
        private const val KEY_LOGGED_IN = "logged_in"
    }
}