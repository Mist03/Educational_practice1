package com.example.mor


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class Profile : AppCompatActivity() {
    lateinit var textview: TextView
    lateinit var textview4: TextView
    lateinit var textview5: TextView
    lateinit var textview6: TextView
    lateinit var textview7: TextView
    lateinit var textview8: TextView
    lateinit var databaseRef: DatabaseReference
    lateinit var textviewEdit: TextView
    lateinit var textViewExit: TextView
    lateinit var TextCity: TextView
    lateinit var TextAddress: TextView
    lateinit var back:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val currentUser = FirebaseAuth.getInstance().getCurrentUser()?.getUid()

        textview = findViewById(R.id.textView3)
        textview4 = findViewById(R.id.textView4)
        textview5 = findViewById(R.id.textView5)
        textview6 = findViewById(R.id.textView6)
        textview7 = findViewById(R.id.textView7)
        textview8 = findViewById(R.id.textView8)
        textViewExit = findViewById(R.id.textViewExit)
        textviewEdit = findViewById(R.id.textView9)
        TextCity = findViewById(R.id.TextCity)
        TextAddress = findViewById(R.id.TextAddress)

        back = findViewById(R.id.textView10)
        databaseRef = FirebaseDatabase.getInstance().getReference()

        databaseRef.child("profile/${currentUser}/firstname").addValueEventListener(object :
            ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot) {
                textview.text = ("${snapshot.value}")
                textview4.text = ("Имя: ${snapshot.value}")
            }
            override fun onCancelled(error: DatabaseError) {}
        })
        databaseRef.child("profile/${currentUser}/lastname").addValueEventListener(object :
            ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot) {
                textview5.text = ("Фамилия: ${snapshot.value}")
            }
            override fun onCancelled(error: DatabaseError) {}
        })
        databaseRef.child("profile/${currentUser}/email").addValueEventListener(object :
            ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot) {
                textview6.text = ("Email: ${snapshot.value}")
            }
            override fun onCancelled(error: DatabaseError) {}
        })
        databaseRef.child("profile/${currentUser}/password").addValueEventListener(object :
            ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot) {
                textview7.text = ("Пароль: ${snapshot.value}")
            }
            override fun onCancelled(error: DatabaseError) {}
        })
        databaseRef.child("Age/${currentUser}/age").addValueEventListener(object :
            ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot) {
                textview8.text = ("Возраст: ${snapshot.value}")
                if (textview8.text == "Возраст: null" ){
                    textview8.text = "Возраст:"
                }

            }
            override fun onCancelled(error: DatabaseError) {}
        })
        databaseRef.child("City/${currentUser}/city").addValueEventListener(object :
            ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot) {
                TextCity.text = ("Город проживания: ${snapshot.value}")
                if (TextCity.text == "Город проживания: null" ){
                    TextCity.text = "Город проживания:"
                }
            }
            override fun onCancelled(error: DatabaseError) {}
        })
        databaseRef.child("Address/${currentUser}/address").addValueEventListener(object :
            ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot) {
                TextAddress.text = ("Адрес проживания: ${snapshot.value}")
                if (TextAddress.text == "Адрес проживания: null" ){
                    TextAddress.text = "Адрес проживания:"
                }
            }
            override fun onCancelled(error: DatabaseError) {}
        })


        textViewExit.setOnClickListener {
            val intent = Intent(this, Prolog2::class.java)
            intent.putExtra("Exit", "1")
            startActivity(intent)
        }
        textviewEdit.setOnClickListener {
            val intent = Intent(this, Editing_profile::class.java)
            startActivity(intent)
        }
        back.setOnClickListener {
            val intent = Intent(this, Lenta::class.java)
            startActivity(intent)
        }
    }
}

