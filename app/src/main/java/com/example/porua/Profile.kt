package com.example.porua

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Profile : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var imageView: ImageView
    private lateinit var logout : Button

    companion object {
        val IMAGE_REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile) // Replace with your layout file

        button = findViewById(R.id.profilrImageButton)
        imageView = findViewById(R.id.profileImage)
        logout = findViewById(R.id.btnLogout)

        logout()

        button.setOnClickListener {
            pickImageGallery()
        }
    }

    private fun pickImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) { // Fix here
            imageView.setImageURI(data?.data)
        }
    }

    private fun logout(){

        logout.setOnClickListener {
            Firebase.auth.signOut()

            val intent = Intent(this,startPage::class.java)
            startActivity(intent)
        }


    }
}
