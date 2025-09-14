package com.example.coursesapp.peresentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursesapp.data.entity.DataCoursesDataBase
import com.example.coursesapp.data.model.CoursesData
import com.example.coursesapp.data.repository.CoursesRepository
import kotlinx.coroutines.launch

class CoursesViewModel(
    private val repository: CoursesRepository
) : ViewModel() {

    var courses by mutableStateOf<List<CoursesData>>(emptyList())
    var coursesByFavorites = mutableStateListOf<CoursesData>()
    var coursesById = mutableStateOf<CoursesData?>(null)
    var LoadDataBase by mutableStateOf(false)
    init {
        loadCourses()
        viewModelScope.launch { repository.getAllCoursesFromDb() }
    }
    fun loadCourses() {
        viewModelScope.launch {
            val jsonCourses = repository.getCourses()

            // Если JSON данные некорректны/пустые - грузим из БД
            if ( jsonCourses.isEmpty()) {
                val dbCourses = repository.getAllCoursesFromDb()
                courses = dbCourses.map {
                    CoursesData(it.id, it.image, it.title, it.text, it.price,
                        it.rate, it.startDate, it.hasLike, it.publishDate)
                }
                return@launch
            }

            // Если JSON данные корректны - синхронизируем с БД
            val dbCourses = repository.getAllCoursesFromDb()
            val dbCoursesMap = dbCourses.associateBy { it.id }

            // Обновляем hasLike из БД в JSON данных
            val syncedCourses = jsonCourses.map { jsonCourse ->
                val dbCourse = dbCoursesMap[jsonCourse.id]
                if (dbCourse != null) {
                    jsonCourse.copy(hasLike = dbCourse.hasLike) // Берем hasLike из БД
                } else {
                    jsonCourse
                }
            }
            if(dbCourses.isEmpty()){
                syncedCourses.forEach { course ->
                    repository.PostCoursesInDataBase(
                        DataCoursesDataBase(
                            id = course.id,
                            image = course.image,
                            title = course.title,
                            text = course.text,
                            price = course.price,
                            rate = course.rate,
                            startDate = course.startDate,
                            hasLike = course.hasLike,
                            publishDate = course.publishDate
                        )
                    )
                }
            }
            else{
                syncedCourses.forEach { course ->
                    repository.updateCourseInDb(
                        DataCoursesDataBase(
                            id = course.id,
                            image = course.image,
                            title = course.title,
                            text = course.text,
                            price = course.price,
                            rate = course.rate,
                            startDate = course.startDate,
                            hasLike = course.hasLike,
                            publishDate = course.publishDate
                        )
                    )
                }
            }
            // Сохраняем синхронизированные данные в БД
            courses = syncedCourses
        }
    }
    fun loadCoursesByFavorites(){
        viewModelScope.launch {
            Log.d("agieoeugeugue",courses.toString())
            if(!courses.isEmpty()){
                for(course in courses){
                    if(course.hasLike){
                        coursesByFavorites.add(course)
                    }
                }
            }

        }
    }
    fun getCoursesById(id:Int){
        if(!courses.isEmpty()) {
            for (course in courses) {
                if (course.id == id) {
                    coursesById.value = course
                }
            }
        }
    }
    fun UpdateDate(courses:CoursesData){
        viewModelScope.launch {
            val value = DataCoursesDataBase(
                id = courses.id,
                image = courses.image,
                title = courses.title,
                text = courses.text,
                price = courses.price,
                rate = courses.price,
                startDate = courses.startDate,
                hasLike = courses.hasLike,
                publishDate = courses.publishDate
            )
            repository.updateCourseInDb(value)
        }

    }
    fun getCoursesInProcessing(){
        if(!courses.isEmpty()) {

            for (course in courses) {
                if (course.hasLike) {
                    coursesByFavorites.add(course)
                }
            }
        }
    }
}