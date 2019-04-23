package com.example.thedtask.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Item_DAO {

    @Insert
    fun saveItem(item : Item_Entity)

    @Query("SELECT * FROM Item_Entity")
    fun readItem(): List<Item_Entity>
}