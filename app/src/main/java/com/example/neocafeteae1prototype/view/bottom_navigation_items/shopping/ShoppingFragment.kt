package com.example.neocafeteae1prototype.view.bottom_navigation_items.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.neocafeteae1prototype.R
import com.example.neocafeteae1prototype.view_model.menu_shopping_vm.SharedViewModel
import com.example.neocafeteae1prototype.view.root.BaseFragment
import com.example.neocafeteae1prototype.view.adapters.ShoppingRecyclerAdapter
import com.example.neocafeteae1prototype.view.tools.alert_dialog.BonusAlertDialog
import com.example.neocafeteae1prototype.view.tools.alert_dialog.CustomAlertDialog
import com.example.neocafeteae1prototype.databinding.FragmentShoppingBinding
import com.example.neocafeteae1prototype.data.models.AllModels
import com.example.neocafeteae1prototype.view.tools.WrapContentLinearLayoutManager
import com.example.neocafeteae1prototype.view.tools.cardActivate
import com.example.neocafeteae1prototype.view.tools.cardNotActive
import com.example.neocafeteae1prototype.view.tools.delegates.RecyclerItemClickListener
import com.example.neocafeteae1prototype.view.tools.logging
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShoppingFragment : BaseFragment<FragmentShoppingBinding>(), RecyclerItemClickListener{

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val bonus by lazy {sharedViewModel.bonus}
    private var inShop = true
    private val recyclerAdapter by lazy { ShoppingRecyclerAdapter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
        setUpRecycler()
        buttonListener()

        "OnViewCreated".logging()

        binding.delivery.setOnClickListener {
            inShop = false
            buttonListener()
        }
        binding.inShop.setOnClickListener {
            inShop = true
            buttonListener()
        }

        binding.order.setOnClickListener {
            CustomAlertDialog(this::showBonusAlertDialog, "Вы накопили $bonus бонусов", "Хотите снять их?")
                .show(childFragmentManager, "TAG")
        }
    }

    // Слушатель 2 cardView (В заведении или нет) меняет их background (Клиент таким  образом узнает какой из них активный)
    private fun buttonListener() {
        if (inShop) {
            binding.inShop.cardActivate(binding.inShopText)
            binding.delivery.cardNotActive(binding.deliveryText)
        } else {
            binding.delivery.cardActivate(binding.deliveryText)
            binding.inShop.cardNotActive(binding.inShopText)
        }
    }

    private fun setUpRecycler() {
        binding.recyclerView.apply {
            layoutManager = WrapContentLinearLayoutManager(requireContext())
            adapter = recyclerAdapter
        }
//        getShoppingList()
    }

//    private fun getShoppingList(){ // Сортирует массив и берет данные у которых кол во больше 0
//        sharedViewModel.getList().observe(viewLifecycleOwner) {
//            sharedViewModel.sortProductForShopping(it as MutableList<AllModels.Popular>)
//        }
//        if (sharedViewModel.shoppingList.isEmpty()){ // ЕСли он пустой открывается окно что корзина пустая
//            findNavController().navigate(ShoppingFragmentDirections.actionShoppingFragmentToEmptyIllustrativeFragment())
//        }else {
//            recyclerAdapter.setList(sharedViewModel.shoppingList)
//        }
//    }

    private fun setUpUi() {
        val bottomNavigationView = activity?.findViewById(R.id.bottomNavigationView) as BottomNavigationView
        with(binding){
            receiptHistory.setOnClickListener { bottomNavigationView.selectedItemId = R.id.user_nav_graph }
            goToMenu.setOnClickListener { bottomNavigationView.selectedItemId = R.id.home_nav_graph }
        }
    }

    private fun showBonusAlertDialog() { // Показывает 2ой Alert Dialog где юзер выбирает сколько бонусов использовать
        BonusAlertDialog(bonus, this::useBonus).show(childFragmentManager, "TAG")
    }

    private fun useBonus(bonus: String) { // Сработает когда в AlertDialog срабатывает использовать бонусы
        Toast.makeText(requireContext(), bonus, Toast.LENGTH_LONG).show()
    }

    override fun setUpToolbar() {
        with(binding.include) {
            notification.setOnClickListener { findNavController().navigate(ShoppingFragmentDirections.actionShoppingFragmentToNotification3()) }
            textView.text = "Корзина"
        }
    }

    override fun inflateView(
        inflater: LayoutInflater, container: ViewGroup?, ): FragmentShoppingBinding {
        return FragmentShoppingBinding.inflate(inflater)
    }

    override fun itemClicked(item: AllModels?) {
//        getShoppingList()
    }
}
