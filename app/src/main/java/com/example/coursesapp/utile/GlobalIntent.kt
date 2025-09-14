package com.example.coursesapp.utile

import android.content.Context
import android.content.Intent

class GlobalIntent {
    fun IntentStart(context: Context, NameActivity:String){
        val actClass = Class.forName(NameActivity)
        val intent = Intent(context,actClass)
        context.startActivity(intent)
    }
    fun IntentStartById(context: Context,NameActivity: String,id:Int){
        val actClass = Class.forName(NameActivity)
        val intent = Intent(context,actClass)
        intent.putExtra("id",id)
        context.startActivity(intent)
    }
}