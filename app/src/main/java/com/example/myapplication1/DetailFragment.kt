package com.example.myapplication1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

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
        var textViewtarea: TextView=fragmento.findViewById(R.id.tvTarea)
        var tvHora:TextView=fragmento.findViewById(R.id.tvHora)
        var tvLugar:TextView=fragmento.findViewById(R.id.tvLugar)
        textViewtarea.text=tarea
        tvHora.text=hora
        tvLugar.text=lugar
        return fragmento
    }
}