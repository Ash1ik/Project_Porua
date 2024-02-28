package com.example.porua

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView

class MenuBar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_bar)
        bookShelf()
    }

    fun bookShelf(){

        val bookshelf = findViewById<ImageButton>(R.id.bookshelf)

        bookshelf.setOnClickListener {

            intent = Intent(this,uploadbooks::class.java)
            startActivity(intent)

        }

    }

}