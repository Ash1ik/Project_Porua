package com.example.porua

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.porua.databinding.ActivityLoginPageBinding
import com.google.firebase.auth.FirebaseAuth

class Login_page : AppCompatActivity() {

    private lateinit var binding : ActivityLoginPageBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginPageBinding.inflate(layoutInflater)

        setContentView(binding.root)
        sign_up()
        sign_in()
    }
    fun sign_up(){
        val sign_up = findViewById<TextView>(R.id.sign_up)

        sign_up.setOnClickListener {
            intent = Intent(this,signUp_Page::class.java)
            startActivity(intent)
        }
    }
    private fun sign_in(){

        firebaseAuth = FirebaseAuth.getInstance()

        binding.Login.setOnClickListener {

            val email = binding.InputEmail.text.toString()
            val pass = binding.InputPass.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty()){

                firebaseAuth.signInWithEmailAndPassword(email , pass).addOnCompleteListener{

                    if (it.isSuccessful){
                        intent = Intent(this, home_page::class.java)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else{
                Toast.makeText(this,"Empty Fields is not allowed", Toast.LENGTH_SHORT).show()
            }


        }

    }

}