package com.example.neocafeteae1prototype.view.bottom_navigation_items.home.product

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.neocafeteae1prototype.databinding.FragmentProductBinding
import com.example.neocafeteae1prototype.view.root.BaseFragment
import com.example.neocafeteae1prototype.view.tools.logging
import com.example.neocafeteae1prototype.view.tools.notVisible
import com.example.neocafeteae1prototype.view.tools.visible
import com.squareup.picasso.Picasso

class ProductFragment : BaseFragment<FragmentProductBinding>() {

    private val args: ProductFragmentArgs by navArgs()

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
        changeVisibility(args.model.county)
        binding.quantity.text = args.model.county.toString()

    }

    private fun changeVisibility(county: Int) {
        with(binding) {
            if (county > 0) {
                add.notVisible()
                addButton.visible()
                removeButton.visible()
                quantity.visible()
                addElement.visible()
            } else if (county <= 0) {
                add.visible()
                addButton.notVisible()
                removeButton.notVisible()
                quantity.notVisible()
                addElement.notVisible()
            }
        }
    }

    private fun setUpUI() {
        binding.close.setOnClickListener { findNavController().navigateUp() }

        with(args.model) {
            Picasso.with(requireContext())
                .load(image)
                .into(binding.productImage)

            binding.productName.text = name
            binding.productPrice.text = price
            binding.description.text = "Description"

            binding.add.setOnClickListener {
                if (county == 0){
                    county++
                    binding.quantity.text = county.toString()
                }
                changeVisibility(county)
            }

            binding.addButton.setOnClickListener {
                this.county += 1
                binding.quantity.text = this.county.toString()
            }
            binding.removeButton.setOnClickListener {
                this.county -= 1
                binding.quantity.text = this.county.toString()
                if (county == 0){
                    changeVisibility(county)
                }
            }
            binding.addElement.setOnClickListener {
                findNavController().navigateUp()
            }

        }
    }


    override fun inflateView(
        inflater: LayoutInflater, container: ViewGroup?): FragmentProductBinding {
        return FragmentProductBinding.inflate(inflater)
    }

    override fun setUpToolbar() {
        "ProductFragmentToolbar".logging()
    }
}