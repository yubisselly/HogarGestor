package com.example.myapplication1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

enum class ProviderType{
    BASIC,
    GOOGLE
}
class WelcomeActivity : AppCompatActivity() {
    private var textemail : TextView?=null
    private var textprovedor : TextView?=null
    private  var btnlogOut: Button?= null
    private var btnProducto: Button?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        textemail= findViewById(R.id.textemail)
        textprovedor= findViewById(R.id.textprovedor)
        val bundle = intent.extras
        val email= bundle?.getString("email")
        val provider = bundle?.getString("provider")
        cargardatos(email?:"",provider?:"")
        val prefs = getSharedPreferences(getString(R.string.prefs_file),Context.MODE_PRIVATE).edit()
        prefs.putString("email",email)
        prefs.putString("provider",provider)
        prefs.apply()

        btnlogOut=findViewById(R.id.btnlogOut)
        btnlogOut?.setOnClickListener {
            val prefs = getSharedPreferences(getString(R.string.prefs_file),Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()
            FirebaseAuth.getInstance().signOut()
            val intento = Intent(this,MainActivity::class.java)
            startActivity(intento)
         //   onBackPressed()
        }
        btnProducto=findViewById(R.id.btn_Productos)
        btnProducto?.setOnClickListener {
            val intent = Intent(this,ListaProductosActivity::class.java)
            startActivity(intent)
        }

        val fab: View=findViewById(R.id.fab)
       /* fab.setOnClickListener { view->
            Snackbar.make(view, R.string.txt_fab,Snackbar.LENGTH_SHORT).show()
        }*/
        fab.setOnClickListener { view->
            val intent = Intent(this,ToDoMainActivity2::class.java)
            startActivity(intent)


        }
    }
    private fun cargardatos(email: String, provider: String){
        title="Inicio"
        textemail!!.text=email
        textprovedor!!.text=provider
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val homeIntent = Intent(this,WelcomeActivity::class.java).apply {
            putExtra("email",textemail!!.text.toString())
            putExtra("provider",textprovedor!!.text.toString())
        }
        startActivity(homeIntent)
    }
}