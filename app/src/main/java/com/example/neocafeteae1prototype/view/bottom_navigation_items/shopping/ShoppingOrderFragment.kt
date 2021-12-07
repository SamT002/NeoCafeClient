package com.example.neocafeteae1prototype.view.bottom_navigation_items.shopping

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
<<<<<<< HEAD
import com.example.neocafeteae1prototype.R
=======
>>>>>>> 3ca4717 (Connected Shopping Fragment and connect QR Fragment)
import com.example.neocafeteae1prototype.data.Consts
import com.example.neocafeteae1prototype.data.models.Resource
import com.example.neocafeteae1prototype.databinding.FragmentShoppingOrderBinding
import com.example.neocafeteae1prototype.view.adapters.MainRecyclerAdapter
import com.example.neocafeteae1prototype.view.root.BaseFragment
import com.example.neocafeteae1prototype.view.tools.alert_dialog.DoneAlertDialog
import com.example.neocafeteae1prototype.view.tools.mainLogging
import com.example.neocafeteae1prototype.view.tools.notVisible
import com.example.neocafeteae1prototype.view.tools.visible
import com.example.neocafeteae1prototype.view_model.menu_shopping_vm.ShoppingOrderViewModel
import dagger.hilt.android.AndroidEntryPoint
<<<<<<< HEAD
import java.text.SimpleDateFormat
import java.util.*
=======
>>>>>>> 3ca4717 (Connected Shopping Fragment and connect QR Fragment)

@AndroidEntryPoint
class ShoppingOrderFragment : BaseFragment<FragmentShoppingOrderBinding>() {

    private val args by navArgs<ShoppingOrderFragmentArgs>()
    private var totalPrice = 0
    private val recyclerAdapter by lazy {MainRecyclerAdapter(null)}
    private val viewModel by viewModels<ShoppingOrderViewModel>()
<<<<<<< HEAD
    private val nav by lazy {findNavController()}

=======
>>>>>>> 3ca4717 (Connected Shopping Fragment and connect QR Fragment)

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycler()
<<<<<<< HEAD
        setDate()
=======
>>>>>>> 3ca4717 (Connected Shopping Fragment and connect QR Fragment)
        viewModel.getProductList(args.shoppingList.products)
        args.shoppingList.products.forEach {
            totalPrice += it.price * it.county
        }
        with(binding){
            sum.text = "$totalPrice c"
            bonus.text = args.bonus.toString()
            result.text = "${totalPrice.minus(args.bonus)} c"
            takeOrder.setOnClickListener { sendProducts() }
        }


    }

<<<<<<< HEAD
    @SuppressLint("SimpleDateFormat")
    private fun setDate() {
        val calendar = Calendar.getInstance().time
        binding.apply {
            date.text = (SimpleDateFormat(getString(R.string.date_format)).format(calendar))
            time.text = (SimpleDateFormat(getString(R.string.hour_format)).format(calendar))
        }
    }

    private fun sendProducts() {
//        viewModel.sendProductList()
        binding.progress.visible()
        viewModel.isProductListSent.observe(viewLifecycleOwner){
            if (it){
                binding.progress.notVisible()
                DoneAlertDialog("Ваш заказ оформлен").show(childFragmentManager, "TAG")
            }
        }
=======
    private fun sendProducts() {
        DoneAlertDialog("Ваш заказ оформлен").show(childFragmentManager, "TAG")

//        "sendProduct".mainLogging()
//        val token = sharedPreferences.getString(Consts.ACCESS, "null")
//        val tableId = sharedPreferences.getString(Consts.TABLE, "null")
//        viewModel.sendProductList(tableId, args.bonus, token!!)
//        binding.progress.visible()
//        viewModel.responseChecker.observe(viewLifecycleOwner){
//            when(it){
//                is Resource.Success -> {
//                    binding.progress.notVisible()
//                    DoneAlertDialog("Ваш заказ оформлен").show(childFragmentManager, "TAG")
//                }
//            }
//        }
>>>>>>> 3ca4717 (Connected Shopping Fragment and connect QR Fragment)
    }

    private fun setUpRecycler() {
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = recyclerAdapter
        }
        recyclerAdapter.setList(viewModel.productList)
    }

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?): FragmentShoppingOrderBinding {
        return FragmentShoppingOrderBinding.inflate(inflater)
    }

    override fun setUpToolbar() {
        with(binding.include){
<<<<<<< HEAD
            backButton.setOnClickListener{nav.navigateUp()}
            notification.setOnClickListener { nav.navigate(ShoppingOrderFragmentDirections.actionShoppingOrderFragment2ToNotification3()) }
=======
            backButton.setOnClickListener{findNavController().navigateUp()}
            notification.setOnClickListener { findNavController().navigate(ShoppingOrderFragmentDirections.actionShoppingOrderFragment2ToNotification3()) }
>>>>>>> 3ca4717 (Connected Shopping Fragment and connect QR Fragment)
        }
    }
}