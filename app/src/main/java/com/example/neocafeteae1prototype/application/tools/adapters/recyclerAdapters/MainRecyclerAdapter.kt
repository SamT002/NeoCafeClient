package com.example.neocafeteae1prototype.application.tools.adapters.recyclerAdapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.neocafeteae1prototype.R
import com.example.neocafeteae1prototype.application.tools.delegates.RecyclerItemClickListener
import com.example.neocafeteae1prototype.databinding.MapItemBinding
import com.example.neocafeteae1prototype.databinding.PopularItemBinding
import com.example.neocafeteae1prototype.domain.sealedClasses.AllModels
import com.example.neocafeteae1prototype.domain.sealedClasses.AllViewHolders

class MainRecyclerAdapter(val recyclerItemClick: RecyclerItemClickListener) : RecyclerView.Adapter<AllViewHolders>() {
    private var items = mutableListOf<AllModels>()
    fun setList(list: MutableList<AllModels>){
        items = list
        notifyDataSetChanged()
    }



    override fun getItemViewType(position: Int): Int {
        return when(items[position]){
            is AllModels.Popular -> R.layout.popular_item
            is AllModels.NeoCafePlace -> R.layout.map_item
            is AllModels.Menu -> super.getItemViewType(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllViewHolders {
        return when(viewType){
            R.layout.popular_item -> {
                AllViewHolders.PopularViewHolder(
                    PopularItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false))
            }
            R.layout.map_item -> AllViewHolders.NeoCafeViewHolder(MapItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false ))

            else -> throw IllegalArgumentException("Invalid Type from adapter")
        }
    }

    override fun onBindViewHolder(holder: AllViewHolders, position: Int) {
        return when(holder){
            is AllViewHolders.PopularViewHolder -> holder.bind(items[position] as AllModels.Popular)
            is AllViewHolders.NeoCafeViewHolder -> {
                holder.bind(items[position] as AllModels.NeoCafePlace)
                holder.itemView.setOnClickListener{
                    Log.i("TAG", "clicked_main")
                    recyclerItemClick.itemClicked(items[position])
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
class myormalName