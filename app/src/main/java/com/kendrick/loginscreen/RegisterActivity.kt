package com.kendrick.loginscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var register: Button
    lateinit var mAuth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        email=findViewById(R.id.Email)
        password=findViewById(R.id.password)
        register=findViewById(R.id.register)
        mAuth = FirebaseAuth.getInstance()

        register.setOnClickListener {

            val email=email.text.toString().trim()
            val password=password.text.toString().trim()
            if (email.isEmpty() || password.isEmpty()){
                Toast.makeText(applicationContext,"Please fill all inputs", Toast.LENGTH_SHORT).show()
            }else{
               mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                   task->
                   if (task.isSuccessful){
                       mAuth.signOut()
                       startActivity(Intent(applicationContext,MainActivity::class.java))
                       Toast.makeText(applicationContext,"Register successful", Toast.LENGTH_SHORT).show()
                       finish()
                   }
               }
            }

        }
    }
}
