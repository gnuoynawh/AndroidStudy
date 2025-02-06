package com.gnuoynawh.practice.mvi

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context) : CounterDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            CounterDatabase::class.java,
            "counter_database"
        ).build()
    }

    @Provides
    fun provideCounterDao(database: CounterDatabase) : CounterDao {
        return database.counterDao()
    }

    @Provides
    @Singleton
    fun provideCounterRepository(counterDao: CounterDao) : CounterRepository {
        return CounterRepository(counterDao)
    }

    @Provides
    fun provideGetCounterUseCase(repository: CounterRepository) : GetCounterUseCase {
        return GetCounterUseCase(repository)
    }

    @Provides
    fun provideUpdateCounterUseCase(repository: CounterRepository) : UpdateCounterUserCase {
        return UpdateCounterUserCase(repository)
    }
}