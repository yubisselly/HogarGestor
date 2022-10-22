package com.example.myapplication1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class ToDoFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmento=inflater.inflate(R.layout.fragment_to_do,container,false)
        val detail1: Button=fragmento.findViewById(R.id.btn_detail_1)
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
        }
        return fragmento
    }
}

