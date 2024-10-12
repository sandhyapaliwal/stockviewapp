package com.example.stockviewapp

import android.app.Application
import timber.log.Timber

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (true) { // Enable Timber logging in debug mode
            Timber.plant(Timber.DebugTree()) // Initialize Timber for logging
        }
    }
}
