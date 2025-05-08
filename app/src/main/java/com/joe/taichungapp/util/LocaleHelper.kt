package com.joe.taichungapp.util

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import java.util.Locale

object LocaleHelper {
    private const val PREF_NAME = "language_prefs"
    private const val KEY_LANGUAGE = "selected_language"

    fun updateLocale(context: Context, language: String): Context {
        saveLanguagePreference(context, language)

        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = context.resources.configuration
        config.setLocale(locale)
        return context.createConfigurationContext(config)
    }

    fun getCurrentLanguage(): String = Locale.getDefault().language

    fun applySavedLocale(context: Context): Context {
        val prefs = getPreferences(context)
        val savedLanguage = prefs.getString(KEY_LANGUAGE, getCurrentLanguage()) ?: "en"
        Log.d("test", "savedLanguage :$savedLanguage")
        return updateLocale(context, savedLanguage)
    }

    private fun saveLanguagePreference(context: Context, language: String) {
        val prefs = getPreferences(context)
        prefs.edit().putString(KEY_LANGUAGE, language).apply()
    }

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }
}
