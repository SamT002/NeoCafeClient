package com.example.neocafeteae1prototype.view.bottom_navigation_items.shopping

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
<<<<<<< HEAD
=======
import android.widget.Toast
>>>>>>> 3ca4717 (Connected Shopping Fragment and connect QR Fragment)
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.neocafeteae1prototype.R
import com.example.neocafeteae1prototype.data.Consts
import com.example.neocafeteae1prototype.data.models.AllModels
<<<<<<< HEAD
import com.example.neocafeteae1prototype.databinding.FragmentShoppingBinding
import com.example.neocafeteae1prototype.view.adapters.ShoppingRecyclerAdapter
import com.example.neocafeteae1prototype.view.root.BaseFragment
import com.example.neocafeteae1prototype.view.tools.WrapContentLinearLayoutManager
import com.example.neocafeteae1prototype.view.tools.alert_dialog.ShoppingAlertDialog
import com.example.neocafeteae1prototype.view.tools.bottom_sheet.BonusBottomSheet
import com.example.neocafeteae1prototype.view.tools.cardActivate
import com.example.neocafeteae1prototype.view.tools.cardNotActive
import com.example.neocafeteae1prototype.view.tools.delegates.RecyclerItemClickListener
import com.example.neocafeteae1prototype.view.tools.delegates.SecondItemClickListener
import com.example.neocafeteae1prototype.view.tools.mainLogging
import com.example.neocafeteae1prototype.view_model.menu_shopping_vm.SharedViewModel
=======
import com.example.neocafeteae1prototype.view_model.menu_shopping_vm.SharedViewModel
import com.example.neocafeteae1prototype.view.root.BaseFragment
import com.example.neocafeteae1prototype.view.adapters.ShoppingRecyclerAdapter
import com.example.neocafeteae1prototype.view.tools.alert_dialog.CustomAlertDialog
import com.example.neocafeteae1prototype.databinding.FragmentShoppingBinding
import com.example.neocafeteae1prototype.data.models.Resource
import com.example.neocafeteae1prototype.view.tools.*
import com.example.neocafeteae1prototype.view.tools.alert_dialog.ShoppingAlertDialog
import com.example.neocafeteae1prototype.view.tools.bottom_sheet.BonusBottomSheet
import com.example.neocafeteae1prototype.view.tools.delegates.RecyclerItemClickListener
import com.example.neocafeteae1prototype.view.tools.delegates.SecondItemClickListener
>>>>>>> 3ca4717 (Connected Shopping Fragment and connect QR Fragment)
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShoppingFragment : BaseFragment<FragmentShoppingBinding>(), RecyclerItemClickListener ,SecondItemClickListener{

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val bonus by lazy {sharedViewModel.bonus}
    private var inShop = true
<<<<<<< HEAD
    private val nav by lazy {findNavController()}
=======
>>>>>>> 3ca4717 (Connected Shopping Fragment and connect QR Fragment)
    private val bottomNavigation by lazy {activity?.findViewById(R.id.bottomNavigationView) as BottomNavigationView}
    private val recyclerAdapter by lazy { ShoppingRecyclerAdapter(this,this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
<<<<<<< HEAD
        "OnViewCreated".mainLogging()
        getTotalPrice()
        setUpUi()
        setUpRecycler()
        buttonListener()

        binding.delivery.setOnClickListener {  // Слушатель в заведении или нет меняет background кнопки
=======
        sharedViewModel.getBonus(sharedPreferences.getString(Consts.ACCESS, "0")!!)
        setUpUi()
        setUpRecycler()
        buttonListener()
        getTotalPrice()

        binding.delivery.setOnClickListener {
>>>>>>> 3ca4717 (Connected Shopping Fragment and connect QR Fragment)
            inShop = false
            buttonListener()
        }
        binding.inShop.setOnClickListener {
            inShop = true
            buttonListener()
        }

<<<<<<< HEAD
=======
        binding.order.setOnClickListener {
            ShoppingAlertDialog(this::showBonusAlertDialog, this::withoutBonus, "Вы накопили $bonus бонусов", "Хотите снять их?")
                .show(childFragmentManager, "TAG")
        }
>>>>>>> 3ca4717 (Connected Shopping Fragment and connect QR Fragment)
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

        binding.order.setOnClickListener {
            if (sharedViewModel.bonus != 0){
                ShoppingAlertDialog(this::showBonusAlertDialog, this::useBonus, "Вы накопили $bonus бонусов", "Хотите снять их?")
                    .show(childFragmentManager, "TAG")
            }else {
                useBonus(0)
            }
        }
    }

    private fun setUpRecycler() {
        binding.recyclerView.apply {
            layoutManager = WrapContentLinearLayoutManager(requireContext())
            adapter = recyclerAdapter
        }
        getShoppingList()
<<<<<<< HEAD
    }

    private fun getShoppingList(){ // Сортирует массив и берет данные у которых кол во больше 0
        sharedViewModel.productList.observe(viewLifecycleOwner) {
                    sharedViewModel.sortProductForShopping(it)
                    if (sharedViewModel.shoppingList.isEmpty()){ // Если он пустой открывается окно что корзина пустая
                        nav.navigate(ShoppingFragmentDirections.actionShoppingFragmentToEmptyIllustrativeFragment())
                    }else {
                        recyclerAdapter.setList(sharedViewModel.shoppingList)
                    }
            getTotalPrice()
                }
    }

    @SuppressLint("SetTextI18n")
=======
    }

    private fun getShoppingList(){ // Сортирует массив и берет данные у которых кол во больше 0
        sharedViewModel.list.observe(viewLifecycleOwner) {
            when(it){
                is Resource.Success -> {
                    sharedViewModel.sortProductForShopping(it.value)
                    if (sharedViewModel.shoppingList.isEmpty()){ // ЕСли он пустой открывается окно что корзина пустая
                        findNavController().navigate(ShoppingFragmentDirections.actionShoppingFragmentToEmptyIllustrativeFragment())
                    }else {
                        recyclerAdapter.setList(sharedViewModel.shoppingList)
                    }
                }
            }
        }
        getTotalPrice()
    }

>>>>>>> 3ca4717 (Connected Shopping Fragment and connect QR Fragment)
    private fun getTotalPrice() {
        binding.result.text = "${sharedViewModel.getTotalPrice()} c"
    }

    private fun setUpUi() {
        with(binding){
            receiptHistory.setOnClickListener { bottomNavigation.selectedItemId = R.id.user_nav_graph }
            goToMenu.setOnClickListener { bottomNavigation.selectedItemId = R.id.home_nav_graph }
        }
    }

    private fun showBonusAlertDialog() { // Показывает 2ой Alert Dialog где юзер выбирает сколько бонусов использовать
        BonusBottomSheet(sharedViewModel.bonus, this::useBonus).show(childFragmentManager, "TAG")
    }


    //Проверка бонусов и стола
<<<<<<< HEAD
    private fun useBonus(bonus: Int) { // Сработает когда в AlertDialog срабатывает использовать бонусы
//        val tableId = sharedPreferences.getInt(Consts.TABLE, 0)
//        if (tableId == 0){
//            bottomNavigation.selectedItemId = R.id.qr_nav_graph
//        }else {
            val shoppingList = AllModels.Test(sharedViewModel.shoppingList)
            nav.navigate(ShoppingFragmentDirections.actionShoppingFragmentToShoppingOrderFragment2(shoppingList, bonus))
//        }

=======
    private fun useBonus(bonus: String) { // Сработает когда в AlertDialog срабатывает использовать бонусы
        val tableId = sharedPreferences.getInt(Consts.TABLE, 0)
        if (tableId == 0){
            bottomNavigation.selectedItemId = R.id.qr_nav_graph
        }else {
            val shoppingList = AllModels.Test(sharedViewModel.shoppingList)
            findNavController().navigate(ShoppingFragmentDirections.actionShoppingFragmentToShoppingOrderFragment2(shoppingList, bonus.toInt()))
        }

    }

    private fun withoutBonus(){
        val shoppingList = AllModels.Test(sharedViewModel.shoppingList)
        findNavController().navigate(ShoppingFragmentDirections.actionShoppingFragmentToShoppingOrderFragment2(shoppingList, 0))
>>>>>>> 3ca4717 (Connected Shopping Fragment and connect QR Fragment)
    }

    override fun setUpToolbar() {
        with(binding.include) {
            notification.setOnClickListener { findNavController().navigate(ShoppingFragmentDirections.actionShoppingFragmentToNotification3()) }
            textView.text = "Корзина"
        }
    }

<<<<<<< HEAD
    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?): FragmentShoppingBinding {
=======
    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?, ): FragmentShoppingBinding {
>>>>>>> 3ca4717 (Connected Shopping Fragment and connect QR Fragment)
        return FragmentShoppingBinding.inflate(inflater)
    }

    override fun itemClicked(item: AllModels?) {
        getTotalPrice()
    }

    override fun holderClicked(model: AllModels?) {
        if (sharedViewModel.shoppingList.isEmpty()) { // ЕСли он пустой открывается окно что корзина пустая
<<<<<<< HEAD
            nav.navigate(ShoppingFragmentDirections.actionShoppingFragmentToEmptyIllustrativeFragment())
        }
    }


=======
            findNavController().navigate(ShoppingFragmentDirections.actionShoppingFragmentToEmptyIllustrativeFragment())
        }
    }

>>>>>>> 3ca4717 (Connected Shopping Fragment and connect QR Fragment)
}
