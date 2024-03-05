package com.example.porua

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class Novel : AppCompatActivity() {

    private lateinit var homebtn : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_novel)

        homebtn = findViewById(R.id.homebtn)

        homebtn.setOnClickListener{
            intent = Intent(this, home_page::class.java)
            startActivity(intent)
        }

    }


}