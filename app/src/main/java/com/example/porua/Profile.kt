package com.example.porua

import android.content.Intent
import android.graphics.Camera
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage

class Profile : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var btnCamera: ImageButton
    private lateinit var btnUpload: Button
    private lateinit var imgview : ImageView

    private lateinit var tvName: TextView
    private lateinit var tvEmail: TextView
    private lateinit var Singout:Button

    private lateinit var databaseReference: DatabaseReference

    private var db = Firebase.firestore
    private var storageReference = Firebase.storage

    private lateinit var uri : Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)


        bookShelf()

        storageReference = FirebaseStorage.getInstance()

        imageView = findViewById(R.id.tvProfileImage)
        btnCamera = findViewById(R.id.camera)
        btnUpload = findViewById(R.id.btnUpload)

        Singout=findViewById(R.id.btnProfileSingout)
        tvName = findViewById(R.id.tvProfileName)
        tvEmail = findViewById(R.id.tvProfileEmail)


        val galleryImage = registerForActivityResult(
            ActivityResultContracts.GetContent(),
            ActivityResultCallback {
                imageView.setImageURI(it)
                if (it != null) {
                    uri = it
                }
            }
        )

        btnCamera.setOnClickListener {
            galleryImage.launch("image/*")
        }

        btnUpload.setOnClickListener {
            storageReference.getReference("Images").child(System.currentTimeMillis().toString())
                .putFile(uri)
                .addOnSuccessListener{ task ->
                    task.metadata!!.reference!!.downloadUrl
                        .addOnSuccessListener { uri ->
                            val uid = FirebaseAuth.getInstance().currentUser!!.uid
                            val imageMap = mapOf(
                                "url" to uri.toString()
                            )
                            val databaseReference =
                                FirebaseDatabase.getInstance().getReference("userImage")
                            databaseReference.child(uid).setValue(imageMap)
                                .addOnSuccessListener {
                                    Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show()
                                }
                                .addOnFailureListener{
                                    Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                                }
                        }
                }
        }


        val uid = FirebaseAuth.getInstance().currentUser!!.uid

        databaseReference = FirebaseDatabase.getInstance().getReference("userImage")
        databaseReference.child(uid).get()
            .addOnSuccessListener {
                val url = it.child("url").value.toString()

                Glide.with(this).load(url).into(imageView)
            }


        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        val ref = db.collection("user").document(userId)

        ref.get().addOnSuccessListener {
            if(it!=null){
                val name = it.data?.get("name")?.toString()
                val email = it.data?.get("email")?.toString()

                tvName.text = name
                tvEmail.text = email
            }
        }
            .addOnFailureListener{
                Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
            }

        Singout.setOnClickListener {
            Firebase.auth.signOut()

            val intent = Intent(this,Login_page::class.java)
            startActivity(intent)
        }


    }


    fun bookShelf(){

        val bookshelf = findViewById<ImageButton>(R.id.bookshelf)

        bookshelf.setOnClickListener {

            intent = Intent(this,uploadbooks::class.java)
            startActivity(intent)

        }

    }


}