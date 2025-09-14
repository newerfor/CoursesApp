package com.example.coursesapp.peresentation.ui.Accaunt

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coursesapp.peresentation.viewmodel.CoursesViewModel
import com.example.coursesapp.utile.CoursesCard
import com.example.coursesapp.utile.NavBar
import org.koin.androidx.compose.koinViewModel

class Accaunt : ComponentActivity() {
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
                        Text("Профиль", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Medium, modifier = Modifier.padding(20.dp))
                        Options()
                        Text("Ваши курсы", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Medium, modifier = Modifier.padding(20.dp))
                        YourCourses(context= context)
                    }

                }

                    NavBar().Footer(2, context = context)


            }
        }
    }
    @Composable
    fun Options(){
        Column (Modifier.fillMaxWidth().padding(15.dp).clip(
            RoundedCornerShape(20.dp)
        ).background(Color(0xFF232328))){
            Row(Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 7.dp)) {


            Text("Написать в поддержку", color = Color.White, modifier = Modifier.padding(top = 7.dp))
                Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.End){
                    Icon(
                        imageVector = Icons.Default.ArrowForwardIos,
                        contentDescription = "Back",
                        modifier = Modifier.size(20.dp).padding(top = 7.dp),
                        tint = Color.White
                    )
                }
            }
            Spacer(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxWidth(0.9f) // 👈 Занимает 90% ширины вместо 100%
                    .height(1.dp)
                    .background(Color.Gray)
                    .align(Alignment.CenterHorizontally) // 👈 Центрирование
            )
            Row(Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 3.dp)) {




            Text("Настройки", color = Color.White)
                Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.End){
                    Icon(
                        imageVector = Icons.Default.ArrowForwardIos,
                        contentDescription = "Back",
                        modifier = Modifier.size(20.dp),
                        tint = Color.White
                    )
                }
            }
            Spacer(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxWidth(0.9f) // 👈 Занимает 90% ширины вместо 100%
                    .height(1.dp)
                    .background(Color.Gray)
                    .align(Alignment.CenterHorizontally) // 👈 Центрирование
            )
            Row(Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 7.dp)) {



            Text("Выйти из аккаунта", color = Color.White)
                Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.End){
                    Icon(
                        imageVector = Icons.Default.ArrowForwardIos,
                        contentDescription = "Back",
                        modifier = Modifier.size(20.dp),
                        tint = Color.White
                    )
                }
            }

        }
    }
    @Composable
    fun YourCourses(viewModel: CoursesViewModel = koinViewModel(),context:Context){
        LaunchedEffect(viewModel.courses.isEmpty()) {
            viewModel.getCoursesInProcessing()
        }

        val courses = viewModel.coursesByFavorites
        if (courses.isEmpty()) {
            Text("Загрузка...")
        } else {
            for(cours in courses){
                CoursesCard().CardsInProsess(
                    cours,
                    context = context,viewModel
                )
            }
        }

    }

}


