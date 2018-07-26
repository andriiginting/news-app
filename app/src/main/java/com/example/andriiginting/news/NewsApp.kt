package com.example.andriiginting.news

import android.app.Application
import com.example.andriiginting.news.utils.ConnectivityCheck.Companion.connectivityCheck
import com.example.andriiginting.news.utils.ConnectivityListener

class NewsApp: Application() {

    override fun onCreate() {
        super.onCreate()

        appInstance = this
    }

    companion object {

        @get:Synchronized
        lateinit var appInstance: NewsApp

        fun connectivityListener(listener: ConnectivityListener){
            connectivityCheck = listener
        }

    }
}