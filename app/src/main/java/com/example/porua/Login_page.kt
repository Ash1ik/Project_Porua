package com.example.porua

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Login_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)
        sign_up()
    }
    fun sign_up(){
        val sign_up = findViewById<TextView>(R.id.sign_up)

        sign_up.setOnClickListener {
            intent = Intent(this,signUp_Page::class.java)
            startActivity(intent)
        }
    }
}