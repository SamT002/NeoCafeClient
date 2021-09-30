package com.example.neocafeteae1prototype.application.bottom_navigation_items.home.PopularProducts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.neocafeteae1prototype.application.tools.BaseFragment
import com.example.neocafeteae1prototype.application.tools.adapters.MainRecyclerAdapter
import com.example.neocafeteae1prototype.application.tools.adapters.ProductRecyclerAdapter
import com.example.neocafeteae1prototype.databinding.FragmentPopularBinding


class PopularFragment : BaseFragment<FragmentPopularBinding>() {

    private val viewModel by lazy {ViewModelProvider(this).get(PopularViewModel::class.java)}
    private lateinit var recyclerAdapter: ProductRecyclerAdapter

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?): FragmentPopularBinding {
        return FragmentPopularBinding.inflate(inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerAdapter = ProductRecyclerAdapter()

        binding.recycler.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = recyclerAdapter
        }

        recyclerAdapter.setList(viewModel.list)
    }
}