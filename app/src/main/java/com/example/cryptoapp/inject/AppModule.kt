package com.example.cryptoapp.inject

import android.content.Context
import com.example.cryptoapp.data.local.AppDatabase
import com.example.cryptoapp.data.local.CryptoDAO
import com.example.cryptoapp.data.remote.CryptoService
import com.example.cryptoapp.data.remote.RemoteDataSource
import com.example.cryptoapp.data.repository.CryptoRepo
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideGSON(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient) : Retrofit = Retrofit.Builder()
        .baseUrl("https://api.coincap.io/v2/")
       // .client(okHttpClient)
        .addConverterFactory((GsonConverterFactory.create(gson)))
        .build()

    @Provides
    fun provideCryptoService(retrofit: Retrofit): CryptoService =
        retrofit.create(CryptoService::class.java)

    @Singleton
    @Provides
    fun provideCryptoRemoteDataSource(cryptoService: CryptoService)=
        RemoteDataSource(cryptoService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCryptoDAO(appDatabase: AppDatabase) =
        appDatabase.cryptoDAO()


    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: RemoteDataSource, localDataSource: CryptoDAO)
            = CryptoRepo(remoteDataSource, localDataSource)

    @Provides
    fun getOkhttp(): OkHttpClient{


        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        return  OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }


}