package com.example.porua

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView

class Cart : AppCompatActivity() {

    var num = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val plus = findViewById<ImageButton>(R.id.plus)
        val minus = findViewById<ImageButton>(R.id.minus)
        val count = findViewById<TextView>(R.id.count)

        plus.setOnClickListener {
            num++


            count.text = num.toString()
        }
        minus.setOnClickListener {
            num--


            count.text = num.toString()
        }

    }
}