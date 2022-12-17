package com.example.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {

    private lateinit var edtEmail: EditText
    private lateinit var edtPasword: EditText
    private lateinit var edtusername: EditText
    private lateinit var btnSignUp: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar?.hide()
        mAuth= FirebaseAuth.getInstance()
        edtEmail=findViewById(R.id.Email)
        edtPasword=findViewById(R.id.Pwd)
        edtusername=findViewById(R.id.UserName)
        btnSignUp=findViewById(R.id.btn_signUp)

        btnSignUp.setOnClickListener{
            val name=edtusername.text.toString()
            val email=edtEmail.text.toString()
            val pass=edtPasword.text.toString()

            signUp(name,email,pass)
        }

    }
    private fun signUp(name:String, email:String,pass:String){
        //logic for user SignUp
        mAuth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Code for jumping to home activity
                    addUserToDatabase(name,email, mAuth.currentUser?.uid!!)
                    val intent= Intent(this,MainActivity::class.java)
                    finish()
                    startActivity(intent)

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this,"Some Error occurred",Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun addUserToDatabase(name: String, email: String, uid: String) {
        mDbRef=FirebaseDatabase.getInstance().getReference()

        mDbRef.child("user").child(uid).setValue(User(name,email, uid))
    }
}