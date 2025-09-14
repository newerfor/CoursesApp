package com.example.coursesapp.peresentation.ui.Favourites

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coursesapp.peresentation.viewmodel.CoursesViewModel
import com.example.coursesapp.utile.CoursesCard
import com.example.coursesapp.utile.NavBar
import org.koin.androidx.compose.koinViewModel

class Favourites : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current

            Column(Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color(0xFF151515))
                        .padding(
                            top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding(),
                        )
                ) {
                    val scrollState = rememberScrollState()
                    Column(
                        modifier = Modifier
                            .verticalScroll(scrollState)
                            .fillMaxSize()
                    ) {
                        CoursesScreenByFavorites(
                            context = context
                        )
                    }
                }

                    NavBar().Footer(1, context = context)


            }
        }
    }

    @Composable
    fun CoursesScreenByFavorites(viewModel: CoursesViewModel = koinViewModel(),context: Context) {

        LaunchedEffect(viewModel.courses.isEmpty()) {
            viewModel.loadCoursesByFavorites()
        }
        Text("Избранное", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Medium, modifier = Modifier.padding(20.dp))

        val courses = viewModel.coursesByFavorites
        if (courses.isEmpty()) {
            Text("Загрузка...")
        } else {
            for(cours in courses){
                CoursesCard().Cards(
                    cours,
                    context = context,viewModel
                )
            }
        }
    }
}
