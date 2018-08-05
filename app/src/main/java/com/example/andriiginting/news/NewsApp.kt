package com.example.andriiginting.news

import android.app.Application
import com.example.andriiginting.news.di.Component.ApplicationComponent
import com.example.andriiginting.news.di.Component.DaggerApplicationComponent
import com.example.andriiginting.news.di.module.ApplicationModule
import com.example.andriiginting.news.utils.ConnectivityCheck.Companion.connectivityCheck
import com.example.andriiginting.news.utils.ConnectivityListener

class NewsApp: Application() {

    private var component: ApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()

        appInstance = this
        component = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    companion object {

        @get:Synchronized
        lateinit var appInstance: NewsApp

        fun connectivityListener(listener: ConnectivityListener){
            connectivityCheck = listener
        }

    }
}