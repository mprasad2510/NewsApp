package org.mp.newsapp.module


import dagger.Module
import dagger.Provides
import org.mp.newsapp.data.Repository
import org.mp.newsapp.data.RepositoryImpl
import org.mp.newsapp.data.remote.Network

@Module
class DataModule {
    @Provides
    fun provideRepository(service: Network): Repository {
        return RepositoryImpl(service)
    }
}