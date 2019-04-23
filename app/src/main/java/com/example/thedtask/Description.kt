package com.example.thedtask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class Description : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)

        //get description and image from previous activity and view it in the current one
        val textView=findViewById<TextView>(R.id.textViewDescription)
        val itemImage=findViewById<ImageView>(R.id.imageViewItem)
        val  description= intent.getStringExtra("itemDescription")
        val image=intent.getStringExtra("itemImage")
        textView.text=description.toString()

        Picasso.get()
            .load(image)
            .fit()
            .into(itemImage)

    }
}
