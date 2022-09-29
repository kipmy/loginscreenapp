package com.kendrick.loginscreen

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class AddproductActivity : AppCompatActivity() {
    lateinit var pName:EditText
    lateinit var pQtty:EditText
    lateinit var pPrice:EditText
    lateinit var submit:Button
    lateinit var products:Button
    lateinit var progress:ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addproduct)
        pName = findViewById(R.id.mEdtName)
        pQtty = findViewById(R.id.mEdtQtty)
        pPrice = findViewById(R.id.mEdtPrice)
        submit = findViewById(R.id.submit)
        products = findViewById(R.id.products)
        progress = ProgressDialog(this)
        progress.setTitle("Submitting")
        progress.setMessage("Please wait...")
        submit.setOnClickListener {
            val productName = pName.text.toString().trim()
            val productQtty = pQtty.text.toString().trim()
            val productPrice = pPrice.text.toString().trim()
            val time = System.currentTimeMillis().toString()
            if(productName.isEmpty() || productPrice.isEmpty() || productQtty.isEmpty()){
                Toast.makeText(applicationContext,"Please fill all inputs", Toast.LENGTH_SHORT).show()
            }else{
                var ref = FirebaseDatabase.getInstance().getReference().child("Products/$time")
                progress.show()
                ref.setValue(Product(productName,productQtty,productPrice,time)).addOnCompleteListener {
                    task->
                    progress.dismiss()
                    if (task.isSuccessful){
                        Toast.makeText(applicationContext,"Product added successfully", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(applicationContext,"Product adding failed", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        products.setOnClickListener {
            startActivity(Intent(applicationContext,ViewproductsActivity::class.java))
        }
    }
}