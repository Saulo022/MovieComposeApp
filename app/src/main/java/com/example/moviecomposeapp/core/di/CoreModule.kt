package com.example.moviecomposeapp.core.di

import android.app.Application
import androidx.room.Room
import com.example.moviecomposeapp.core.data.MovieRepositoryImpl
import com.example.moviecomposeapp.core.data.local.MovieDao
import com.example.moviecomposeapp.core.data.local.MovieDatabase
import com.example.moviecomposeapp.core.data.remote.MovieApiTMDB
import com.example.moviecomposeapp.core.data.remote.interceptor.ApiKeyInterceptor
import com.example.moviecomposeapp.core.domain.repository.MovieRepository
import com.example.moviecomposeapp.core.domain.usecase.ReduceFilteredMovies
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
        val client = OkHttpClient.Builder().addInterceptor(ApiKeyInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()

        return Retrofit.Builder().baseUrl(MovieApiTMDB.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create()).client(client).build().create()
    }

    @Singleton
    @Provides
    fun provideDatabase(application: Application): MovieDatabase {
        return Room.databaseBuilder(application, MovieDatabase::class.java, "movies_db").build()
    }

    @Singleton
    @Provides
    fun provideDatabaseDao(database: MovieDatabase): MovieDao {
        return database.dao
    }

    @Singleton
    @Provides
    fun provideRepository(api: MovieApiTMDB, dao: MovieDao): MovieRepository {
        return MovieRepositoryImpl(api, dao, ReduceFilteredMovies())
    }
}

