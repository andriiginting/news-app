package com.example.andriiginting.news.di.Component

import com.example.andriiginting.news.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {}