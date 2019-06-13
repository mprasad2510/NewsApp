package org.mp.newsapp

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import org.mp.newsapp.component.DaggerAppComponent
import org.mp.newsapp.module.AppModule
import timber.log.Timber

class MviApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}