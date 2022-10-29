package com.example.myapplication1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {
    private var editName: EditText?=null
    private var editLastName: EditText?=null
    private var editNit: EditText?=null
    private var editPhone: EditText?=null
    private var editEmail: EditText?=null
    private var editPassword: EditText?=null
    private var checkBoxPolices: CheckBox?=null
   // private val editEmail_pattern: Pattern= Patterns.EMAIL_ADDRESS
    private val editEmail_patter: Pattern= Pattern.compile("[a-zA-Z0-9]*"+
            "@{1,1}"+
            "[a-zA-Z0-9]*"+
            "."+
            "[a-zA-Z]*"+
            "." +
            "[a-zA-Z]*")
    private val nameAndLast_Pattern: Pattern= Pattern.compile("[a-zA-Z]*")
    private val password_Pattern: Pattern= Pattern.compile(
        "^"+
            "(?=.*[0-9])"+
            "(?=.*[a-z])"+
            "(?=.*[A-Z])"+
            "(?=.*[@#$%^&+=.])"+
            "(?=\\S+$)"+
            ".{8,}"+
            "$"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        editName=findViewById(R.id.editName)
        editLastName=findViewById(R.id.editLastName)
        editNit=findViewById(R.id.editNit)
        editEmail=findViewById(R.id.editEmail)
        editPassword=findViewById(R.id.editPassword)
        editPhone=findViewById(R.id.editPhone)
        checkBoxPolices=findViewById(R.id.ChekboxPolicies)
    }

    fun onRegisterSave(view: View) {

       // var messageregistro= getString(R.string.messageerrorUsername)
       // var messagepolitica= getString(R.string.messageerrorUsername)
       // var messageerrorpassw= getString(R.string.messageerrorUsername)

        if(validateForm()){
            Toast.makeText(this,"Registro exitoso",Toast.LENGTH_LONG).show()

        }else{
            Toast.makeText(this,"Debe aceptar las políticas",Toast.LENGTH_LONG).show()
        }

    }
    private fun validateForm():Boolean{
        var validate= true

        var name: String=editName!!.text.toString()
        var lastname: String=editLastName!!.text.toString()
        var password: String=editPassword!!.text.toString()
        var email: String=editEmail!!.text.toString()

        if (!checkBoxPolices!!.isChecked) {
            validate=false
        }
        if (TextUtils.isEmpty(lastname)) {
            editLastName!!.error = "Required"
            validate = false
        }else if(!nameAndLast_Pattern.matcher(lastname.replace(" ","")).matches())
        {
            editLastName!!.error="name no valid"
            validate=false
        }else  editLastName!!.error=null

        if (TextUtils.isEmpty(name)) {
            editName!!.error = "Required"
            validate = false
        }else if(!nameAndLast_Pattern.matcher(name.replace(" ","")).matches())
        {
            editName!!.error="name no valid"
            validate=false
        }else  editName!!.error=null

        if (TextUtils.isEmpty(password))
        {
            editPassword!!.error = "Required"
            validate = false
        }else if(!password_Pattern.matcher(password).matches())
        {
            editPassword!!.error="password no valid, incluya un carácter especial que puede ser (@#\$%^&+=.) "
            validate=false
        }else  editPassword!!.error=null

        if (TextUtils.isEmpty(email))
            {
            editEmail!!.error = "Required"
                validate=false
            }else if (!editEmail_patter.matcher(email).matches())
        {
            editEmail!!.error="the '@' is required"
            validate=false
        }


            return validate
    }
}
