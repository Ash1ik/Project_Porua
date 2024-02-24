package com.example.porua

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class startActivity : AppCompatActivity() {
    private  lateinit var image:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        start()

    }



    fun start(){

        image = findViewById(R.id.ImgPorua)
        image.alpha = 0f
        image.animate().setDuration(1500).alpha(1f).withEndAction{
            val intent = Intent(this,Login_page::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}


