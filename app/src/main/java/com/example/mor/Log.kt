package com.example.mor

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.content.SharedPreferences
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*



class Log : AppCompatActivity() {
    private lateinit var tvRedirectSignUp: Button
    lateinit var etEmail: EditText
    private lateinit var etPass: EditText
    private lateinit var btnLogin: Button
    lateinit var databaseRef: DatabaseReference
    lateinit var sp : SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    // Creating firebaseAuth object
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log)
        // View Binding
        tvRedirectSignUp = findViewById(R.id.btnRegistartionSignIn)
        btnLogin = findViewById(R.id.btnSigninSignIn)
        etEmail = findViewById(R.id.editTextEmailSignIn)
        etPass = findViewById(R.id.editTextPasswordSignIn)


        // initialising Firebase auth object
        auth = FirebaseAuth.getInstance()


        btnLogin.setOnClickListener {
            login()
        }

        tvRedirectSignUp.setOnClickListener {
            val intent = Intent(this, Reg::class.java)
            startActivity(intent)

        }
    }

    private fun login() {
        val email = etEmail.text.toString()
        val pass = etPass.text.toString()
        // check pass
        if (email.isBlank() || pass.isBlank()) {
            Toast.makeText(this, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }


        // calling signInWithEmailAndPassword(email, pass)
        // function using Firebase auth object
        // On successful response Display a Toast
        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Successfully LoggedIn", Toast.LENGTH_SHORT).show()

                val currentUser = FirebaseAuth.getInstance().getCurrentUser()?.getUid()
                databaseRef = FirebaseDatabase.getInstance().getReference()

                databaseRef.child("profile/${currentUser}/firstname").addValueEventListener(object :
                    ValueEventListener
                {
                    override fun onDataChange(snapshot: DataSnapshot) {

                    }
                    override fun onCancelled(error: DatabaseError) {}
                })
                sp = getSharedPreferences("APP_SETTINGS", Context.MODE_PRIVATE)
                editor = sp.edit()

                editor.putBoolean("is_logged", true).commit()

                val handler = Handler()
                handler.postDelayed({start()}, 1000)
            } else
                Toast.makeText(this, "Log In failed ", Toast.LENGTH_SHORT).show()
        }

    }
    fun start(){
        val intent = Intent(this, Lenta::class.java)
        startActivity(intent)
    }
}
