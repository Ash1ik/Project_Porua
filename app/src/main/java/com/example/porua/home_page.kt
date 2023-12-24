package com.example.porua

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView

class home_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        profile()
        scienceFiction()
        novel()
        poetry()
        thriller()
        favorite()
        search_icon()
    }


    fun scienceFiction(){
        val science_fiction_button = findViewById<Button>(R.id.science_fiction_button)

        science_fiction_button.setOnClickListener {
            intent = Intent(this,Science_Fiction::class.java)
            startActivity(intent)
        }
    }
    fun novel(){
        val novel = findViewById<Button>(R.id.novel_button)

        novel.setOnClickListener {
            intent = Intent(this,Novel::class.java)
            startActivity(intent)
        }
    }
    fun poetry(){
        val poetry = findViewById<Button>(R.id.poetry_button)

        poetry.setOnClickListener {
            intent = Intent(this,Poetry::class.java)
            startActivity(intent)
        }
    }
    fun thriller(){
        val thriller = findViewById<Button>(R.id.thriller_button)

        thriller.setOnClickListener {
            intent = Intent(this,Thriller::class.java)
            startActivity(intent)
        }
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
    fun search_icon(){
        val search_icon = findViewById<ImageView>(R.id.search_icon)

        search_icon.setOnClickListener {
            intent = Intent(this,Search_bar::class.java)
            startActivity(intent)
        }
    }
}