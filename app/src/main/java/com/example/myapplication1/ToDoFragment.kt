package com.example.myapplication1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication1.room_database.ToDoDatabase
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ToDoFragment : Fragment() {
    private lateinit var listRecyclerView: RecyclerView
    private lateinit var myAdapter: RecyclerView.Adapter<MyTaskListAdapter.MyViewHolder>
    var myTaskTitles: ArrayList<String> = ArrayList()
    var myTaskTimes: ArrayList<String> = ArrayList()
    var myTaskPlaces: ArrayList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmento=inflater.inflate(R.layout.fragment_to_do,container,false)
/*        val detail1: Button=fragmento.findViewById(R.id.btn_detail_1)
        val detail2: Button=fragmento.findViewById(R.id.btn_detail_2)
        val detail3: Button=fragmento.findViewById(R.id.btn_detail_3)
        detail1.setOnClickListener {
            val datos = Bundle()
            datos.putString("tarea", "ir al super mercado")
            datos.putString("hora", "7:20")
            datos.putString("lugar", "superx")
            activity?.getSupportFragmentManager()?.beginTransaction()
                ?.setReorderingAllowed(true)
                ?.replace(R.id.fcv,DetailFragment::class.java,datos,"detail")
                ?.addToBackStack("")
                ?.commit()
        }*/

        return fragmento
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*myTaskTitles.add("Ir al super mercado")
        myTaskTitles.add("llevar el carro a la tecnomecanica")
        myTaskTitles.add("cita medica")
        myTaskTitles.add("tarea 4")

        myTaskTimes.add("10:08pm")
        myTaskTimes.add("12:00m")
        myTaskTimes.add("03:30pm")
        myTaskTimes.add("09:15am")

        myTaskPlaces.add("lugar 1")
        myTaskPlaces.add("lugar 2")
        myTaskPlaces.add("lugar 3")
        myTaskPlaces.add("lugar 4")

        var info : Bundle = Bundle()
        info.putStringArrayList("titles",myTaskTitles)
        info.putStringArrayList("times",myTaskTimes)
        info.putStringArrayList("places",myTaskPlaces)
        listRecyclerView = requireView().findViewById(R.id.recycleToDoList)
        myAdapter = MyTaskListAdapter(activity as AppCompatActivity,info)
        listRecyclerView.setHasFixedSize(true)
        listRecyclerView.adapter = myAdapter
        listRecyclerView.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))*/

        val fab : View = requireActivity().findViewById(R.id.fab2)
        fab.setOnClickListener { view->
            val intent =Intent(activity,NewTaskActivity::class.java)
            var recursiveScope = 0
            startActivityForResult(intent,recursiveScope)
        }
        var info : Bundle = Bundle()
        info.putStringArrayList("titles",myTaskTitles)
        info.putStringArrayList("times",myTaskTimes)
        info.putStringArrayList("places",myTaskPlaces)
        listRecyclerView = requireView().findViewById(R.id.recycleToDoList)
        myAdapter = MyTaskListAdapter(activity as AppCompatActivity,info)
        listRecyclerView.setHasFixedSize(true)
        listRecyclerView.adapter = myAdapter
        listRecyclerView.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
        updateList()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode==0){
            if(resultCode== Activity.RESULT_OK){
                updateList()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
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
        }
    }
}

