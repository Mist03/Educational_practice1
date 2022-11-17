package com.example.mor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase


class Reg : AppCompatActivity() {

    lateinit var etEmail: EditText
    lateinit var etConfPass: EditText
    private lateinit var etPass: EditText
    lateinit var firstname: EditText
    lateinit var lastname: EditText
    private lateinit var btnSignUp: Button
    lateinit var tvRedirectLogin: Button

    private lateinit var auth: FirebaseAuth
    var databaseReference1: DatabaseReference? = null
    var databaseReference2: DatabaseReference? = null
    var database: FirebaseDatabase? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)


        // View Bindings
        firstname = findViewById(R.id.editTextNameSignUp)
        lastname = findViewById(R.id.editTextSecondNameSignUp)
        etEmail = findViewById(R.id.editTextEmailSignUp)
        etConfPass = findViewById(R.id.editTextRepeatPasswordSignUp)
        etPass = findViewById(R.id.editTextPasswordSignUp)
        btnSignUp = findViewById(R.id.btnRegistationSignUp)
        tvRedirectLogin = findViewById(R.id.tvRedirectLogin)

        // Initialising auth object
        auth = Firebase.auth
        database = FirebaseDatabase.getInstance()
        databaseReference1 = database?.reference!!.child("profile")
        databaseReference2 = database?.reference!!.child("Personal_data")

        btnSignUp.setOnClickListener {
            signUpUser()
        }

        // switching from signUp Activity to Login Activity
        tvRedirectLogin.setOnClickListener {
            val intent = Intent(this, Log::class.java)
            startActivity(intent)
        }

    }

    private fun signUpUser() {
        val firstName = firstname.text.toString()
        val lastName = lastname.text.toString()
        val email = etEmail.text.toString()
        val pass = etPass.text.toString()
        val confirmPassword = etConfPass.text.toString()

        // check pass
        if (email.isBlank() || pass.isBlank() || confirmPassword.isBlank() || firstName.isBlank() || lastName.isBlank()) {
            Toast.makeText(this, "Email and Password and Name can't be blank", Toast.LENGTH_SHORT).show()
            return
        }
        if (pass != confirmPassword) {
            Toast.makeText(this, "Password and Confirm Password do not match", Toast.LENGTH_SHORT)
                .show()
            return
        }
        // If all credential are correct
        // We call createUserWithEmailAndPassword
        // using auth object and pass the
        // email and pass in it.
        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener() {
            if (it.isSuccessful) {

                val currentUser = auth.currentUser
                val currentUSerDB1 = databaseReference1?.child(currentUser?.uid!!)
                val currentUSerDB2 = databaseReference2?.child(currentUser?.uid!!)
                currentUSerDB1?.child("email")?.setValue(email)
                currentUSerDB1?.child("password")?.setValue(pass)
                currentUSerDB1?.child("firstname")?.setValue(firstname.text.toString())
                currentUSerDB1?.child("lastname")?.setValue(lastname.text.toString())
                currentUSerDB2?.child("firstname")?.setValue(firstname.text.toString())
                currentUSerDB2?.child("lastname")?.setValue(lastname.text.toString())


                Toast.makeText(this, "Successfully Singed Up", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, Log::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Singed Up Failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}