package com.example.myapplication1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.myapplication1.room_database.AdminProducto.Producto
import com.example.myapplication1.room_database.AdminProducto.ProductoAdactar
import com.example.myapplication1.room_database.AdminProducto.ProductoDatabase
import kotlinx.android.synthetic.main.activity_lista_productos.*

class ListaProductosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_productos)
        //val producto =  Producto(1,"pet",1550.0F,"Pet tipo 4",R.drawable.ic_baseline_battery_full_24)
        //val producto2 =  Producto(2,"vidrio",2050.0F,"botella 250ml",R.drawable.ic_baseline_delete_outline_24)
        //val listaProductos= listOf(producto,producto2)
        var listaProductos= emptyList<Producto>()
        val database = ProductoDatabase.getDatabase(this)
        database.productos().getAll().observe(
            this, Observer { listaProductos = it
                val adactar = ProductoAdactar(this,listaProductos)
            lista.adapter = adactar
            }
        )
        //val adactar = ProductoAdactar(this,listaProductos)
        //lista.adapter = adactar
        lista.setOnItemClickListener() { parent, view, position, id ->
            val intent = Intent(this, ProductoActivity::class.java)
            intent.putExtra("producto", listaProductos[position])
            startActivity(intent)
        }
        floatingacgtionbittonLPA.setOnClickListener {
            val intent = Intent(this, NuevoProductoActivity::class.java)
            startActivity(intent)
        }
    }
}