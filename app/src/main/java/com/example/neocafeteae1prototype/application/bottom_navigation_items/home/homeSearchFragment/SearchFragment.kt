package com.example.neocafeteae1prototype.application.bottom_navigation_items.home.homeSearchFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.neocafeteae1prototype.application.tools.BaseFragment
import com.example.neocafeteae1prototype.application.tools.adapters.ProductRecyclerAdapter
import com.example.neocafeteae1prototype.application.tools.delegates.SecondItemClickListener
import com.example.neocafeteae1prototype.application.tools.showToast
import com.example.neocafeteae1prototype.databinding.FragmentSearchBinding
import com.example.neocafeteae1prototype.domain.sealedClasses.AllModels

class SearchFragment : BaseFragment<FragmentSearchBinding>(), SecondItemClickListener {

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

        binding.search.isFocusable = true
        recyclerAdapter = ProductRecyclerAdapter(this)
        setUpRecycler()

    }

    private fun setUpRecycler() {

        binding.searchRecycler.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = recyclerAdapter
        }
        recyclerAdapter?.setList(viewModel.list)
    }

    override fun holderClicked(model: AllModels) {
        "Hello".showToast(requireContext(), Toast.LENGTH_LONG)
    }
}