package com.example.neocafeteae1prototype.application.bottom_navigation_items.user.user_shopping_history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.neocafeteae1prototype.application.tools.BaseFragment
import com.example.neocafeteae1prototype.application.tools.adapters.MainRecyclerAdapter
import com.example.neocafeteae1prototype.databinding.FragmentReceiptDetailBinding
import com.example.neocafeteae1prototype.domain.sealedClasses.AllModels


class ReceiptDetailFragment : BaseFragment<FragmentReceiptDetailBinding>() {
    private val recyclerAdapter by lazy { MainRecyclerAdapter(null) }

    private val safeArgs: ReceiptDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi(safeArgs.receiptModel)
        binding.back.setOnClickListener { findNavController().navigateUp() }
        binding.notification.setOnClickListener { findNavController().navigate(ReceiptDetailFragmentDirections.actionReceiptDetailFragmentToNotification5()) }

        binding.recycler.apply {
            adapter = recyclerAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        recyclerAdapter.setList(safeArgs.receiptModel.list)
    }

    private fun setUpUi(receiptModel: AllModels.Receipt) {
        binding.`when`.text = receiptModel.time
        binding.address.text = receiptModel.street
        binding.totalPrice.text = receiptModel.total
    }

    override fun inflateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
    ): FragmentReceiptDetailBinding {
        return FragmentReceiptDetailBinding.inflate(inflater)
    }
}