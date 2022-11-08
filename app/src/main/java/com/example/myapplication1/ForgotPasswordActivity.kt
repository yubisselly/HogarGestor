package com.example.myapplication1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {
    private var editEmailForget : EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        editEmailForget= findViewById(R.id.editEmailForget)
    }
    fun Toforget(view : View){
        FirebaseAuth.getInstance().sendPasswordResetEmail(
            editEmailForget!!.text.toString()
        ).addOnCompleteListener {
            if(it.isSuccessful){
                Toast.makeText(this,"Correo enviado",Toast.LENGTH_LONG).show()
                val intent = Intent (this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this,"Correo no válido",Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent (this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}