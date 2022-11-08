package com.example.myapplication1


import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.myapplication1.room_database.ToDo
import com.example.myapplication1.room_database.ToDoDatabase
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class DetailFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmento=inflater.inflate(R.layout.fragment_detail, container, false)
        var tarea=requireArguments().getString("tarea")
        var hora=requireArguments().getString("hora")
        var lugar=requireArguments().getString("lugar")
        var id=requireArguments().getString("id")
        var textViewtarea: TextView=fragmento.findViewById(R.id.tvTarea)
        var tvHora:TextView=fragmento.findViewById(R.id.tvHora)
        var tvLugar:TextView=fragmento.findViewById(R.id.tvLugar)
        var tvID: TextView=fragmento.findViewById(R.id.tvID)
        textViewtarea.text=tarea
        tvHora.text=hora
        tvLugar.text=lugar
        tvID.text=id

        val btnmodify : Button = fragmento.findViewById(R.id.btnmodify)
        btnmodify.setOnClickListener {
            val principal = Intent(inflater.context,NewTaskActivity::class.java)
            principal.putExtra("tarea",textViewtarea.text as String)
            principal.putExtra("hora",tvHora.text as String)
            principal.putExtra("lugar",tvLugar.text as String)
            principal.putExtra("id",tvID.text as String)
            startActivity(principal)
        }

        val btndelete : Button = fragmento.findViewById(R.id.btndelete)
        btndelete.setOnClickListener {
            val positiveButton = { dialogpositivo: DialogInterface, which: Int ->
                val db = ToDoDatabase.getDatabase(requireActivity())
                val todoDAO = db.todoDao()
                var idTask: String = tvID.text.toString()
                val task = ToDo(idTask.toInt(), tarea.toString(), hora.toString(), lugar.toString())
                runBlocking {
                    launch {
                        todoDAO.deleteTask(task)

                    }
                }
                val actividadtodoActiviti = Intent(inflater.context, ToDoMainActivity2::class.java)
                startActivity(actividadtodoActiviti)

            }
            val negativeButton = { _: DialogInterface, _: Int -> }
            val dialogpositivo = AlertDialog.Builder(requireActivity())
                .setTitle("¿Seguro que desea eliminar la tarea?")
                .setMessage(tarea.toString())
                .setPositiveButton("Si", positiveButton)
                .setNegativeButton("cancel", negativeButton)
                .create()
                .show()
        }
        return fragmento
    }
}
