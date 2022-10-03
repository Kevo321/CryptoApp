package com.example.cryptoapp.inject

import android.content.Context
import com.example.cryptoapp.data.local.AppDatabase
import com.example.cryptoapp.data.local.CryptoDAO
import com.example.cryptoapp.data.local.RatesAppDatabase
import com.example.cryptoapp.data.local.RatesDAO
import com.example.cryptoapp.data.remote.CryptoService
import com.example.cryptoapp.data.remote.RemoteDataSource
import com.example.cryptoapp.data.remote.rates.RatesRemoteDataSource
import com.example.cryptoapp.data.remote.rates.RatesService
import com.example.cryptoapp.data.repository.CryptoRepo
import com.example.cryptoapp.data.repository.RatesRepo
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RatesAppModule {





    @Provides
    fun provideCryptoService(retrofit: Retrofit): RatesService =
        retrofit.create(RatesService::class.java)

    @Singleton
    @Provides
    fun provideCryptoRemoteDataSource(ratesService: RatesService)=
        RatesRemoteDataSource(ratesService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        RatesAppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCryptoDAO(appDatabase: RatesAppDatabase) =
        appDatabase.ratesDAO()


    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: RatesRemoteDataSource, localDataSource: RatesDAO)
            = RatesRepo(remoteDataSource, localDataSource)

}