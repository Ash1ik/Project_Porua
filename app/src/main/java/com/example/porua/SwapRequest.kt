package com.example.porua

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SwapRequest : AppCompatActivity() {
    private lateinit var BkNumber:EditText
    private lateinit var SpRqst:Button
    private lateinit var PhNum:EditText
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swap_request)

        BkNumber = findViewById(R.id.etBookNumber)
        SpRqst = findViewById(R.id.btnSpRqst)
        PhNum = findViewById(R.id.etPhoneNum)

        SpRqst.setOnClickListener {
            val bookNumber = BkNumber.text.toString().trim()
            val phoneNum = PhNum.text.toString().trim()
            val userId = FirebaseAuth.getInstance().currentUser?.uid


            val userMap = hashMapOf(
                "BookNumber" to bookNumber,
                "UserId" to userId,
                "PhoneNumber" to phoneNum
            )


            if (userId != null) {
                FirebaseDatabase.getInstance().reference.child("BooksRequst").child(userId)
                    .setValue(userMap)
                    .addOnSuccessListener {
                        // ... (existing success handling)
                        Toast.makeText(this, "Successfully added in database", Toast.LENGTH_SHORT)
                            .show()

                    }
                    .addOnFailureListener {
                        // ... (existing error handling)
                        Toast.makeText(this, "Failed to add", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }
}