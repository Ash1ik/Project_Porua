package com.example.porua

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class ResetPassword : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var btnResetPassword: Button
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        etEmail = findViewById(R.id.enterEmail)
        btnResetPassword = findViewById(R.id.btnResetPassword)

        auth = FirebaseAuth.getInstance()

        btnResetPassword.setOnClickListener {
            val sEmail = etEmail.text.toString()
            auth.sendPasswordResetEmail(sEmail)
                .addOnSuccessListener {
                    Toast.makeText(this, "Please check your Email", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, it.toString(),Toast.LENGTH_SHORT).show()
                }
        }


    }
}