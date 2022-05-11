package com.mbobiosio.rickandmorty.di

import android.app.Application
import androidx.paging.ExperimentalPagingApi
import androidx.room.Room
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.mbobiosio.rickandmorty.BuildConfig
import com.mbobiosio.rickandmorty.data.locale.AppDatabase
import com.mbobiosio.rickandmorty.data.repository.CharacterRepositoryImpl
import com.mbobiosio.rickandmorty.domain.repository.CharacterRepository
import com.mbobiosio.rickandmorty.domain.usecase.GetCharactersByName
import com.mbobiosio.rickandmorty.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideGetCharactersByNameUseCase(
        getCharactersRepository: CharacterRepository
    ): GetCharactersByName = GetCharactersByName(getCharactersRepository)

    @Singleton
    @Provides
    @ExperimentalPagingApi
    fun provideCharacterRepository(
        service: ApolloClient,
        database: AppDatabase
    ): CharacterRepository = CharacterRepositoryImpl(service, database)

    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder().apply {
            connectTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            addNetworkInterceptor(
                HttpLoggingInterceptor().apply {
                    level =
                        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                }
            )
        }.build()

    @Singleton
    @Provides
    fun provideRickAndMortyApi(okHttpClient: OkHttpClient): ApolloClient =
        ApolloClient.Builder().apply {
            serverUrl(Constants.BASE_URL)
            okHttpClient(okHttpClient)
        }.build()

    @Singleton
    @Provides
    fun provideRickAndMortyDatabase(
        app: Application
    ): AppDatabase =
        Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "rickmorty.db"
        ).fallbackToDestructiveMigration()
            .build()
}
