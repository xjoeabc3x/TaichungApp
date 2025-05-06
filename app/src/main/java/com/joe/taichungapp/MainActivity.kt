package com.joe.taichungapp

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.joe.taichungapp.util.LocaleHelper

class MainActivity : AppCompatActivity() {
    override fun attachBaseContext(newBase: Context) {
        val context = LocaleHelper.updateLocale(newBase, LocaleHelper.getCurrentLanguage())
        super.attachBaseContext(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_main)
    }
}