package com.example.myapplication1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.myapplication1.room_database.ToDo
import com.example.myapplication1.room_database.ToDoDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class NewTaskActivity : AppCompatActivity() {
    lateinit var editTextTitle : EditText
    lateinit var editTextTime : EditText
    lateinit var editTextPlace : EditText
    lateinit var editTextId : EditText
    lateinit var btsavetask : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)
        editTextTitle = findViewById(R.id.editTextTitle)
        editTextTime = findViewById(R.id.editTextTime)
        editTextPlace = findViewById(R.id.editTextPlace)
        editTextId = findViewById(R.id.editTextId2)
        btsavetask = findViewById(R.id.btsavetask) //validar que boton se programó
        if(this.intent.getStringExtra("id")!=null){
            editTextTitle.setText(this.intent.getStringExtra("tarea"))
            editTextTime.setText(this.intent.getStringExtra("hora"))
            editTextPlace.setText(this.intent.getStringExtra("lugar"))
            editTextId.setText(this.intent.getStringExtra("id"))
            btsavetask.setText("Edit Task")
        }
    }

    fun onSaveTask(view : View){
        var title: String = editTextTitle.text.toString()
        var time: String = editTextTime.text.toString()
        var place: String = editTextPlace.text.toString()
        var id: String = editTextId.text.toString()
        val db = ToDoDatabase.getDatabase(this)
        val todoDAO = db.todoDao()
        val dbFirebase = FirebaseFirestore.getInstance()
        val task = ToDo(id.toInt(),title,time,place)
        runBlocking {
                launch {
                    if(id.equals("0")) {
                        var result = todoDAO.insertTask(task)
                        if (result != 1L) {
                            dbFirebase.collection("ToDo").document(result.toString())
                                .set(
                                    hashMapOf(
                                        "title" to title,
                                        "time" to time,
                                        "place" to place
                                    )
                                )
                            setResult(Activity.RESULT_OK)
                            finish()
                    }
                }else{
                    todoDAO.updateTask(task)
                        dbFirebase.collection("ToDo").document(id)
                            .set(
                                hashMapOf(
                                    "title" to title,
                                    "time" to time,
                                    "place" to place
                                )
                            )
                        finish()
                }
            }
        }
        val principal = Intent(this,ToDoMainActivity2::class.java)
        startActivity(principal)
    }
}

