package com.example.porua

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class home_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        profile()
    }

    fun profile(){
        val profile = findViewById<ImageButton>(R.id.Profile)

        profile.setOnClickListener {
            intent = Intent(this,Profile::class.java)
            startActivity(intent)
        }
    }

}