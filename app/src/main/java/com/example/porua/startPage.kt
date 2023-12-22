package com.example.porua

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class startPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_page)
        continueWithmail()
    }
    fun continueWithmail(){
        val  continueWithmail = findViewById<Button>(R.id.Email)

        continueWithmail.setOnClickListener{
            intent = Intent(this,Login_page::class.java)
            startActivity(intent)
        }

        val browse = findViewById<Button>(R.id.Browse)

        browse.setOnClickListener {
            intent = Intent(this,home_page::class.java)
            startActivity(intent)
        }
    }

}