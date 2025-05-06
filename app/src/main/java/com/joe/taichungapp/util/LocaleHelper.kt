
package com.joe.taichungapp.util

import android.content.Context
import java.util.Locale

object LocaleHelper {
    fun updateLocale(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = context.resources.configuration
        config.setLocale(locale)
        return context.createConfigurationContext(config)
    }

    fun getCurrentLanguage(): String = Locale.getDefault().language

    fun toggleLanguage(context: Context): Context {
        val newLang = if (getCurrentLanguage() == "en") "zh" else "en"
        return updateLocale(context, newLang)
    }
}
