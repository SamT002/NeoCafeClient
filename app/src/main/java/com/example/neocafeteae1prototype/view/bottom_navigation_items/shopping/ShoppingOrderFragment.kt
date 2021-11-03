package com.example.neocafeteae1prototype.view.bottom_navigation_items.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.neocafeteae1prototype.databinding.FragmentShoppingBinding
import com.example.neocafeteae1prototype.databinding.FragmentShoppingOrderBinding
import com.example.neocafeteae1prototype.view.root.BaseFragment
import com.example.neocafeteae1prototype.view.tools.logging


class ShoppingOrderFragment : BaseFragment<FragmentShoppingOrderBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?): FragmentShoppingOrderBinding {
        return FragmentShoppingOrderBinding.inflate(inflater)
    }

    override fun setUpToolbar() {
        "ShoppingOrderFragmentToolbar".logging()
    }
}