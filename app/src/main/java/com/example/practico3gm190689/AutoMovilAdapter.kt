package com.example.practico3gm190689

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practico3gm190689.databinding.ItemAutoBinding

class AutoMovilAdapter(private var lista: List<Any>, private var onClickListener: (ArrayList<String>) -> Unit
    ): RecyclerView.Adapter<AutoMovilAdapter.AutoMovilViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AutoMovilViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AutoMovilViewHolder(layoutInflater.inflate(R.layout.item_auto, parent, false))
    }

    override fun getItemCount(): Int = lista.size

    override fun onBindViewHolder(holder: AutoMovilViewHolder, position: Int) {
        val auto = lista[position]
        holder.render(auto as ArrayList<String>, onClickListener)
    }

    class AutoMovilViewHolder (view: View): RecyclerView.ViewHolder(view) {
        private val binding = ItemAutoBinding.bind(view)

        fun render(data: ArrayList<String>, onClickListener: (ArrayList<String>) -> Unit){
            binding.tvMarca.text = data[0]
            binding.tvModelo.text = data[1]
            binding.tvPrecio.text = "$ "+data[2]
            binding.editar.setOnClickListener {
                var info = ArrayList<String>()
                info.add("Editar")
                info.add(data[0])
                onClickListener(info)
            }
            binding.btnDelete.setOnClickListener {
                var info = ArrayList<String>()
                info.add("Eliminar")
                info.add(data[0])
                onClickListener(info)
            }
        }
    }
}