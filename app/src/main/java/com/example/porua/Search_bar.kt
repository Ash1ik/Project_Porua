package com.example.porua

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.firebase.Firebase
import com.google.firebase.database.database

class Search_bar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_bar)
        science_fiction()
    }

    fun science_fiction(){

        val science = findViewById<Button>(R.id.science_fiction)

        science.setOnClickListener {
            val database = Firebase.database
            val myRef = database.getReference("message")

            myRef.setValue("PORUA")
        }
    }
}

//    fun sendData(view: View) {
//        val database = Firebase.database
//        val myRef = database.getReference("message")
//
//        myRef.setValue("Hello, World")
//    }
//}