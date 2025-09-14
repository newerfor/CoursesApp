package com.example.coursesapp.peresentation.ui.Avtorization

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coursesapp.R
import com.example.coursesapp.utile.GlobalIntent
import java.net.URLConnection

class Avtorization : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            AvtorizationValue()

        }
    }

    @Composable
    fun AvtorizationValue() {
        Column(
            Modifier.fillMaxSize().background(Color(0xFF151515)).padding(20.dp),
            Arrangement.Center
        ) {
            var email = remember { mutableStateOf("") }
            var password = remember { mutableStateOf("") }
            Text("Вход", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Medium)
            InputAvtorization("Email", "example@gmail.com",email.value) { newemail ->
                email.value = newemail
            }
            InputAvtorization("Пароль", "Введите пароль",password.value){ newpassword ->
                password.value = newpassword
            }
            AvtorizationButton(email,password)
            Spacer (Modifier.fillMaxWidth().padding(vertical = 40.dp).height(1.dp).background(Color.Gray))
            AvtorizationInMessangers()
        }
    }
    fun isEmailValid(email: String): Boolean {
        val pattern = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$")
        return pattern.matches(email)
    }

    @Composable
    fun AvtorizationInMessangers(){
        val context = LocalContext.current
        Row(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Box(
                modifier = Modifier
                    .weight(1f).padding(horizontal = 12.dp)
                    .height(45.dp)
                    .background(
                        color = Color(0xFF0178FF), // VK-синий
                        shape = RoundedCornerShape(25.dp)
                    ).clickable {       val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/"))
                        context.startActivity(intent)
                    },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.vkicon),
                    contentDescription = "VK",
                    modifier = Modifier.size(40.dp)
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f).padding(horizontal = 12.dp)
                    .height(45.dp)
                    .background(
                        color = Color(0xFFED830A), // ОК-оранжевый
                        shape = RoundedCornerShape(25.dp)
                    ).clickable {       val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://ok.ru/"))
                        context.startActivity(intent)
                    },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.odnicon),
                    contentDescription = "Одноклассники",
                    modifier = Modifier.size(70.dp)

                )
            }
        }
    }
    @Composable
    fun AvtorizationButton(email: MutableState<String>, password: MutableState<String>) {
        val context = LocalContext.current
        var isFormValid = email.value.isNotBlank() && password.value.isNotBlank() && isEmailValid(email.value)
        Column(Modifier.fillMaxWidth().padding(vertical = 12.dp),Arrangement.Top,Alignment.CenterHorizontally) {
            Button( onClick = {
                if(isFormValid){
                    GlobalIntent().IntentStart(context =context, "com.example.coursesapp.peresentation.ui.main.MainActivity")

                }
                }, Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(
                    containerColor = if (isFormValid) {
                        Color(0xFF12B956)
                    } else {
                        Color.Gray
                    }
                )) {
                Text("Вход", textAlign = TextAlign.Center)
            }
            Row (Modifier.fillMaxWidth().padding(12.dp),Arrangement.Center,Alignment.Top,){
                Text("Нету аккаунта?", color = Color.White)
                Text("Регистраця", modifier = Modifier.padding(horizontal = 6.dp), color =  Color(0xFF12B956))
            }
            Text("Забыл пароль", color = Color(0xFF12B956))
        }

    }
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun InputAvtorization(name: String, TextInput: String,value:String, onChange: (String) -> Unit) {
        Column(Modifier.padding(vertical = 9.dp)) {
            Text(
                "$name",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(vertical = 17.dp)
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(30.dp))
                    .background(Color(0xFF32333A)),
                value = value,
                onValueChange = { newtext->
                    if (newtext.matches(Regex("^[a-zA-Z0-9@._-]*$"))) {
                    onChange(newtext)
                }},
                placeholder = { Text(
                    text = TextInput,
                    color = Color.Gray
                )},
                colors = TextFieldDefaults.outlinedTextFieldColors(focusedTextColor = Color.White)
            )
        }

    }
}