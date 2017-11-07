package com.hatecrab.movies.di.utilites

import com.hatecrab.movies.api.IRemoteApi
import com.hatecrab.movies.interactors.ApiInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InteractorsModule {

    @Provides
    @Singleton
    internal fun provideApiInteractor(remoteApi: IRemoteApi): ApiInteractor {
        return ApiInteractor(remoteApi)
    }
}