package com.example.myapplication1

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.myapplication1.room_database.AdminProducto.ImagenController
import com.example.myapplication1.room_database.AdminProducto.Producto
import com.example.myapplication1.room_database.AdminProducto.ProductoDatabase
import com.example.myapplication1.room_database.ToDoDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_producto.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_producto)
        val producto = intent.getSerializableExtra("producto") as Producto
        val imageUri = ImagenController.getImagenUri(this,producto.idProducto.toLong())
        imagenViewAP.setImageURI(imageUri)

        textView_NombrePA.text=producto.nombre
        textView_PrecioPA.text="$${producto.precio}"
        textView_DetallePA.text=producto.descripcion
      //  imagenViewAP.setImageResource(producto.imagen)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.producto_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val producto = intent.getSerializableExtra("producto") as Producto
        val database = ProductoDatabase.getDatabase(this)
        val dbFirebase = FirebaseFirestore.getInstance()

        when(item.itemId){
            R.id.edit_item->{
                val producto = intent.getSerializableExtra("producto") as Producto
                val intent = Intent(this, NuevoProductoActivity::class.java)
                intent.putExtra("producto",producto)
                startActivity(intent)
            }
            R.id.delete_item->{
                CoroutineScope(Dispatchers.IO).launch {
                   database.productos().delete(producto)
                    dbFirebase.collection("Productos")
                        .document(producto.idProducto.toString()).delete()
//                    val positiveButton = { dialogpositivo: DialogInterface, which: Int -> val db = ToDoDatabase.getDatabase(requireActivity())

                        val database = Firebase.database
                        val myRef = database.getReference("productos")
                        val Folder: StorageReference = FirebaseStorage.getInstance()
                            .getReference()
                            .child("imagen")
                        val file_nae = Folder.child(producto.idProducto.toString()).delete()
                   // }

/*                    val negativeButton = { _: DialogInterface, _: Int -> }
                    val dialogpositivo = AlertDialog.Builder(requireActivity())
                        .setTitle("¿Seguro que desea eliminar la tarea?")
                        .setMessage(producto.idProducto.toString())
                        .setPositiveButton("Si", positiveButton)
                        .setNegativeButton("cancel", negativeButton)
                        .create()
                        .show()*/

                    this@ProductoActivity.finish()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
/*    fun onEditarP(view: View){
        val producto = intent.getSerializableExtra("producto") as Producto
        val intent = Intent(this, NuevoProductoActivity::class.java)
        intent.putExtra("producto",producto)
        startActivity(intent)
    }*/
}