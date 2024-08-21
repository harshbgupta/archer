package co.si.main.z.networking

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Copyright © 2021 Hell Corporation. All rights reserved.
 *
 * @author Mr. Lucifer
 * @since September 22, 2021
 */

@Module
@InstallIn(SingletonComponent::class)
object ZDI {

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit): ZApi {
        return retrofit.create(ZApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(service: ZApi): ZRepo =
        ZRepo(service)
}