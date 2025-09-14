package com.example.coursesapp.data.model

data class CoursesData(
    val id: Int,
    val image:String,
    val title: String,
    val text: String,
    val price: String,
    val rate: String,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String
)

data class CoursesResponse(
    val courses: List<CoursesData>
)