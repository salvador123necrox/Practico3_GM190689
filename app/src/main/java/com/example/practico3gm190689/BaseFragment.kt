package com.example.practico3gm190689

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practico3gm190689.databinding.FragmentBaseBinding
import com.example.practico3gm190689.models.Marcas

class BaseFragment : Fragment() {

    private var Marcas: Marcas? = null
    private var marcaAdapter: MarcaAdapter? = null

    private var model: Any? = null
    private var adapter: Any? = null
    private var activity: Any? = null

    private var _binding: FragmentBaseBinding? = null
    private val binding get() = _binding!!

    private var list: ArrayList<Any>? = null

    private var clase: String = ""

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
            val activity = Intent(context, AgregarMarcaActivity::class.java)
            startActivity(activity)
        }


        initRecyclerView()

        return view
    }

    private fun initRecyclerView(){
        binding.rvCardList.layoutManager = LinearLayoutManager(context)
        Marcas = Marcas(context)
        var cursor: Cursor? = Marcas!!.ObtenerMarcas()

        list = ArrayList()
        if(cursor != null && cursor.count > 0){
            cursor.moveToFirst()
            do {
                list!!.add(cursor.getString(1))
            } while ( cursor.moveToNext() )
        }

        //marcaAdapter = MarcaAdapter(list!!)

        binding.rvCardList.adapter = marcaAdapter

        Log.d("Data", list.toString())


    }

}