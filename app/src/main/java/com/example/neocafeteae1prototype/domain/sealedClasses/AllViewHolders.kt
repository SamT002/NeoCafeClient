package com.example.neocafeteae1prototype.domain.sealedClasses

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.neocafeteae1prototype.R
import com.example.neocafeteae1prototype.application.tools.loadWithPicasso
import com.example.neocafeteae1prototype.application.tools.setWhiteColor
import com.example.neocafeteae1prototype.databinding.*
import com.squareup.picasso.Picasso

sealed class AllViewHolders(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    class MenuItemViewHolder(val binding: MenuItemBinding) : AllViewHolders(binding) {
        fun bind(item: AllModels.Menu) {
            with(binding) {
                Picasso.with(menuImage.context)
                    .load(item.image)
                    .into(menuImage)

                menuName.text = item.name
            }
        }
    }

    class NeoCafeViewHolder(val binding: MapItemBinding) : AllViewHolders(binding){

        fun bind(item:AllModels.NeoCafePlace){
            with(binding){
                streetMap.text = item.street
                timeWork.text = item.time
                imageView3.loadWithPicasso(item.image)
            }

        }

    }

    class BranchTimeWorkViewHolder(val binding: BranchTimeWorkItemBinding) : AllViewHolders(binding){

        fun bind(item: AllModels.BranchWorkTime){
            with(binding){
                startWorkTime.text = item.startTime
                endWorkTime.text = item.endTime
                day.text = item.day

                if (item.work) {
                    linearLayout.setBackgroundResource(R.drawable.layout_background_with_radius_enable)
                    startWorkTime.setWhiteColor(startWorkTime.context)
                    endWorkTime.setWhiteColor(endWorkTime.context)
                    day.setWhiteColor(day.context)
                }
            }
        }
    }

    class ReceiptViewHolder(val binding: HistoryOfReceiptItemBinding):AllViewHolders(binding){

        fun bind(item : AllModels.Receipt){

            val firstProduct = item.list[0]
            val secondProduct = item.list[1]

            with(binding){
                with(item){
                    `when`.text = time
                    setUpProductList(productName, productPrice, productCounty, totalProductPrice, firstProduct)
                    setUpProductList(secondProductName, secondProductPrice, secondProductCounty, secondTotalProductPrice, secondProduct)
                }
            }
        }

        private fun setUpProductList(name: TextView, price:TextView, county:TextView, totalProductPrice:TextView, data:AllModels.Product){
            name.text = data.productName
            price.text = data.productPrice
            county.text = data.county
            totalProductPrice.text = data.totalProductPrice

        }

    }

    class ProductReceiptViewHolder(val binding: ProductRecieptItemBinding) : AllViewHolders(binding){

        fun bind(item:AllModels.Product){
                with(binding){
                    productName.text = item.productName
                    productPrice.text = item.productPrice
                    productCounty.text = item.county
                    totalProductPrice.text = item.productPrice
            }

        }
    }


    class NotificationViewHolder(val binding:NotificationItemBinding) : AllViewHolders(binding){

        fun bind(item:AllModels.Notification){
            with(binding){
                message.text = item.message
                title.text = item.title
                time.text = item.time
            }
        }
    }

}
