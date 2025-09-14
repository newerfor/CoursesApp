
package com.example.coursesapp.di

import android.content.Context
import androidx.room.Room
import com.example.coursesapp.data.dao.DAO
import com.example.coursesapp.data.database.DataBase
import com.example.coursesapp.data.repository.CoursesRepository
import com.example.coursesapp.peresentation.viewmodel.CoursesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Репозиторий (только контекст, база данных создается внутри репозитория)
    single { CoursesRepository(get()) }

    // ViewModel
    viewModel { CoursesViewModel(get()) }
}