package com.example.porua

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Login_page : AppCompatActivity() {


    private lateinit var btnLogin : Button
    private lateinit var etEmail: EditText
    private lateinit var etPassword : EditText
    private lateinit var forgotPass : TextView


    private lateinit var auth: FirebaseAuth





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)


        auth = Firebase.auth

        btnLogin = findViewById(R.id.btnLogin)
        etEmail = findViewById(R.id.Input_email)
        etPassword = findViewById(R.id.Input_pass)

        sign_up()
        log_in()
        resetPass()


        
    }
    private fun sign_up(){
        val sign_up = findViewById<TextView>(R.id.sign_up)

        sign_up.setOnClickListener {
            intent = Intent(this,signUp_Page::class.java)
            startActivity(intent)
        }
    }



    private fun log_in(){

        btnLogin.setOnClickListener {

            val sEmail = etEmail.text.toString().trim()
            val sPassword = etPassword.text.toString().trim()

            if(sEmail.isEmpty() && sPassword.isEmpty()){

                Toast.makeText(this,"The field is empty",Toast.LENGTH_SHORT).show()

            }
            else{

                auth.signInWithEmailAndPassword(sEmail, sPassword)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information

                            val verification = auth.currentUser?.isEmailVerified

                            if(verification == true){
                                val user = auth.currentUser
                                updateUI()
                            }
                            else{
                                Toast.makeText(this,"Please verify your Email",Toast.LENGTH_SHORT).show()
                            }

                        } else {

                            // If sign in fails, display a message to the user.
                            Toast.makeText(
                                baseContext,
                                "Authentication failed.",
                                Toast.LENGTH_SHORT,
                            ).show()
                            //updateUI(null)
                        }
                    }

            }

        }

    }

    private fun  resetPass(){

        forgotPass = findViewById(R.id.forgotPassword)

        auth = FirebaseAuth.getInstance()

        forgotPass.setOnClickListener {
            intent = Intent(this,ResetPassword::class.java)
            startActivity(intent)
        }

    }


    private fun updateUI() {
        val intent = Intent(this,home_page::class.java)
        startActivity(intent)
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            updateUI()
        }
    }




}

//    private fun log_in(){
//
//        binding.Login.setOnClickListener {
//
//            val email = binding.InputEmail.text.toString()
//            val pass = binding.InputPass.text.toString()
//
//            if(email.isNotEmpty() && pass.isNotEmpty()){
//
//                firebaseAuth.signInWithEmailAndPassword(email , pass).addOnCompleteListener{
//
//                    if (it.isSuccessful){
//                        binding.InputEmail.text.clear()
//                        binding.InputPass.text.clear()
//                        intent = Intent(this, home_page::class.java)
//                        startActivity(intent)
//
//                    }
//                    else{
//                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//            else{
//                Toast.makeText(this,"Empty Fields is not allowed", Toast.LENGTH_SHORT).show()
//            }
//
//
//        }
//
//    }

