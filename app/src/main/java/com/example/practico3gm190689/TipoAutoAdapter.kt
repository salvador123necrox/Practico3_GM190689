package com.example.practico3gm190689

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practico3gm190689.databinding.ItemPrincipalBinding

class TipoAutoAdapter(private var lista: List<Any>, private var onClickListener: (ArrayList<String>) -> Unit
    ): RecyclerView.Adapter<TipoAutoAdapter.TipoAutoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipoAutoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TipoAutoViewHolder(layoutInflater.inflate(R.layout.item_principal, parent, false))
    }

    override fun getItemCount(): Int = lista.size

    override fun onBindViewHolder(holder: TipoAutoViewHolder, position: Int) {
        val tipoAuto = lista[position]
        holder.render(tipoAuto as ArrayList<String>, onClickListener)
    }

    class TipoAutoViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = ItemPrincipalBinding.bind(view)

        fun render(data: ArrayList<String>, onClickListener: (ArrayList<String>) -> Unit){
            binding.tvDataName.text = data[1]
            binding.editar.setOnClickListener {
                var info = ArrayList<String>()
                info.add("Editar")
                info.add(data[0])
                onClickListener(info)
            }
            binding.eliminar.setOnClickListener {
                var info = ArrayList<String>()
                info.add("Eliminar")
                info.add(data[0])
                onClickListener(info)
            }
        }
    }
}