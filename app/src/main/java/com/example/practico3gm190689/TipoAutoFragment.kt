package com.example.practico3gm190689

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practico3gm190689.databinding.FragmentBaseBinding
import com.example.practico3gm190689.models.TipoAuto

class TipoAutoFragment : Fragment() {

    private var TipoAuto: TipoAuto? = null
    private var tipoAutoAdapter: TipoAutoAdapter? = null

    private var _binding: FragmentBaseBinding? = null
    private val binding get() = _binding!!

    private var list: MutableList<Any> = ArrayList()

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
            val activity = Intent(context, AgregarTipoAutoActivity::class.java)
            startActivity(activity)
        }

        initRecyclerView()

        return view
    }

    private fun initRecyclerView(){
        binding.rvCardList.layoutManager = LinearLayoutManager(context)
        TipoAuto = TipoAuto(context)
        var cursor: Cursor? = TipoAuto!!.ObtenerTipoAuto()

        list.clear()
        if(cursor != null && cursor.count > 0){
            cursor.moveToFirst()
            do {
                val tipoAuto = ArrayList<String>()
                tipoAuto.add(cursor.getString(0))
                tipoAuto.add(cursor.getString(1))
                list!!.add(tipoAuto)
            } while ( cursor.moveToNext() )
        }

        tipoAutoAdapter = TipoAutoAdapter(list!!){
            onItemClick(it[0], it[1])
        }

        binding.rvCardList.adapter = tipoAutoAdapter
    }

    private fun onItemClick(tipo: String, id: String) {
        if(tipo == "Editar"){
            editarTipoAuto(id)
        } else {
            eliminarTipoAutoMarca(id)
        }
    }

    private fun editarTipoAuto(id: String){
        val activity = Intent(context, AgregarTipoAutoActivity::class.java)
        activity.putExtra("idtipoauto",id.toInt())
        startActivity(activity)
    }

    private fun eliminarTipoAutoMarca(id: String){
        TipoAuto = TipoAuto(context)
        TipoAuto!!.EliminarTipoAuto(id.toInt())
        Toast.makeText(context, "Tipo de auto eliminado", Toast.LENGTH_LONG).show()
        initRecyclerView()
    }

}