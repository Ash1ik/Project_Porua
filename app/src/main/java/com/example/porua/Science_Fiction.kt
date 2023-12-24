package com.example.porua

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class Science_Fiction : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_science_fiction)
        profile()
        favorite()
    }
    fun profile(){
        val profile = findViewById<ImageButton>(R.id.Profile)

        profile.setOnClickListener {
            intent = Intent(this,Profile::class.java)
            startActivity(intent)
        }
    }
    fun favorite(){
        val favorite = findViewById<ImageButton>(R.id.favorite)

        favorite.setOnClickListener {
            intent = Intent(this,Favorite_list::class.java)
            startActivity(intent)
        }
    }
}