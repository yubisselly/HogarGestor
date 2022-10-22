package com.example.myapplication1

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity()
{
    private var edtUsername: EditText? = null
    private var edtPassword: TextInputEditText? = null
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toobar))
        edtUsername = findViewById(R.id.edtUsername)
        edtPassword = findViewById(R.id.edtPassword)
    }

    fun onlogin(botonLogin: View)
    {
        var messageerrorUsername= getString(R.string.messageerrorUsername)
        var username: String = edtUsername!!.text.toString()
        var userpassword: String = edtPassword!!.text.toString()

        if (username == "ejemplo@ejemplo.com")
        {
            if (userpassword == "123") {
                val positiveButton = { dialogpositivo: DialogInterface, which: Int ->
                    val intent = Intent(this,WelcomeActivity::class.java)
                    startActivity(intent)
                  //  val intent = Intent(this,ToDoMainActivity2::class.java)
                  //  startActivity(intent)

                }
                val negativeButton = {_:DialogInterface,_:Int->}
                val dialogpositivo = AlertDialog.Builder(this)
                    .setTitle("Welcome")
                    .setMessage("user:"+username)
                    .setPositiveButton("ok", positiveButton)
                    .setNegativeButton("cancel", negativeButton)
                    .create()
                    .show()
            }
            else {

                val dialog =
                    AlertDialog.Builder(this).setTitle("ERROR!").setMessage("password invalid")
                        .create().show()
                Toast.makeText(this, "Invalid password", Toast.LENGTH_SHORT).show()
            }
        }
        else{
            val dialog =
                AlertDialog.Builder(this).setTitle("ERROR!").setMessage(messageerrorUsername).create()
                    .show()
        }
    }

    fun onRegister(view: View) {
        val intent = Intent(this,RegisterActivity::class.java)
        startActivity(intent)
    }
}
