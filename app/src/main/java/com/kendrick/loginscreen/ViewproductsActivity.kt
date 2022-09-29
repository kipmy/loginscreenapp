package com.kendrick.loginscreen

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ViewproductsActivity : AppCompatActivity() {
    lateinit var mListProducts:ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewproducts)

        var products:ArrayList<Product> = ArrayList()
        var myAdapter = CustomAdapter(applicationContext,products)
        var progress = ProgressDialog(this)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
        mListProducts = findViewById(R.id.mListProducts)

        //Access the table in the database

        var my_db = FirebaseDatabase.getInstance().reference.child("Products")
        //Start retrieving data
        progress.show()
        my_db.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                //Get the data and put it on the arraylist users
                products.clear()
                for (snap in p0.children){
                    var product = snap.getValue(Product::class.java)
                    products.add(product!!)
                }
                //Notify the adapter that data has changed
                myAdapter.notifyDataSetChanged()
                progress.dismiss()
            }

            override fun onCancelled(p0: DatabaseError) {
                progress.dismiss()
                Toast.makeText(applicationContext,"DB Locked",Toast.LENGTH_LONG).show()
            }
        })

        mListProducts.adapter = myAdapter
    }
}