package com.example.porua

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class RecyclearViewActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private val bookList = ArrayList<Book>()
    private val db = FirebaseFirestore.getInstance() // Use Firestore extension

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclear_view)

        recyclerView = findViewById(R.id.recycleview)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val currentUserId = FirebaseAuth.getInstance().currentUser!!.uid

        db.collection(currentUserId).get()
            .addOnSuccessListener { result ->
                if (result.isEmpty) {
                    // Handle case where no books are found
                    Toast.makeText(this, "No books found", Toast.LENGTH_SHORT).show()
                    return@addOnSuccessListener
                }

                for (document in result.documents) {
                    val book = document.toObject(Book::class.java)
                    if (book != null) {
                        bookList.add(book)
                    }
                }

                recyclerView.adapter = MyBookAdapter(currentUserId, bookList)
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Error fetching books: $exception", Toast.LENGTH_SHORT).show()
                }

        }
}