package com.example.neocafeteae1prototype.view.bottom_navigation_items.home.home_search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.neocafeteae1prototype.databinding.FragmentSearchBinding
import com.example.neocafeteae1prototype.view.adapters.ProductRecyclerAdapter
import com.example.neocafeteae1prototype.view.root.BaseFragment
import com.example.neocafeteae1prototype.view.tools.logging

class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    private val recyclerAdapter by lazy { ProductRecyclerAdapter(null) }
    private val viewModel by lazy { ViewModelProvider(this).get(SearchViewModel::class.java) }

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?, ): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.search.isActivated = true
        setUpRecycler()
    }

    private fun setUpRecycler() {
        binding.searchRecycler.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = recyclerAdapter
        }
        recyclerAdapter.setList(viewModel.list)
    }


    override fun setUpToolbar() {
        "SearchFragmentToolbar".logging()
    }
}