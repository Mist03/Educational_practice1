package com.example.mor

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Prolog2 : AppCompatActivity() {
    lateinit var sp : SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    private lateinit var btn: Button
    lateinit var txt: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prolog2)

        txt = findViewById(R.id.textViewRegistrationProlog2)
        btn = findViewById(R.id.enterAccountProlog2)
        sp = getSharedPreferences("APP_SETTINGS", Context.MODE_PRIVATE)
        editor = sp.edit()
        val b = intent.getStringExtra("Exit")
        if (b=="1") {
            editor.remove("is_logged").commit()
        }

        btn.setOnClickListener {
            val intent = Intent(this, Log::class.java)
            startActivity(intent)
        }
        txt.setOnClickListener {
            val intent = Intent(this, Reg::class.java)
            startActivity(intent)
        }
    }

}