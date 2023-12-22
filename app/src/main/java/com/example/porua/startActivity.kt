package com.example.porua

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class startActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        start()
    }

    fun start(){

        val go = findViewById<ImageView>(R.id.ImgPorua)

        go.setOnClickListener{
            intent = Intent(this,startPage::class.java)
            startActivity(intent)
        }
    }
}