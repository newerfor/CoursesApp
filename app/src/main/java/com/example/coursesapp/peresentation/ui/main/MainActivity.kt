package com.example.coursesapp.peresentation.ui.main

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.ArrowCircleUp
import androidx.compose.material.icons.outlined.ArrowDownward
import androidx.compose.material.icons.outlined.ArrowUpward
import androidx.compose.material.icons.outlined.SwapVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.coursesapp.R
import com.example.coursesapp.data.model.CoursesData
import com.example.coursesapp.di.appModule
import com.example.coursesapp.peresentation.viewmodel.CoursesViewModel
import com.example.coursesapp.utile.CoursesCard
import com.example.coursesapp.utile.NavBar
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.compose.koinViewModel
import org.koin.core.context.GlobalContext
import org.koin.core.context.GlobalContext.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (GlobalContext.getOrNull() == null) {
            startKoin {
                androidContext(this@MainActivity)
                modules(appModule)
            }
        }
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
                        SerchAndFilters()
                        Row(Modifier.fillMaxWidth().padding(horizontal = 30.dp), verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.End){
                            Text("По дате обновления ", color = Color(0xFF12B956))
                            Image(painter = painterResource(R.drawable.filterimage), contentDescription = "",Modifier.size(15.dp))
                        }
                        CoursesScreen(context = context)
                    }
                }

                    NavBar().Footer(0, context = context)

            }
        }
    }

    @Composable
    fun SerchAndFilters(){
        var searchText by remember { mutableStateOf("") }
        Row(Modifier.fillMaxWidth().padding(20.dp)) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(45.dp)).weight(1f)
                ) {
                    OutlinedTextField(
                        value = searchText,
                        onValueChange = { newText -> searchText = newText },
                        modifier = Modifier
                            .fillMaxWidth().background(Color(0xFF24252A)),
                        placeholder = {
                            Text(
                                text = "Search courses...",
                                color = Color.Gray
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search",
                                modifier = Modifier.size(24.dp),
                                tint = Color.White
                            )
                        },

                        )
                }
                Column(Modifier.size(56.dp).clip(RoundedCornerShape(50.dp)).background(Color(0xFF24252A)).padding(horizontal = 12.dp)) {
                    Column(Modifier.fillMaxSize(),Arrangement.Center,Alignment.CenterHorizontally) {
                        Image(painter = painterResource(R.drawable.filterimagesecond) , contentDescription = "",Modifier.size(40.dp))
                    }
                }

        }
    }
    @Composable
    fun CoursesScreen(viewModel: CoursesViewModel = koinViewModel(),context:Context) {
        val courses = viewModel.courses
        if (courses.isEmpty()) {
            Text("Загрузка...")
        } else {
           for(cours in courses){
               CoursesCard().Cards(cours, context = context,viewModel)
           }
        }
    }

}