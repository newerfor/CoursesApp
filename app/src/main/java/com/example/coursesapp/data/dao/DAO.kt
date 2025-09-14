package com.example.coursesapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.coursesapp.data.entity.DataCoursesDataBase

@Dao
interface DAO {
    @Query("SELECT * FROM courses")
    suspend  fun getAll(): List<DataCoursesDataBase>

    @Insert // 👈 Добавьте insert
    suspend fun insertCourse(value:DataCoursesDataBase)

    @Update
    suspend  fun UpdateCourse(user: DataCoursesDataBase)
}