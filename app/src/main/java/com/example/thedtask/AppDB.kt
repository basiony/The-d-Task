package com.example.thedtask

import androidx.room.Database
import androidx.room.RoomDatabase

@Database (entities = [(Item_Entity::class)],version = 1)
abstract class AppDB : RoomDatabase() {

   abstract fun itemDAO() : Item_DAO
}