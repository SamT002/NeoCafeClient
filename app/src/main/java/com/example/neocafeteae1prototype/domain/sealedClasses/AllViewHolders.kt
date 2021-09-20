package com.example.neocafeteae1prototype.domain.sealedClasses

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.neocafeteae1prototype.databinding.MapItemBinding
import com.example.neocafeteae1prototype.databinding.PopularItemBinding
import com.squareup.picasso.Picasso

sealed class AllViewHolders(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    class PopularViewHolder(val binding: PopularItemBinding) : AllViewHolders(binding) {
        fun bind(item: AllModels.Popular) {
            binding.foodName.text = item.name
            binding.foodPrice.text = item.price

            Picasso.with(binding.foodImage.context)
                    .load(item.image)
                    .into(binding.foodImage)

        }
    }

    class NeoCafeViewHolder(val binding: MapItemBinding) : AllViewHolders(binding){

        fun bind(item:AllModels.NeoCafePlace){
            with(binding){
                streetMap
            }
            binding.streetMap.text = item.street
            binding.timeWork.text = item.time

            Picasso.with(binding.imageView3.context)
                .load(item.image)
                .into(binding.imageView3)

        }

    }

}
