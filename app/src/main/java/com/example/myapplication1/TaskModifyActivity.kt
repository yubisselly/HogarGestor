package com.example.myapplication1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.myapplication1.room_database.ToDo
import com.example.myapplication1.room_database.ToDoDatabase
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class TaskModifyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_modify)

/*        val fab : View = requireActivity().findViewById(R.id.btnmodify)
        fab.setOnClickListener { view->
            val intent =Intent(activity,TaskModifyActivity::class.java)*/

        /*modifyeditTextTitle = findViewById(R.id.modifyTextTitle)
        modifyeditTextTime = findViewById(R.id.modifyeditTextTime)
        modifyeditTextPlace = findViewById(R.id.modifyeditTextPlace)
            }

    fun onModifyTask(view : View) {
        var title: String = modifyeditTextTitle.text.toString()
        var time: String = modifyeditTextTime.text.toString()
        var place: String = modifyeditTextPlace.text.toString()
        val db = ToDoDatabase.getDatabase(this)
        val todoDAO = db.todoDao()
        val task = ToDo(0, title, time, place)
        runBlocking {
            launch {
                var result = todoDAO.insertTask(task)
                if (result != 1L) {
                    setResult(Activity.RESULT_OK)
                    finish()
                }
            }
        }
    }

        fun updateList(){
            val db = ToDoDatabase.getDatabase(requireActivity())
            val toDoDAD = db.todoDao()
            runBlocking {
                launch {
                    var result = toDoDAD.getAllTasks()
                    var i=0
                    myTaskTitles.clear()
                    myTaskTimes.clear()
                    myTaskPlaces.clear()
                    while (i<result.size){
                        myTaskTitles.add(result[i].title.toString())
                        myTaskTimes.add(result[i].time.toString())
                        myTaskPlaces.add(result[i].place.toString())
                        i++
                    }
                    myAdapter.notifyDataSetChanged()

                }
            }*/
        }
}