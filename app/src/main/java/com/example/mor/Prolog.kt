package com.example.mor

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*



class Prolog : AppCompatActivity() {
    lateinit var sp : SharedPreferences
    lateinit var databaseRef: DatabaseReference
    lateinit var editor: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prolog)
        val currentUser = FirebaseAuth.getInstance().getCurrentUser()?.getUid()
        databaseRef = FirebaseDatabase.getInstance().getReference()

        databaseRef.child("profile/${currentUser}/firstname").addValueEventListener(object :
            ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot) {

            }
            override fun onCancelled(error: DatabaseError) {}
        })
        databaseRef.child("Age/${currentUser}/age").addValueEventListener(object :
            ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot) {

            }
            override fun onCancelled(error: DatabaseError) {}
        })


        sp = getSharedPreferences("APP_SETTINGS", Context.MODE_PRIVATE)
        editor = sp.edit()

        //editor.putBoolean("is_logged", true).commit()

        if (sp.getBoolean("is_logged", false)) {
            val handler = Handler()
            handler.postDelayed({lenta()}, 3000)
        } else {
            val handler = Handler()
            handler.postDelayed({start()}, 3000)
// пользователь не авторизован
        }
    }

    fun start(){
        val intent = Intent(this, Prolog2::class.java)
        startActivity(intent)
    }
    fun lenta(){
        val intent = Intent(this, Lenta::class.java)
        startActivity(intent)
    }
}