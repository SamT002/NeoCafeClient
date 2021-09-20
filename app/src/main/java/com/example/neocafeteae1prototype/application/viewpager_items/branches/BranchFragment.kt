package com.example.neocafeteae1prototype.application.viewpager_items.branches

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.neocafeteae1prototype.application.tools.delegates.RecyclerItemClickListener
import com.example.neocafeteae1prototype.application.tools.adapters.recyclerAdapters.MainRecyclerAdapter
import com.example.neocafeteae1prototype.application.viewpager_items.branches.viewModel.BranchesViewModel
import com.example.neocafeteae1prototype.databinding.FragmentMapBinding
import com.example.neocafeteae1prototype.domain.sealedClasses.AllModels


class BranchFragment : Fragment(), RecyclerItemClickListener {

    private var _binding:FragmentMapBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { BranchesViewModel() }

    private val myAdapter by lazy{MainRecyclerAdapter(this)}

    var myItem:AllModels.NeoCafePlace? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMapBinding.inflate(inflater, container, false)

        binding.recyclerMap.apply {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(requireContext())
            myAdapter.setList(viewModel.list)
        }


        // Inflate the layout for this fragment
        return binding.root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun itemClicked(item: AllModels) {
        myItem = item as AllModels.NeoCafePlace
        viewModel.setItem(myItem!!)
//        viewModel.setItem(item as AllModels.NeoCafePlace)
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace((requireView().parent as ViewGroup).id, BranchesDetailFragment())
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            addToBackStack(null);
            commit()
        }
    }
}