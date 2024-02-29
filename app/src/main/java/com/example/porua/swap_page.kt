package com.example.porua

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class swap_page : AppCompatActivity() {
    private lateinit var swapBooks:ImageView
    private lateinit var swap:Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swap_page)

        swapBooks = findViewById(R.id.ivAdminSwapBook)
        swap = findViewById(R.id.btnSwap)

        swapBooks.setOnClickListener{
            val intent = Intent(this, RecyclerViewActivity::class.java)
            startActivity(intent)
        }
        swap.setOnClickListener {
            val intent = Intent(this, SwapRequest::class.java)
            startActivity(intent)
        }
    }
}