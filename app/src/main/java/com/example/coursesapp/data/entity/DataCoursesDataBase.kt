package com.example.coursesapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "courses")
data class DataCoursesDataBase(
    @PrimaryKey
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
