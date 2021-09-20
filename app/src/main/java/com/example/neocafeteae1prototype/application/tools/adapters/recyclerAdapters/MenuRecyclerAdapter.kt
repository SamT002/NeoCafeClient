package com.example.neocafeteae1prototype.application.tools.adapters.recyclerAdapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.neocafeteae1prototype.databinding.MenuItemBinding
import com.example.neocafeteae1prototype.domain.sealedClasses.AllModels
import com.squareup.picasso.Picasso

class MenuRecyclerAdapter : RecyclerView.Adapter<MenuRecyclerAdapter.ViewHolder>() {

    private var list = mutableListOf<AllModels.Menu>()

    fun setList(list: MutableList<AllModels.Menu>){
        this.list = list
    }


    inner class ViewHolder(val binding:MenuItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = MenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(list[position]){
                binding.menuName.text = this.name

                Picasso.with(binding.menuImage.context)
                    .load(this.image)
                    .into(binding.menuImage)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}