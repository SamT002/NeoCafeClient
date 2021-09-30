package com.example.neocafeteae1prototype.application.bottom_navigation_items.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.neocafeteae1prototype.R
import com.example.neocafeteae1prototype.application.tools.BaseFragment
import com.example.neocafeteae1prototype.application.tools.adapters.MainRecyclerAdapter
import com.example.neocafeteae1prototype.application.tools.adapters.ProductRecyclerAdapter
import com.example.neocafeteae1prototype.databinding.FragmentShoppingBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class ShoppingFragment : BaseFragment<FragmentShoppingBinding>() {

    private val viewModel by lazy { ViewModelProvider(this).get(ShoppingViewModel::class.java) }
    private lateinit var recyclerAdapter:ProductRecyclerAdapter

    override fun inflateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
    ): FragmentShoppingBinding {
        return FragmentShoppingBinding.inflate(inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.receiptHistory.setOnClickListener {
            val bottomNavigationView = activity?.findViewById(R.id.bottomNavigationView) as BottomNavigationView
            bottomNavigationView.selectedItemId = R.id.user_nav_graph
        }

        recyclerAdapter = ProductRecyclerAdapter()

        binding.recyclerView.apply{
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = recyclerAdapter
        }
        recyclerAdapter.setList(viewModel.list)
        binding.notificaton.setOnClickListener {
            ShoppingFragmentDirections.actionShoppingFragmentToNotification3()
        }
    }
}