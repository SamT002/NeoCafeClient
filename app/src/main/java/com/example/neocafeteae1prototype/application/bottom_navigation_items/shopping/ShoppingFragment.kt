package com.example.neocafeteae1prototype.application.bottom_navigation_items.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.neocafeteae1prototype.R
import com.example.neocafeteae1prototype.application.bottom_navigation_items.SharedViewModel
import com.example.neocafeteae1prototype.application.tools.BaseFragment
import com.example.neocafeteae1prototype.application.tools.adapters.MainRecyclerAdapter
import com.example.neocafeteae1prototype.application.tools.adapters.ProductRecyclerAdapter
import com.example.neocafeteae1prototype.application.tools.adapters.ShoppingRecyclerAdapter
import com.example.neocafeteae1prototype.application.tools.alert_dialog.BonusAlertDialog
import com.example.neocafeteae1prototype.application.tools.alert_dialog.CustomAlertDialog
import com.example.neocafeteae1prototype.databinding.FragmentShoppingBinding
import com.example.neocafeteae1prototype.domain.sealedClasses.AllModels
import com.google.android.material.bottomnavigation.BottomNavigationView

class ShoppingFragment : BaseFragment<FragmentShoppingBinding>() {

    private val sharedViewModel:SharedViewModel by activityViewModels()
    private var bonus = 0

    private val viewModel by lazy { ViewModelProvider(this).get(ShoppingViewModel::class.java) }
    private lateinit var recyclerAdapter:ShoppingRecyclerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.include.textView.text = "Корзина"
        bonus = sharedViewModel.bonus
        setUpUi()
        setUpRecycler()

        binding.order.setOnClickListener {
            CustomAlertDialog(this::showBonusAlertDialog, "Вы накопили $bonus бонусов", "Хотите снять их?")
                .show(childFragmentManager, "TAG")
        }
    }

    private fun setUpRecycler() {
        recyclerAdapter = ShoppingRecyclerAdapter(null)

        binding.recyclerView.apply{
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = recyclerAdapter
        }

        binding.include.notification.setOnClickListener {
            findNavController().navigate(ShoppingFragmentDirections.actionShoppingFragmentToNotification3())
        }
//        recyclerAdapter.setList(viewModel.list)

        sharedViewModel.getList().observe(viewLifecycleOwner, {
            for (i in it){
                if (i.county > 0){
                    sharedViewModel.setShoppingList(i)
                }
            }
        })

        sharedViewModel.shoppingLiveData.observe(viewLifecycleOwner, ){
            recyclerAdapter.setList(it as MutableList<AllModels.Popular>)
        }
    }

    private fun setUpUi() {
        val bottomNavigationView = activity?.findViewById(R.id.bottomNavigationView) as BottomNavigationView
        binding.receiptHistory.setOnClickListener {
            bottomNavigationView.selectedItemId = R.id.user_nav_graph
        }
        binding.goToMenu.setOnClickListener {
            bottomNavigationView.selectedItemId = R.id.home_nav_graph
        }
    }

    private fun showBonusAlertDialog(){
        BonusAlertDialog(50, this::useBonus).show(childFragmentManager, "TAG")
    }

    private fun useBonus(bonus:String){
        Toast.makeText(requireContext(), bonus, Toast.LENGTH_LONG).show()
    }


    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentShoppingBinding {
        return FragmentShoppingBinding.inflate(inflater)
    }
}
