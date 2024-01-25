package com.example.porua


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.provider.ContactsContract.CommonDataKinds.SipAddress
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class signUp_Page : AppCompatActivity() {

    private lateinit var tvName : EditText
    private lateinit var tvEmail: EditText
    private lateinit var tvPassword : EditText
    private lateinit var tvConfirmPassword : EditText
    private lateinit var btnNext : Button


    private lateinit var auth: FirebaseAuth
    private var db = Firebase.firestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_sign_up_page)


        auth = Firebase.auth

        tvEmail = findViewById(R.id.etEmail)
        tvPassword = findViewById(R.id.etPassword)
        tvConfirmPassword = findViewById(R.id.etConfirmPassword)
        btnNext = findViewById(R.id.btnNext)


        btnNext.setOnClickListener {

            val sEmail = tvEmail.text.toString().trim()
            val sPassword = tvPassword.text.toString().trim()
            val sConfirmPassword = tvConfirmPassword.text.toString().trim()

            if(sEmail.isNotEmpty() && sPassword.isNotEmpty() && sConfirmPassword.isNotEmpty()){

                if(sPassword == sConfirmPassword){

                    auth.createUserWithEmailAndPassword(sEmail, sPassword)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information

                                saveData()
                                auth.currentUser?.sendEmailVerification()?.addOnSuccessListener {

                                    Toast.makeText(this,"Please Verify your Email",Toast.LENGTH_SHORT).show()


                                }?.addOnFailureListener{
                                    Toast.makeText(this,it.toString(),Toast.LENGTH_SHORT).show()
                                }


                                val user = auth.currentUser
                                updateUI(user)
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
                else{
                    Toast.makeText(this,"Password is not matching",Toast.LENGTH_SHORT).show()
                }

            }
            else{
                Toast.makeText(this,"Empty Fields is not allowed",Toast.LENGTH_SHORT).show()
            }

        }


    }

    private fun updateUI(user: FirebaseUser?) {
        val intent = Intent(this,Login_page::class.java)
        startActivity(intent)
    }

    private fun saveData(){

        tvName = findViewById(R.id.etName)
        tvEmail = findViewById(R.id.etEmail)
        tvPassword = findViewById(R.id.etPassword)
        btnNext = findViewById(R.id.btnNext)
        tvConfirmPassword = findViewById(R.id.etConfirmPassword)



        val sName = tvName.text.toString().trim()
        val sEmail = tvEmail.text.toString().trim()
        val sPassword = tvPassword.text.toString().trim()

        val userMap = hashMapOf(
            "name" to sName,
            "email" to sEmail,
            "password" to sPassword
        )

        val userId = FirebaseAuth.getInstance().currentUser!!.uid

        db.collection("user").document(userId).set(userMap)
            .addOnSuccessListener {
                //Toast.makeText(this, "Successfully added", Toast.LENGTH_SHORT).show()

                tvName.text.clear()
                tvEmail.text.clear()
                tvPassword.text.clear()
                tvConfirmPassword.text.clear()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }



    }


}