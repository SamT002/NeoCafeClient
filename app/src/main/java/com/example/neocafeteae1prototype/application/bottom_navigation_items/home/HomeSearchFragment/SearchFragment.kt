package com.example.neocafeteae1prototype.application.bottom_navigation_items.home.HomeSearchFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.neocafeteae1prototype.application.tools.BaseFragment
import com.example.neocafeteae1prototype.application.tools.adapters.MainRecyclerAdapter
import com.example.neocafeteae1prototype.application.tools.adapters.ProductRecyclerAdapter
import com.example.neocafeteae1prototype.databinding.FragmentSearchBinding

class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    private var recyclerAdapter: ProductRecyclerAdapter? = null
    private val viewModel by lazy { ViewModelProvider(this).get(SearchViewModel::class.java) }

    override fun inflateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
    ): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerAdapter = ProductRecyclerAdapter()
        setUpRecycler()

    }

    private fun setUpRecycler() {

        binding.searchRecycler.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = recyclerAdapter
        }
        recyclerAdapter?.setList(viewModel.list)
    }
}