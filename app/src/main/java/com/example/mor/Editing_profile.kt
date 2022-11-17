package com.example.mor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class Editing_profile : AppCompatActivity() {
    val currentUser = FirebaseAuth.getInstance().getCurrentUser()?.getUid()
    lateinit var databaseRef: DatabaseReference
    lateinit var EditTextname: EditText
    lateinit var EditTextlastname: EditText
    lateinit var EditTextage: EditText
    lateinit var EditTextCity: EditText
    lateinit var EditTextAddress: EditText
    private lateinit var Save_сhanges: TextView
    lateinit var back: TextView
    var database: FirebaseDatabase? = null
    var databaseReference3: DatabaseReference? = null
    var databaseReference4: DatabaseReference? = null
    var databaseReference5: DatabaseReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editing_profile)



        EditTextname = findViewById(R.id.EditTextname)
        EditTextlastname = findViewById(R.id.EditTextlastname)
        EditTextage = findViewById(R.id.EditTextage)
        Save_сhanges = findViewById(R.id.textView0)
        EditTextCity = findViewById(R.id.EditTextCity)
        EditTextAddress = findViewById(R.id.EditTextAddress)
        back = findViewById(R.id.textView1)



        databaseRef = FirebaseDatabase.getInstance().getReference()
        database = FirebaseDatabase.getInstance()
        databaseReference3 = database?.reference!!.child("Age")
        databaseReference4 = database?.reference!!.child("City")
        databaseReference5 = database?.reference!!.child("Address")


        databaseRef.child("profile/${currentUser}/firstname").addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                EditTextname.hint = ("Имя: ${snapshot.value}")
            }

            override fun onCancelled(error: DatabaseError) {}
        })
        databaseRef.child("profile/${currentUser}/lastname").addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                EditTextlastname.hint = ("Фамилия: ${snapshot.value}")
            }

            override fun onCancelled(error: DatabaseError) {}
        })
        if(EditTextage.hint == "Возраст:"){
            databaseRef.child("Age/${currentUser}/age").addValueEventListener(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    EditTextage.hint = ("Возраст: ${snapshot.value}")
                    if(EditTextage.hint == "Возраст: null"){
                        EditTextage.hint = "Возраст:"
                    }
                }

                override fun onCancelled(error: DatabaseError) {}
            })
        }else{
            EditTextage.hint = "Возраст:"
        }
        if(EditTextAddress.hint == "Адрес проживания:"){
            databaseRef.child("Address/${currentUser}/address").addValueEventListener(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    EditTextAddress.hint = ("Адрес проживания: ${snapshot.value}")
                    if(EditTextAddress.hint == "Адрес проживания: null"){
                        EditTextAddress.hint = "Адрес проживания:"
                    }
                }

                override fun onCancelled(error: DatabaseError) {}
            })
        }else{
            EditTextAddress.hint = "Адрес проживания:"
        }
        if(EditTextCity.hint == "Город проживания:"){
            databaseRef.child("City/${currentUser}/city").addValueEventListener(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    EditTextCity.hint = ("Город проживания: ${snapshot.value}")
                    if(EditTextCity.hint == "Город проживания: null"){
                        EditTextCity.hint = "Город проживания:"
                    }
                }

                override fun onCancelled(error: DatabaseError) {}
            })
        }else{
            EditTextCity.hint = "Город проживания:"
        }

        Save_сhanges.setOnClickListener{
            EditUser()
        }
        back.setOnClickListener{
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }
    }
    fun EditUser() {
        val Edittextname = EditTextname.text.toString()
        val Edittextlastname = EditTextlastname.text.toString()
        val Edittextage = EditTextage.text.toString()
        val Edittextcity = EditTextCity.text.toString()
        val Edittextaddress = EditTextAddress.text.toString()


        // check
        if (Edittextname.isBlank() && Edittextlastname.isBlank() && Edittextage.isBlank() && Edittextcity.isBlank() && Edittextaddress.isBlank()) {
            Toast.makeText(this, "Чтобы сохранить изменения должно что-то изменяться", Toast.LENGTH_SHORT).show()
            return
        }
        if (Edittextname.isNotBlank()) {
            databaseRef.child("profile/${currentUser}/firstname").setValue(EditTextname.text.toString())
        }
        if (Edittextlastname.isNotBlank()) {
            databaseRef.child("profile/${currentUser}/lastname").setValue(EditTextlastname.text.toString())
        }
        if (Edittextage.isNotBlank()) {
            databaseRef.child("Age/${currentUser}/age").setValue(EditTextage.text.toString())
        }
        if (Edittextcity.isNotBlank()) {
            databaseRef.child("City/${currentUser}/city").setValue(EditTextCity.text.toString())
        }
        if (Edittextaddress.isNotBlank()) {
            databaseRef.child("Address/${currentUser}/address").setValue(EditTextAddress.text.toString())
        }

        val intent = Intent(this, Profile::class.java)
        Toast.makeText(this, "Изменения сохранены", Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }
}