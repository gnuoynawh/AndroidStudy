package com.gnuoynawh.prj.mvvmexam.di

import android.content.Context
import androidx.room.Room
import com.gnuoynawh.prj.mvvmexam.data.local.db.MyRoomDatabase
import com.gnuoynawh.prj.mvvmexam.data.repository.AudioRepository
import com.gnuoynawh.prj.mvvmexam.domain.audio.AddAudioItemUseCase
import com.gnuoynawh.prj.mvvmexam.domain.audio.DeleteAudioItemUseCase
import com.gnuoynawh.prj.mvvmexam.domain.audio.GetAudioItemUseCase
import com.gnuoynawh.prj.mvvmexam.domain.audio.GetAudioListUseCase
import com.gnuoynawh.prj.mvvmexam.domain.audio.UpdateAudioItemUseCase
import com.gnuoynawh.prj.mvvmexam.presentation.main.MainViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appModule = module {

    single { Dispatchers.Main }
    single { Dispatchers.IO }

    // viewModel
    viewModel { MainViewModel(get(), get(), get(), get(), get()) }

    // use case
    factory { AddAudioItemUseCase(get()) }
    factory { DeleteAudioItemUseCase(get()) }
    factory { GetAudioItemUseCase(get()) }
    factory { GetAudioListUseCase(get()) }
    factory { UpdateAudioItemUseCase(get()) }

    // repository
    single { AudioRepository(get(), get())}
    single { provideDB(androidApplication()) }
    single { provideAudioDao(get()) }
}

internal fun provideDB(context: Context): MyRoomDatabase =
    Room.databaseBuilder(context, MyRoomDatabase::class.java, MyRoomDatabase.DB_NAME).build()

internal fun provideAudioDao(database: MyRoomDatabase) = database.audioDao()