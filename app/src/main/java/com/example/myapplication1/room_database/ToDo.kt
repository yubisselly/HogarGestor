package com.example.myapplication1.room_database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToDo(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val title : String,
    val time : String,
    val place : String
)

@Entity
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val name : String,
    val description : String,
    val price : Int,
    val image : String
)