package br.com.fornaro.matafome.di.module

import android.app.Application
import androidx.room.Room
import br.com.fornaro.matafome.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application) =
        Room.databaseBuilder(application, AppDatabase::class.java, "database").build()

    @Singleton
    @Provides
    fun provideCartDao(database: AppDatabase) = database.cartItemDao()
}