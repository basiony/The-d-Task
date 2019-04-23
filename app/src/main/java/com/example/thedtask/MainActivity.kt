package com.example.thedtask

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val itemList: MutableList<Data> = mutableListOf()


    private lateinit var myAdapter: ItemsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setup Adapter
        myAdapter= ItemsAdapter(itemList)


        //setup recyclerView
        recyclerViewitems.layoutManager= LinearLayoutManager(this);
        recyclerViewitems.adapter = myAdapter


        //database instance
        var db = Room.databaseBuilder(applicationContext,AppDB::class.java,"itemDB").build()


        fun task() {

            //checking internet connectivity
            val cm= baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = cm.activeNetworkInfo

            //if there is no internet connection, will retrieve item's data from database
            if (networkInfo == null) {
                Toast.makeText(this, "You are not connected to internet", Toast.LENGTH_LONG).show()

                /*Thread {
                var item = Item_Entity()

                db.itemDAO().readItem().forEach{


                }


            }.start()*/


                //get data from cache if there is no network

                AndroidNetworking.initialize(this)
                AndroidNetworking.get("http://limitless-forest-98976.herokuapp.com/").getResponseOnlyIfCached().build()
                    .getAsObject(
                        ItemDetails::class.java,
                        object : ParsedRequestListener<ItemDetails> {
                            override fun onResponse(response: ItemDetails) {
                                itemList.addAll(response.data)
                                myAdapter.notifyDataSetChanged()
                            }


                            override fun onError(anError: ANError?) {

                            }


                        }


                    )


            } else {

                //get data from server if the device is connected to the internet

                AndroidNetworking.initialize(this)
                AndroidNetworking.get("http://limitless-forest-98976.herokuapp.com/").build()
                    .getAsObject(
                        ItemDetails::class.java,
                        object : ParsedRequestListener<ItemDetails> {
                            override fun onResponse(response: ItemDetails) {
                                itemList.addAll(response.data)
                                myAdapter.notifyDataSetChanged()


                                //saving in database using room library. I tried to save images in it but i found that it is not recommended, so i used the AndroidNetworking library method for caching (getResponseOnlyIfCached())


                                Thread {
                                    var item = Item_Entity()


                                    //saving each item in the Item_Entity table

                                    itemList.forEach {

                                        item.item_title = it.name
                                        item.item_price = it.price
                                        item.item_description = it.productDescription



                                        db.itemDAO().saveItem(item)

                                    }


                                    //testing that items are saved successfully

                                    db.itemDAO().readItem().forEach {
                                        Log.i("ayhaga", "id:${it.item_id}")
                                        Log.i("ayhaga", "title: ${it.item_title}")
                                        Log.i("ayhaga", "title: ${it.item_price}")
                                        Log.i("ayhaga", "desc: ${it.item_description}")

                                    }


                                }.start()


                            }


                            override fun onError(anError: ANError?) {

                            }


                        }


                    )

            }
        }






        task()


        // handling the SwipeRefreshLayout
        val refresh=findViewById<SwipeRefreshLayout>(R.id.refreshLayout)
        refresh.setOnRefreshListener {
            Handler(Looper.getMainLooper()).postDelayed(
                {
                    itemList.removeAll { true }
                    task()
                    myAdapter.notifyDataSetChanged()
                    refresh.isRefreshing=false

                },3000
            )
        }


    }
}




