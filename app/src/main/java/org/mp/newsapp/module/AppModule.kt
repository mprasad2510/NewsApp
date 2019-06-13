package org.mp.newsapp.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import org.mp.newsapp.di.util.scheduler.BaseSchedulerProvider
import org.mp.newsapp.di.util.scheduler.SchedulerProvider
import javax.inject.Singleton


@Module
class AppModule(private val application: Application) {
    @Provides
    @Singleton
    fun provideContext(): Context = application

    @Provides
    @Singleton
    fun provideSchedular(): BaseSchedulerProvider = SchedulerProvider()
}