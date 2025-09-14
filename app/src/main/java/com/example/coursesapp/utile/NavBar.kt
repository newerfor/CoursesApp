package com.example.coursesapp.utile


import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.Bookmark
import androidx.compose.ui.unit.dp

class NavBar {
    @Composable
    fun Footer(activeIndex: Int = 0,context:Context) {
        var activateIndex by remember { mutableStateOf(activeIndex) }
        Row(
            modifier = Modifier
                .background( Color(0xFF24252A))
                .height(100.dp)
                .fillMaxWidth().padding(bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            val items = listOf("Главная", "Избранное", "Аккаунт")
            val icons = listOf(Icons.Outlined.Home, Icons.Outlined.BookmarkBorder, Icons.Outlined.Person)
            items.forEachIndexed { index, label ->
                Column(
                    modifier = Modifier.weight(1f).padding(top = 12.dp).fillMaxHeight().clickable {
                        if(index == 0){
                            GlobalIntent().IntentStart(context,"com.example.coursesapp.peresentation.ui.main.MainActivity")
                        }
                        else{
                            if(index == 1){
                                GlobalIntent().IntentStart(context,"com.example.coursesapp.peresentation.ui.Favourites.Favourites")
                            }
                            else{
                                if(index==2){
                                    GlobalIntent().IntentStart(context,"com.example.coursesapp.peresentation.ui.Accaunt.Accaunt")
                                }
                            }
                        }

                                                                                                  },
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    if(index==activeIndex){
                        Column(Modifier.clip(
                            RoundedCornerShape(20.dp)).width(64.dp).height(31.dp).background(Color.Gray)
                            , horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                            Icon(
                                imageVector = icons[index],
                                contentDescription = label,
                                modifier = Modifier.size(24.dp),
                                tint = if (index == activeIndex) Color(0xFF3CB043) else Color.White // зелёный активный
                            )
                        }
                    }
                    else{
                        Icon(
                            imageVector = icons[index],
                            contentDescription = label,
                            modifier = Modifier.size(24.dp),
                            tint = if (index == activeIndex) Color(0xFF3CB043) else Color.White // зелёный активный
                        )
                    }


                    Text(
                        text = label,
                        color = if (index == activeIndex) Color(0xFF3CB043) else Color.White,
                        modifier = Modifier.padding(top = 4.dp),
                    )
                }
            }
        }
    }

}

