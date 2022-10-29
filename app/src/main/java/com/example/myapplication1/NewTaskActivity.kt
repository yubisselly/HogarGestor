package com.example.myapplication1

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.example.myapplication1.room_database.ToDo
import com.example.myapplication1.room_database.ToDoDatabase
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class NewTaskActivity : AppCompatActivity() {
    lateinit var editTextTitle : EditText
    lateinit var editTextTime : EditText
    lateinit var editTextPlace : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)
        editTextTitle = findViewById(R.id.editTextTitle)
        editTextTime = findViewById(R.id.editTextTime)
        editTextPlace = findViewById(R.id.editTextPlace)
    }

    fun onSaveTask(view : View){
        var title: String = editTextTitle.text.toString()
        var time: String = editTextTime.text.toString()
        var place: String = editTextPlace.text.toString()
        val db = ToDoDatabase.getDatabase(this)
        val todoDAO = db.todoDao()
        val task = ToDo(0,title,time,place)
        runBlocking {
            launch {
                var result = todoDAO.insertTask(task)
                if(result!=1L){
                    setResult(Activity.RESULT_OK)
                    finish()
                }
            }
        }
    }
}