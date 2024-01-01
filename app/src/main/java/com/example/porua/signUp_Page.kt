package com.example.porua


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.inputmethod.InputBinding
import android.widget.Toast
import com.example.porua.databinding.ActivitySignUpPageBinding
import com.google.firebase.auth.FirebaseAuth


class signUp_Page : AppCompatActivity() {

    private lateinit var binding : ActivitySignUpPageBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpPageBinding.inflate(layoutInflater)

        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.next.setOnClickListener {

            val email = binding.InputEmail.text.toString()
            val pass = binding.InputPass.text.toString()
            val confirmPass = binding.InputConfirmPass.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()){
                if(pass == confirmPass){
                    firebaseAuth.createUserWithEmailAndPassword(email ,pass).addOnCompleteListener{

                        if(it.isSuccessful){
                            binding.InputEmail.text.clear()
                            binding.InputPass.text.clear()
                            Toast.makeText(this,"Account created successfully",Toast.LENGTH_SHORT).show()
                            intent = Intent(this,home_page::class.java)
                            startActivity(intent)
                        }
                        else{
                            Toast.makeText(this,it.exception.toString(),Toast.LENGTH_SHORT).show()
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

}