package org.mp.newsapp.component


import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import org.mp.newsapp.MviApp
import org.mp.newsapp.module.ActivityBindingModule
import org.mp.newsapp.module.AppModule
import org.mp.newsapp.module.NetworkModule

import javax.inject.Singleton


@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    NetworkModule::class,
    ActivityBindingModule::class
])

interface AppComponent : AndroidInjector<MviApp> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MviApp>() {

        abstract fun appModule(appModule: AppModule): Builder

        override fun seedInstance(instance: MviApp?) {
            appModule(AppModule(instance!!))
        }
    }
}