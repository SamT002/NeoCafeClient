package com.example.neocafeteae1prototype.application.bottom_navigation_items.branches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.neocafeteae1prototype.application.tools.BaseFragment
import com.example.neocafeteae1prototype.application.tools.adapters.MainRecyclerAdapter
import com.example.neocafeteae1prototype.application.tools.delegates.RecyclerItemClickListener
import com.example.neocafeteae1prototype.databinding.FragmentMapBinding
import com.example.neocafeteae1prototype.domain.sealedClasses.AllModels


class BranchFragment : BaseFragment<FragmentMapBinding>(), RecyclerItemClickListener {

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?): FragmentMapBinding {
        return FragmentMapBinding.inflate(inflater)
    }

    private val branchViewModel by lazy { ViewModelProvider(this).get(BranchViewModel::class.java) }


    private val myAdapter by lazy { MainRecyclerAdapter(this) }

    var myItem: AllModels.NeoCafePlace? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recyclerMap.apply {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(requireContext())
            myAdapter.setList(branchViewModel.list)
            binding.notification.setOnClickListener { findNavController().navigate(BranchFragmentDirections.actionBranchFragmentToNotification2()) }
        }
    }


    override fun itemClicked(item: AllModels) {
        myItem = item as AllModels.NeoCafePlace
        findNavController().navigate(BranchFragmentDirections.actionBranchFragmentToBranchesDetailFragment(item))
    }


}