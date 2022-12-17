package com.example.chatapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var edtEmail: EditText
    private lateinit var edtPasword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnSignUp:Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        mAuth= FirebaseAuth.getInstance()

        edtEmail=findViewById(R.id.Email)
        edtPasword=findViewById(R.id.Pwd)
        btnLogin=findViewById(R.id.btn_login)
        btnSignUp=findViewById(R.id.btn_signUp)

        btnSignUp.setOnClickListener{
            val intent=Intent(this,SignUp::class.java)
            finish()
            startActivity(intent)
        }

        btnLogin.setOnClickListener{
            val email = edtEmail.text.toString()
            val pass= edtPasword.text.toString()

            login(email,pass)
        }

    }



    private fun login(email: String,pass: String){
        //logic for login of user
        mAuth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Code for logging in user
                    val intent=Intent(this,MainActivity::class.java)
                    startActivity(intent)
                } else {
                    //if Login fails
                    Toast.makeText(this,"User does not exist",Toast.LENGTH_SHORT).show()
                }
            }
    }
}