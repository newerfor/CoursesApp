// data/repository/CoursesRepository.kt
package com.example.coursesapp.data.repository

import android.content.Context
import androidx.room.Room
import com.example.coursesapp.data.dao.DAO
import com.example.coursesapp.data.database.DataBase
import com.example.coursesapp.data.entity.DataCoursesDataBase
import com.example.coursesapp.data.model.CoursesData
import com.example.coursesapp.data.model.CoursesResponse
import com.google.gson.Gson

class CoursesRepository(private val context: Context) {

    // Ленивая инициализация базы данных
    private val database: DataBase by lazy {
        Room.databaseBuilder(
            context,
            DataBase::class.java,
            "courses-database"
        ) // ← Добавьте эту строку
            .build()
    }

    private val dao: DAO by lazy { database.userDao() }

    fun getCourses(): List<CoursesData> {
        return try {
            val jsonString = context.assets.open("courses.json")
                .bufferedReader().use { it.readText() }

            val response = Gson().fromJson(jsonString, CoursesResponse::class.java)
            response.courses ?: emptyList()

        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    suspend fun getAllCoursesFromDb(): List<DataCoursesDataBase> {
        return dao.getAll()
    }

    suspend fun updateCourseInDb(course: DataCoursesDataBase) {
        dao.UpdateCourse(course)
    }
    // Инициализация БД данными из JSON
    suspend fun PostCoursesInDataBase(value: DataCoursesDataBase) {
         dao.insertCourse(value)
    }

}