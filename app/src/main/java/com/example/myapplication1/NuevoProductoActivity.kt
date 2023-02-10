package com.example.myapplication1

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication1.room_database.AdminProducto.ImagenController
import com.example.myapplication1.room_database.AdminProducto.Producto
import com.example.myapplication1.room_database.AdminProducto.ProductoDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_nuevo_producto.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class NuevoProductoActivity : AppCompatActivity() {
    private val SELECT_ACTIVITY = 50
    private var imagenUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_producto)
        var idProducto : Int=0
        if(intent.hasExtra("producto")){
            val producto = intent.extras?.getSerializable("producto")as Producto
            editTextNombreANP.setText(producto.nombre)
            editTextPrecioANP.setText(producto.precio.toString())
            editTextDetalleANP.setText(producto.descripcion)
            idProducto = producto.idProducto
            val imageUri = ImagenController.getImagenUri(this,idProducto.toLong())
            imagenselectANP.setImageURI(imageUri)
        }
        val database = ProductoDatabase.getDatabase(this)
        val dbFirebase = FirebaseFirestore.getInstance()
        buttonANP.setOnClickListener {
            val nombre = editTextNombreANP.text.toString()
            val precio = editTextPrecioANP.text.toString().toFloat()
            val descripcion = editTextDetalleANP.text.toString()
            val producto = Producto(idProducto,nombre,precio,descripcion,R.drawable.ic_launcher_background)

            if(idProducto==0){
            CoroutineScope(Dispatchers.IO).launch {
                var result = database.productos().insert(producto)
                dbFirebase.collection("Productos").document(result.toString())
                    .set(
                        hashMapOf(
                            "nombre" to nombre,
                            "precio" to precio,
                            "descripcion" to descripcion
                        )
                    )
                imagenUri?.let {
                    ImagenController.saveImagen(this@NuevoProductoActivity,result,it)
                }
                this@NuevoProductoActivity.finish()
                }
            }else{
                CoroutineScope(Dispatchers.IO).launch {
                    database.productos().update(producto)
                    dbFirebase.collection("Productos").document(idProducto.toString())
                        .set(
                            hashMapOf(
                                "nombre" to nombre,
                                "precio" to precio,
                                "descripcion" to descripcion
                            )
                        )
                    imagenUri?.let {
                        ImagenController.saveImagen(this@NuevoProductoActivity,idProducto.toLong(),it)
                    }
                    this@NuevoProductoActivity.finish()
                }
                val principal = Intent(this,ListaProductosActivity::class.java)
                startActivity(principal)
            }
        }
        imagenselectANP.setOnClickListener {
            ImagenController.selectPhoneFromGallery(this,SELECT_ACTIVITY)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when{
            requestCode==SELECT_ACTIVITY && resultCode == Activity.RESULT_OK->{
                imagenUri = data!!.data
                imagenselectANP.setImageURI(imagenUri)
            }
        }
    }
}