package com.redenvy.justdoit

import android.app.Application
import timber.log.Timber

class hiltApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG)
        {
            Timber.plant(Timber.DebugTree())
        }
    }
}