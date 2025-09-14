package com.example.coursesapp.utile

import java.text.SimpleDateFormat
import java.util.Locale

class CoursesDate {
    fun formatDateToRussian(dateString: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = inputFormat.parse(dateString)

        val outputFormat = SimpleDateFormat("d MMMM yyyy", Locale("ru", "RU"))
        return outputFormat.format(date)
    }
}