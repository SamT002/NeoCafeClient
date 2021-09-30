package com.example.neocafeteae1prototype.application.notification

import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.neocafeteae1prototype.R
import com.example.neocafeteae1prototype.application.tools.BaseFragment
import com.example.neocafeteae1prototype.application.tools.Consts
import com.example.neocafeteae1prototype.application.tools.adapters.MainRecyclerAdapter
import com.example.neocafeteae1prototype.application.tools.alert_dialog.CustomAlertDialog
import com.example.neocafeteae1prototype.application.tools.showToast
import com.example.neocafeteae1prototype.databinding.FragmentNotificationBinding
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator


class Notification : BaseFragment<FragmentNotificationBinding>() {

    private val viewModel by lazy { ViewModelProvider(this).get(NotificationViewModel::class.java) }
    private var recyclerAdapter: MainRecyclerAdapter? = null

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?): FragmentNotificationBinding {
        return FragmentNotificationBinding.inflate(inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerAdapter = MainRecyclerAdapter(null)

        setUpRecycler()
        setUpSwipeCallback()
        binding.back.setOnClickListener { findNavController().navigateUp() }
    }

    private fun setUpSwipeCallback() {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder,
            ): Boolean {
                return false
            }

            override fun onChildDraw(
                    c: Canvas,
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    dX: Float,
                    dY: Float,
                    actionState: Int,
                    isCurrentlyActive: Boolean,
            ) {
                RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addSwipeLeftBackgroundColor(Color.parseColor(Consts.ENABLE_COLOR))
                        .addActionIcon(R.drawable.ic_trash)
                        .create()
                        .decorate()

                super.onChildDraw(
                        c,
                        recyclerView,
                        viewHolder,
                        dX,
                        dY,
                        actionState,
                        isCurrentlyActive
                )

            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.bindingAdapterPosition

                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        Toast.makeText(requireContext(), "$position", Toast.LENGTH_LONG).show()
                        recyclerAdapter?.notifyItemRemoved(position)
                    }
                }
            }


        }

        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.notificationRecycler)
    }

    private fun setUpRecycler() {
        binding.notificationRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = recyclerAdapter
        }

        recyclerAdapter?.setList(viewModel.list)

    }
}