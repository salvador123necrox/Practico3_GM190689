package com.example.practico3gm190689

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practico3gm190689.databinding.FragmentBaseBinding
import com.example.practico3gm190689.models.Automoviles
import com.example.practico3gm190689.models.Marcas

class AutosFragment : Fragment() {

    private var autoModelo: Automoviles? = null
    private var autoAdapter: AutoMovilAdapter? = null
    private var _binding: FragmentBaseBinding? = null
    private val binding get() = _binding!!
    private var lista: MutableList<Any> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBaseBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.fabAgregar.setOnClickListener {
            val activity = Intent(context, AgregarAutoActivity::class.java)
            startActivity(activity)
        }
        initRecyclerView()
        return view
    }

    private fun initRecyclerView(){
        binding.rvCardList.layoutManager = LinearLayoutManager(context)
        autoModelo = Automoviles(context)
        var cursor: Cursor? = autoModelo!!.ObtenerAutos()
        lista.clear()
        var marcaModel: Marcas = Marcas(context)
        if(cursor != null && cursor.count > 0){
            cursor.moveToFirst()
            do {
                var car = ArrayList<String>()
                car.add(marcaModel.searchNombre(cursor.getString(1).toInt()).toString())
                car.add(cursor.getString(2))
                car.add(cursor.getString(3))
                lista!!.add(car)
            } while ( cursor.moveToNext() )
        }
        autoAdapter = AutoMovilAdapter(lista!!){
            onItemClick(it[0], it[1])
        }
        binding.rvCardList.adapter = autoAdapter
    }

    private fun onItemClick(tipo: String, id: String) {
        if(tipo == "Editar"){
            editarAuto(id)
        } else {
            eliminarAuto(id)
        }
    }

    private fun editarAuto(id: String){
        val activity = Intent(context, AgregarMarcaActivity::class.java)
        activity.putExtra("idauto",id.toInt())
        startActivity(activity)
    }

    private fun eliminarAuto(id: String){
        autoModelo = Automoviles(context)
        autoModelo!!.EliminarAuto(id.toInt())
        Toast.makeText(context, "Auto eliminado", Toast.LENGTH_LONG).show()
        initRecyclerView()
    }

}