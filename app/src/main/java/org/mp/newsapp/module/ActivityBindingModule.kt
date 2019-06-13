package org.mp.newsapp.module


import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.mp.newsapp.di.feature.home.HomeActivity
import org.mp.newsapp.di.feature.home.detail.UserActivity


@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector(modules = [(MviModule::class)])
    abstract fun HomeActivity(): HomeActivity
    @ContributesAndroidInjector(modules = [(MviModule::class)])
    abstract fun UserActivity(): UserActivity
}