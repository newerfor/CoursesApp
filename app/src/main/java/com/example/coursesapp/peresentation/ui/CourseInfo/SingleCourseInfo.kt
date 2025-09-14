package com.example.coursesapp.peresentation.ui.CourseInfo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import coil.compose.AsyncImage
import com.example.coursesapp.R
import com.example.coursesapp.peresentation.viewmodel.CoursesViewModel
import com.example.coursesapp.utile.CoursesDate
import com.example.coursesapp.utile.GlobalIntent
import com.example.coursesapp.utile.NavBar
import org.koin.androidx.compose.koinViewModel


class SingleCourseInfo : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            val id = intent?.getIntExtra("id",-1)
            Column(Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color(0xFF151515))
                ) {
                    val scrollState = rememberScrollState()
                    Column(
                        modifier = Modifier
                            .verticalScroll(scrollState)
                            .fillMaxSize()
                    ) {
                        if(id != -1 && id!=null){
                            InfoCoursesById(id = id, context = context)
                        }

                    }
                }

                    NavBar().Footer(0, context = context)


            }
        }
    }
    @Composable
    fun InfoCoursesById(viewModel: CoursesViewModel = koinViewModel(),id:Int,context: Context){
        LaunchedEffect(viewModel.courses.isEmpty()) {
            viewModel.getCoursesById(id)
        }

        var coursesbyIdInfo by viewModel.coursesById

        if (coursesbyIdInfo != null) {
            var dateCourses = CoursesDate().formatDateToRussian(coursesbyIdInfo!!.startDate)

            var onHashLike by remember { mutableStateOf(coursesbyIdInfo!!.hasLike) }
            Column(Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(270.dp)
                ) {
                    AsyncImage(
                        model = coursesbyIdInfo!!.image,
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(270.dp),
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.TopCenter
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 16.dp,
                                vertical = WindowInsets.statusBars.asPaddingValues().calculateTopPadding(),), // отступы от краёв экрана
                        horizontalArrangement = Arrangement.SpaceBetween, // растянуть элементы по краям
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier.clickable { GlobalIntent().IntentStart(context =context ,"com.example.coursesapp.peresentation.ui.main.MainActivity") }
                                .size(44.dp)
                                .clip(RoundedCornerShape(50))
                                .background(Color.White),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                modifier = Modifier.size(24.dp),
                                tint = Color.Black
                            )
                        }
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(RoundedCornerShape(50))
                                .background(Color.White) .clickable {
                                    if (onHashLike) {
                                        onHashLike = false
                                        val newcourses = coursesbyIdInfo!!.copy(hasLike = onHashLike)
                                        viewModel.UpdateDate(newcourses)
                                    } else {
                                        onHashLike = true
                                        val newcourses = coursesbyIdInfo!!.copy(hasLike = onHashLike)
                                        viewModel.UpdateDate(newcourses)
                                    }
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = if (onHashLike) {
                                    Icons.Filled.Bookmark  // ✅ заполненная
                                } else {
                                    Icons.Outlined.BookmarkBorder // ✅ контурная (пустая)
                                },
                                contentDescription = "Bookmark",
                            )
                        }
                    }
                    // Добавляем Row в левый нижний угол с отступом 7.dp
                    Row(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(7.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Рейтинг
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(
                                            Color(0x99222222),
                                            Color(0x66222222)
                                        )
                                    )
                                )
                                .border(
                                    width = 1.dp,
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            Color(0x99FFFFFF),
                                            Color(0x33FFFFFF)
                                        )
                                    ),
                                    shape = RoundedCornerShape(20.dp)
                                )
                                .padding(horizontal = 10.dp, vertical = 2.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Filled.Star,
                                    contentDescription = "Rating",
                                    tint = Color.Green,
                                    modifier = Modifier.size(15.dp)
                                )
                                Text(
                                    text = coursesbyIdInfo!!.rate,
                                    color = Color.White,
                                    modifier = Modifier.padding(start = 4.dp),
                                    style = MaterialTheme.typography.labelMedium
                                )
                            }
                        }

                        // Отступ между рейтингом и датой
                        Spacer(modifier = Modifier.width(8.dp))

                        // Дата
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(
                                            Color(0x99222222),
                                            Color(0x66222222)
                                        )
                                    )
                                )
                                .border(
                                    width = 1.dp,
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            Color(0x99FFFFFF),
                                            Color(0x33FFFFFF)
                                        )
                                    ),
                                    shape = RoundedCornerShape(20.dp)
                                )
                                .padding(horizontal = 10.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = dateCourses,
                                color = Color.White,
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    }
                }




                Column(Modifier.fillMaxWidth().padding(12.dp)) {
                    Text(coursesbyIdInfo!!.title, color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Medium, modifier = Modifier.padding(vertical = 12.dp))
                    Row (Modifier.padding(vertical = 12.dp)){
                        AsyncImage(model = (R.drawable.preview_avtor), contentDescription = "",Modifier.size(50.dp))
                        Column(Modifier.fillMaxHeight().padding(6.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start) {
                            Text("Автор", color = Color.Gray)
                            Text("Merion Academy", color = Color.White)
                        }
                    }
                    Column(Modifier.fillMaxWidth().padding(vertical = 12.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                        Button(onClick = {}, Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF12B956))) {
                            Text("Начать курс")
                        }
                        Button(onClick = {},Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)){
                            Text("Перейти на платформу")
                        }

                    }
                    Column {
                        Text("О курсе", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Medium)
                        Text(coursesbyIdInfo!!.text, color = Color.Gray, fontSize = 17.sp, modifier = Modifier.padding(vertical = 12.dp))
                    }
                }

            }
        }

    }
}

