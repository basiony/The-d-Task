package com.example.thedtask

import androidx.room.Entity
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey


@Entity
class Item_Entity {

    @PrimaryKey(autoGenerate = true)
    var item_id : Int=0
    @ColumnInfo(name="ITEM_TITLE")
    var item_title : String=""

    @ColumnInfo(name="ITEM_PRICE")
    var item_price : Int=0

    @ColumnInfo(name="ITEM_DESCRIPTION")
    var item_description : String=""

}