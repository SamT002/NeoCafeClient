package com.example.neocafeteae1prototype.view.bottom_navigation_items.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.neocafeteae1prototype.R
<<<<<<< HEAD
=======
import com.example.neocafeteae1prototype.data.models.AllModels
import com.example.neocafeteae1prototype.data.models.Resource
>>>>>>> 3ca4717 (Connected Shopping Fragment and connect QR Fragment)
import com.example.neocafeteae1prototype.databinding.FragmentEmptyIllustrativeBinding
import com.example.neocafeteae1prototype.view.root.BaseFragment
import com.example.neocafeteae1prototype.view_model.menu_shopping_vm.SharedViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmptyIllustrativeFragment : BaseFragment<FragmentEmptyIllustrativeBinding>() {

   private val sharedViewModel by activityViewModels<SharedViewModel>()

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      val bottomNavigationView = activity?.findViewById(R.id.bottomNavigationView) as BottomNavigationView
      binding.goToMenuButton.setOnClickListener { bottomNavigationView.selectedItemId = R.id.home_nav_graph }
<<<<<<< HEAD
=======

      sharedViewModel.list.observe(viewLifecycleOwner){
         when(it){
            is Resource.Success -> {
               sharedViewModel.sortProductForShopping(it.value)
               if(sharedViewModel.shoppingList.isNotEmpty()){
                  findNavController().navigateUp()
               }
            }
         }
      }


>>>>>>> 3ca4717 (Connected Shopping Fragment and connect QR Fragment)

      sharedViewModel.productList.observe(viewLifecycleOwner) {
         sharedViewModel.sortProductForShopping(it)
         if (sharedViewModel.shoppingList.isNotEmpty()) {
            findNavController().navigateUp()
         }
      }
   }
   override fun inflateView(inflater: LayoutInflater, container: ViewGroup?): FragmentEmptyIllustrativeBinding {
      return FragmentEmptyIllustrativeBinding.inflate(inflater)
   }

   override fun setUpToolbar() {
      with(binding.include){
         textView.text = "Корзина"
         backButton.setOnClickListener { findNavController().navigateUp() }
         notification.setOnClickListener { findNavController().navigate(EmptyIllustrativeFragmentDirections.actionEmptyIllustrativeFragmentToNotification3()) }
      }
   }
}