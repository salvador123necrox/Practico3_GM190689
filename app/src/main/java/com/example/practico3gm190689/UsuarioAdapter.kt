package com.example.practico3gm190689

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practico3gm190689.databinding.ItemPrincipalBinding

class UsuarioAdapter(private var lista: List<Any>): RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return UsuarioViewHolder(layoutInflater.inflate(R.layout.item_principal, parent, false))
    }

    override fun getItemCount(): Int = lista.size

    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        val marca = lista[position]
        holder.render(marca as ArrayList<String>)
    }

    class UsuarioViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = ItemPrincipalBinding.bind(view)

        fun render(data: ArrayList<String>){
            binding.tvDataName.text = data[1] + " " + data[2]
        }
    }
}