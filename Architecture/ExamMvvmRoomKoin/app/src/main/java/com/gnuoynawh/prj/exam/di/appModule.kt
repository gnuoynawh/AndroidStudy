package com.gnuoynawh.prj.exam.di

import android.content.Context
import androidx.room.Room
import com.gnuoynawh.prj.exam.data.local.db.MyRoomDatabase
import com.gnuoynawh.prj.exam.data.repository.FileRepository
import com.gnuoynawh.prj.exam.domain.file.AddFileItemUseCase
import com.gnuoynawh.prj.exam.domain.file.DeleteFileItemUseCase
import com.gnuoynawh.prj.exam.domain.file.GetFileItemUseCase
import com.gnuoynawh.prj.exam.domain.file.GetFileListUseCase
import com.gnuoynawh.prj.exam.domain.file.UpdateFileItemUseCase
import com.gnuoynawh.prj.exam.presentation.main.MainViewModel
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
    factory { AddFileItemUseCase(get()) }
    factory { DeleteFileItemUseCase(get()) }
    factory { GetFileItemUseCase(get()) }
    factory { GetFileListUseCase(get()) }
    factory { UpdateFileItemUseCase(get()) }

    // repository
    single { FileRepository(get(), get())}
    single { provideDB(androidApplication()) }
    single { provideAudioDao(get()) }
}

internal fun provideDB(context: Context): MyRoomDatabase =
    Room.databaseBuilder(context, MyRoomDatabase::class.java, MyRoomDatabase.DB_NAME).build()

internal fun provideAudioDao(database: MyRoomDatabase) = database.audioDao()