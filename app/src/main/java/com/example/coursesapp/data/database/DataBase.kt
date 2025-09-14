package com.example.coursesapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.coursesapp.data.dao.DAO
import com.example.coursesapp.data.entity.DataCoursesDataBase

@Database(entities = [DataCoursesDataBase::class], version = 2)
abstract class DataBase : RoomDatabase() {
    abstract fun userDao(): DAO
}