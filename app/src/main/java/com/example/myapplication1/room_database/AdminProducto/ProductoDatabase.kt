package com.example.myapplication1.room_database.AdminProducto



import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Producto::class], version = 1)
abstract class ProductoDatabase: RoomDatabase() {
    abstract fun productos(): ProductoDao
    companion object{
        @Volatile
        private var INSTANCE : ProductoDatabase?=null
        fun getDatabase(context: Context): ProductoDatabase{
            val tempIntance = INSTANCE
            if(tempIntance!=null){
                return tempIntance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                    ProductoDatabase::class.java,
                    "Producto_database").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}