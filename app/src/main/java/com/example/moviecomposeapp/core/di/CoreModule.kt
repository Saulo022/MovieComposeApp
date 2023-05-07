package com.example.moviecomposeapp.core.di

import com.example.moviecomposeapp.core.data.MovieRepositoryImpl
import com.example.moviecomposeapp.core.data.remote.MovieApiTMDB
import com.example.moviecomposeapp.core.data.remote.interceptor.ApiKeyInterceptor
import com.example.moviecomposeapp.core.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CoreModule {

    @Provides
    @Singleton
    fun provideApi(): MovieApiTMDB {
        val client = OkHttpClient.Builder().addInterceptor(ApiKeyInterceptor()).build()

        return Retrofit.Builder().baseUrl(MovieApiTMDB.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create()).client(client).build().create()
    }

    @Singleton
    @Provides
    fun provideRepository(api: MovieApiTMDB): MovieRepository {
        return MovieRepositoryImpl(api)
    }
}

