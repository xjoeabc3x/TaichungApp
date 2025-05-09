package com.joe.taichungapp.util

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Build
import android.util.Log
import java.util.Locale

object LocaleHelper {
    fun setLocale(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)

        val config = Configuration()
        config.setLocale(locale)

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            context.createConfigurationContext(config)
        } else {
            @Suppress("DEPRECATION")
            context.resources.updateConfiguration(config, context.resources.displayMetrics)
            context
        }
    }

    fun getPersistedLanguage(): String {
        return Locale.getDefault().language
    }
}
