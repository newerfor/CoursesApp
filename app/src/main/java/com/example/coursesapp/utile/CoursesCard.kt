package com.example.coursesapp.utile

import android.content.Context
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.ArrowDownward
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.ArrowRight
import androidx.compose.material.icons.outlined.ArrowUpward
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.CurrencyRuble
import androidx.compose.material.icons.sharp.Bookmark
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.Bookmark
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.coursesapp.data.model.CoursesData
import com.example.coursesapp.peresentation.viewmodel.CoursesViewModel
import java.text.SimpleDateFormat
import java.util.Locale


class CoursesCard {
    @Composable
    fun Cards(courses: CoursesData,context:Context,viewModel: CoursesViewModel) {
        var onHashLike by remember { mutableStateOf(courses.hasLike) }
        var dateCourses = CoursesDate().formatDateToRussian(courses.startDate)
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .clip(RoundedCornerShape(20.dp))
            ) {
            AsyncImage(model = courses.image, contentDescription = "",
                modifier = Modifier
                    .fillMaxSize()
                    .height(300.dp),
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp, vertical = 8.dp
                    ), // отступы от краёв экрана
                horizontalArrangement = Arrangement.End, // растянуть элементы по краям
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(50))
                        .background(Color.Black.copy(alpha = 0.5f)) // 👈 Прозрачный черный (50%)
                        .clickable {
                            if (onHashLike) {
                                onHashLike = false
                                val newcourses = courses.copy(hasLike = onHashLike)
                                viewModel.UpdateDate(newcourses)
                            } else {
                                onHashLike = true
                                val newcourses = courses.copy(hasLike = onHashLike)
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
                        tint = if(onHashLike){Color(0xFF12B956)}else{Color.White} // 👈 Белый цвет иконки для контраста
                    )
                }
            }
            Column(Modifier.align(Alignment.BottomEnd)) {
                Row(Modifier.padding(3.dp),verticalAlignment = Alignment.CenterVertically) {
                    Column(Modifier.padding(horizontal = 3.dp)) {
                        // Rating with glass effect
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(
                                            Color(0x99222222),  // Более непрозрачный белый (60% opacity)
                                            Color(0x66222222)   // Средняя прозрачность (40% opacity)
                                        )
                                    )
                                )
                                .border(
                                    width = 1.dp,
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            Color(0x99FFFFFF),  // Светлая граница сверху
                                            Color(0x33FFFFFF)   // Более прозрачная снизу
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
                                    tint = Color(0xFF12B956), // Gold color for star
                                    modifier = Modifier.size(15.dp)
                                )
                                Text(
                                    text = courses.rate,
                                    color = Color.White,
                                    modifier = Modifier.padding(start = 4.dp),
                                    style = MaterialTheme.typography.labelMedium
                                )
                            }
                        }
                    }


                    // Date with glass effect
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color(0x99222222),  // Более непрозрачный белый (60% opacity)
                                        Color(0x66222222)   // Средняя прозрачность (40% opacity)
                                    )
                                )
                            )
                            .border(
                                width = 1.dp,
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color(0x99FFFFFF),  // Светлая граница сверху
                                        Color(0x33FFFFFF)   // Более прозрачная снизу
                                    )
                                ),
                                shape = RoundedCornerShape(20.dp)
                            )
                            .padding(horizontal = 10.dp, vertical = 2.dp)
                    ){
                        Text(
                            text = dateCourses,
                            color = Color.White,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFF232328))
                        .padding(12.dp), // затемнение если нужно
                ) {
                    Text(courses.title,fontSize = 20.sp, color = Color.White, modifier = Modifier.padding(vertical = 7.dp))
                    Text(courses.text, maxLines = 2,overflow = TextOverflow.Ellipsis,color = Color.Gray, fontSize = 14.sp, modifier = Modifier.padding(vertical = 7.dp))
                    Row(Modifier.padding(vertical = 7.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically // 👈 Выравнивание по центру
                        ) {
                            Text(
                                courses.price,
                                fontSize = 17.sp,
                                color = Color.White
                            )
                            Icon(
                                imageVector = Icons.Outlined.CurrencyRuble,
                                contentDescription = "Рубль",
                                tint = Color.White,
                                modifier = Modifier.size(17.dp),
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    GlobalIntent().IntentStartById(
                                        context,
                                        "com.example.coursesapp.peresentation.ui.CourseInfo.SingleCourseInfo",
                                        courses.id
                                    )
                                },
                            verticalAlignment = Alignment.CenterVertically, // 👈 Выравнивание по центру вертикально
                            horizontalArrangement = Arrangement.End
                        ) {
                            Text(
                                "Подробнее",
                                color = Color(0xFF12B956),
                                modifier = Modifier.align(Alignment.CenterVertically) // 👈 Дополнительное выравнивание
                            )
                            Icon(
                                imageVector = Icons.Outlined.ArrowForward,
                                contentDescription = "Вправо", // 👈 Исправил описание
                                modifier = Modifier
                                    .size(15.dp)
                                    .padding(horizontal = 1.dp)
                                    .align(Alignment.CenterVertically),
                                tint = Color(0xFF12B956)// 👈 Выравнивание иконки
                            )
                        }
                    }

                }
            }

        }
    }
    @Composable
    fun CardsInProsess(courses: CoursesData,context:Context,viewModel: CoursesViewModel) {
        var onHashLike by remember { mutableStateOf(courses.hasLike) }
        var dateCourses = CoursesDate().formatDateToRussian(courses.startDate)
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .clip(RoundedCornerShape(20.dp))
        ) {
            AsyncImage(model = courses.image, contentDescription = "",
                modifier = Modifier
                    .fillMaxSize()
                    .height(300.dp),
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp, vertical = 8.dp
                    ), // отступы от краёв экрана
                horizontalArrangement = Arrangement.End, // растянуть элементы по краям
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(50))
                        .background(Color.Black.copy(alpha = 0.5f)) // 👈 Прозрачный черный (50%)
                        .clickable {
                            if (onHashLike) {
                                onHashLike = false
                                val newcourses = courses.copy(hasLike = onHashLike)
                                viewModel.UpdateDate(newcourses)
                            } else {
                                onHashLike = true
                                val newcourses = courses.copy(hasLike = onHashLike)
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
                        tint = if(onHashLike){Color(0xFF12B956)}else{Color.White} // 👈 Белый цвет иконки для контраста
                    )
                }
            }
            Column(Modifier.align(Alignment.BottomEnd)) {
                Row(Modifier.padding(3.dp),verticalAlignment = Alignment.CenterVertically) {
                    Column(Modifier.padding(horizontal = 3.dp)) {
                        // Rating with glass effect
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(
                                            Color(0x99222222),  // Более непрозрачный белый (60% opacity)
                                            Color(0x66222222)   // Средняя прозрачность (40% opacity)
                                        )
                                    )
                                )
                                .border(
                                    width = 1.dp,
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            Color(0x99FFFFFF),  // Светлая граница сверху
                                            Color(0x33FFFFFF)   // Более прозрачная снизу
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
                                    tint = Color(0xFF12B956), // Gold color for star
                                    modifier = Modifier.size(15.dp)
                                )
                                Text(
                                    text = courses.rate,
                                    color = Color.White,
                                    modifier = Modifier.padding(start = 4.dp),
                                    style = MaterialTheme.typography.labelMedium
                                )
                            }
                        }
                    }


                    // Date with glass effect
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color(0x99222222),  // Более непрозрачный белый (60% opacity)
                                        Color(0x66222222)   // Средняя прозрачность (40% opacity)
                                    )
                                )
                            )
                            .border(
                                width = 1.dp,
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color(0x99FFFFFF),  // Светлая граница сверху
                                        Color(0x33FFFFFF)   // Более прозрачная снизу
                                    )
                                ),
                                shape = RoundedCornerShape(20.dp)
                            )
                            .padding(horizontal = 10.dp, vertical = 2.dp)
                    ){
                        Text(
                            text = dateCourses,
                            color = Color.White,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFF232328))
                        .padding(8.dp), // затемнение если нужно
                ) {
                    Text(courses.title,fontSize = 20.sp, color = Color.White, modifier = Modifier.padding(vertical = 7.dp, horizontal = 7.dp))
                    Row {
                        Text("50%",  color = Color(0xFF12B956), modifier = Modifier.padding(horizontal =7.dp ))
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                            Text("20", color = Color(0xFF12B956))
                            Text("/44 уроков", color = Color.Gray, modifier = Modifier.padding(horizontal =7.dp ))
                        }
                    }
                    Row {
                        Spacer(
                            modifier = Modifier
                                .weight(1f)
                                .height(15.dp)
                                .padding(5.dp)
                                .clip(RoundedCornerShape(20.dp)) // 👈 Сначала clip
                                .background( Color(0xFF12B956))          // 👈 Потом background
                        )
                        Spacer(
                            modifier = Modifier
                                .weight(1f)
                                .height(15.dp)
                                .padding(5.dp)
                                .clip(RoundedCornerShape(50.dp)) // 👈 Сначала clip
                                .background(Color.DarkGray)         // 👈 Потом background
                        )
                    }
                }
            }

        }
    }
}