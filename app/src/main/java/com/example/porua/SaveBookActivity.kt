package com.example.porua

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class SaveBookActivity : AppCompatActivity() {

    private lateinit var bookName: TextView
    private lateinit var writerName: TextView
    private lateinit var bookDescription: TextView
    private lateinit var bookFeedback: TextView
    private lateinit var btnSave: Button

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_book)

        bookName = findViewById(R.id.tvUpBookName)
        writerName = findViewById(R.id.tvUpWriterName)
        bookDescription = findViewById(R.id.tvUpDescription)
        bookFeedback = findViewById(R.id.tvUpFeedback)
        btnSave = findViewById(R.id.btnSave)

        btnSave.setOnClickListener {
            val sName = bookName.text.toString().trim()
            val sAddress = writerName.text.toString().trim()
            val sEmail = bookDescription.text.toString().trim()
            val sPass = bookFeedback.text.toString().trim()

            val userMap = hashMapOf(
                "bookName" to sName,
                "writerName" to sAddress,
                "bookDescription" to sEmail,
                "bookFeedback" to sPass
            )

            val userId = FirebaseAuth.getInstance().currentUser?.uid
            // Generate a unique book ID using document ID auto-generation
            val bookId = db.collection("Books").document().id

            userId?.let { uid ->
                db.collection(userId).document(bookId).set(userMap)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Successfully added", Toast.LENGTH_SHORT).show()
                        bookName.text = ""
                        writerName.text = ""
                        bookDescription.text = ""
                        bookFeedback.text = ""
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Failed to add", Toast.LENGTH_SHORT).show()
                    }



                FirebaseDatabase.getInstance().reference.child("Books").child(bookId)
                    .setValue(userMap)
                    .addOnSuccessListener {
                        // ... (existing success handling)
                        Toast.makeText(this, "Successfully added in database", Toast.LENGTH_SHORT)
                            .show()
                        bookName.text = ""
                        writerName.text = ""
                        bookDescription.text = ""
                        bookFeedback.text = ""
                    }
                    .addOnFailureListener {
                        // ... (existing error handling)
                        Toast.makeText(this, "Failed to add", Toast.LENGTH_SHORT).show()
                    }

            }
        }
    }
}