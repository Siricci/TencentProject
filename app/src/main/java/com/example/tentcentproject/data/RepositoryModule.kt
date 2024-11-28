package com.example.tentcentproject.data

import android.app.Application
import androidx.room.Room
import com.example.tentcentproject.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideDatabase(
        app: Application
    ): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "appDatabase"
        ).build()
    }

    @Provides
    @Singleton
    fun provideGalleryRepository(
        appDatabase: AppDatabase
    ): GalleryRepository {
        return GalleryRepository(appDatabase)
    }
}