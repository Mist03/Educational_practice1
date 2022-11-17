package com.example.mor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class Lenta : AppCompatActivity() {
    lateinit var textView: TextView
    lateinit var databaseRef: DatabaseReference
    lateinit var getName : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lenta)

        val currentUser = FirebaseAuth.getInstance().getCurrentUser()?.getUid()

        textView = findViewById(R.id.textViewName)
        getName = findViewById(R.id.imageViewPerehodLentaProfile)
        databaseRef = FirebaseDatabase.getInstance().getReference()

        databaseRef.child("profile/${currentUser}/firstname").addValueEventListener(object : ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot) {
                textView.text = ("С возвращением ${snapshot.value}")
            }
            override fun onCancelled(error: DatabaseError) {}
        })
        getName.setOnClickListener {
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }


    }


}
