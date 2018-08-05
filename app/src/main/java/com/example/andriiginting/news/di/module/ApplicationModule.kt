package com.example.andriiginting.news.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(application: Application) {

    private var application: Application? = null

    init {
        this.application = application
    }

    @Provides
    fun providesApplication(): Context{
        return this.application!!
    }
}