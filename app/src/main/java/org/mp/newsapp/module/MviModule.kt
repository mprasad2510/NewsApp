package org.mp.newsapp.module


import dagger.Module
import dagger.Provides
import org.mp.newsapp.di.ActivityScope
import org.mp.newsapp.di.feature.home.HomeActionProcessorHolder
import org.mp.newsapp.di.feature.home.HomeViewmodelFactory
import org.mp.newsapp.di.feature.home.detail.UserActionProcessorHolder
import org.mp.newsapp.di.feature.home.detail.UserViewModelFactory


@Module(includes = [DataModule::class])
class MviModule {

    @Provides
    @ActivityScope
    fun provideUserViewmodelFactory(actionProcessorHolder: UserActionProcessorHolder): UserViewModelFactory {
        return UserViewModelFactory(actionProcessorHolder)
    }

    @Provides
    @ActivityScope
    fun provideHomeViewmodelFactory(actionProcessorHolder: HomeActionProcessorHolder): HomeViewmodelFactory {
        return HomeViewmodelFactory(actionProcessorHolder)
    }


}