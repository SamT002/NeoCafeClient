package com.example.neocafeteae1prototype.application.bottom_navigation_items.user.user_shopping_history

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.neocafeteae1prototype.application.tools.BaseFragment
import com.example.neocafeteae1prototype.application.tools.adapters.MainRecyclerAdapter
import com.example.neocafeteae1prototype.application.tools.alert_dialog.CustomAlertDialog
import com.example.neocafeteae1prototype.application.tools.delegates.RecyclerItemClickListener
import com.example.neocafeteae1prototype.databinding.FragmentUserShoppingHistoryBinding
import com.example.neocafeteae1prototype.domain.sealedClasses.AllModels

class UserShoppingHistory : BaseFragment<FragmentUserShoppingHistoryBinding>(), RecyclerItemClickListener {

    private lateinit var recyclerAdapter: MainRecyclerAdapter
    private val viewModel by lazy { ViewModelProvider(this).get(UserShoppingViewModel::class.java) }

    override fun inflateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
    ): FragmentUserShoppingHistoryBinding {
        return FragmentUserShoppingHistoryBinding.inflate(inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerAdapter = MainRecyclerAdapter(this)
        binding.back.setOnClickListener { findNavController().navigateUp() }
        binding.notification.setOnClickListener { findNavController().navigate(UserShoppingHistoryDirections.actionUserShoppingHistoryToNotification5()) }

        binding.recyclerView.apply {
            adapter = recyclerAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        recyclerAdapter.setList(viewModel.list)

        binding.clearReceipt.setOnClickListener { showAlertDialog() }
    }

    private fun showAlertDialog() {
        CustomAlertDialog(this::clearAllReceipt, null, "Вы правда хотите очистить историю заказ?").show(childFragmentManager, "TAG")
    }

    override fun itemClicked(item: AllModels) {
        findNavController().navigate(UserShoppingHistoryDirections.actionUserShoppingHistoryToReceiptDetailFragment(item as AllModels.Receipt))
    }

    private fun clearAllReceipt() {
        Log.i("TAG", "clearAllReceipt")
    }


}