package com.example.neocafeteae1prototype.application.tools.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.neocafeteae1prototype.application.tools.delegates.SecondItemClickListener
import com.example.neocafeteae1prototype.databinding.MenuItemBinding
import com.example.neocafeteae1prototype.databinding.PopularItemBinding
import com.example.neocafeteae1prototype.domain.sealedClasses.AllModels
import com.squareup.picasso.Picasso

class ShoppingRecyclerAdapter(val clicker:SecondItemClickListener?) : RecyclerView.Adapter<ShoppingRecyclerAdapter.ViewHolder>() {

    private var list = mutableListOf<AllModels.Popular>()

    fun setList(list: MutableList<AllModels.Popular>) {
        this.list = list
    }


    inner class ViewHolder(val binding: PopularItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = PopularItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding
        with(list[position]) {
            Picasso.with(binding.foodImage.context)
                .load(image)
                .into(binding.foodImage)

            binding.foodName.text = name
            binding.foodPrice.text = price

            if (county > 0) {
                binding.county.apply {
                    visibility = View.VISIBLE
                    text = county.toString()
                }
                binding.minus.visibility = View.VISIBLE
            }else{
                binding.county.visibility = View.GONE
                binding.minus.visibility = View.GONE
            }

            binding.plus.setOnClickListener {
                county+=1
                notifyItemChanged(position)
            }
            binding.minus.setOnClickListener {
                county-=1
                notifyItemChanged(position)
            }
        }

        holder.itemView.setOnClickListener {
            clicker?.holderClicked(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}