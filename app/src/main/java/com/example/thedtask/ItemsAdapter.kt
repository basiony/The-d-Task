package com.example.thedtask


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_itemlist.view.*

// adapter responsible for viewing items in the recycler view
class ItemsAdapter(private val datalist:MutableList<Data>): RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>(){

    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        context = parent.context
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_itemlist,parent,false )
        )
    }

    override fun getItemCount()= datalist.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item= datalist[position]

        val itemName=holder.view.textViewTitle
        val itemPrice=holder.view.textViewPrice
        val itemImage=holder.view.imageView

        //load images with fixed dimensions
        Picasso.get()
            .load(item.image.link)
            .fit()
            .into(itemImage)
        val name= "${item.name}"
        itemName.text=name;



        val price='$'+"${item.price}"
        itemPrice.text=price.toString()

        val descr= "${item.productDescription}"
        val img="${item.image.link}"

        holder.view.setOnClickListener{

            val intent = Intent(context,Description::class.java)
            intent.putExtra("itemDescription",descr.toString())
            intent.putExtra("itemImage",img)
            startActivity(context,intent, Bundle())

        }



    }


    class ItemViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}