package com.example.neocafeteae1prototype.view.bottom_navigation_items.home

import ItemOffsetDecoration
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.neocafeteae1prototype.R
import com.example.neocafeteae1prototype.data.Consts
import com.example.neocafeteae1prototype.data.models.AllModels
import com.example.neocafeteae1prototype.databinding.FragmentHomeBinding
import com.example.neocafeteae1prototype.view.adapters.MainRecyclerAdapter
import com.example.neocafeteae1prototype.view.adapters.ProductRecyclerAdapter
import com.example.neocafeteae1prototype.view.tools.bottom_sheet.ProductModalSheet
import com.example.neocafeteae1prototype.view.root.BaseFragment
import com.example.neocafeteae1prototype.view.root.BaseFragmentWithErrorLiveData
import com.example.neocafeteae1prototype.view.tools.delegates.RecyclerItemClickListener
import com.example.neocafeteae1prototype.view.tools.delegates.SecondItemClickListener
import com.example.neocafeteae1prototype.view.tools.notVisible
import com.example.neocafeteae1prototype.view_model.menu_shopping_vm.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragmentWithErrorLiveData<FragmentHomeBinding>(), RecyclerItemClickListener,
    SecondItemClickListener {

    private val shareViewModel: SharedViewModel by activityViewModels()
    private val popularAdapter by lazy { ProductRecyclerAdapter(this) } // Для продуктоа категории популярное
    private val mainAdapter by lazy { MainRecyclerAdapter(this) } // Для категории меню
    private val nav by lazy {findNavController()}

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclers()
        setUpButtonsListener()
        with(shareViewModel){
            getUserInfo()
            userData.observe(viewLifecycleOwner){
                binding.userName.text = "Привет ${it.first_name}"
            }
        }
    }

    private fun setUpRecyclers() {
        binding.menuRecycler.apply {
            adapter = mainAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            addItemDecoration(ItemOffsetDecoration(requireContext(), R.dimen.item_offset))
        }
        mainAdapter.setList(shareViewModel.menuList)

        binding.popularRecycler.apply {
            adapter = popularAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
        shareViewModel.productList.observe(viewLifecycleOwner) {
            shareViewModel.getPopularProduct(it)
            popularAdapter.setList(shareViewModel.popularList)
            binding.progress.notVisible()
        }
    }

    override fun itemClicked(item: AllModels?) {
        val category = item as AllModels.Menu
        nav.navigate(HomeFragmentDirections.actionHomeFragmentToMenuFragment(category.name))
    }

    override fun holderClicked(model: AllModels?) {
        ProductModalSheet(model!! as AllModels.Popular).show(childFragmentManager, "TAG")
    }

    private fun setUpButtonsListener() {
        binding.all.setOnClickListener { nav.navigate(HomeFragmentDirections.actionHomeFragmentToPopularFragment()) }
    }

    override fun setUpToolbar() {
        with(binding) {
            notificationIcon.setOnClickListener { nav.navigate(HomeFragmentDirections.actionHomeFragmentToNotification()) }
            searchIcon.setOnClickListener { nav.navigate(HomeFragmentDirections.actionHomeFragmentToSearchFragment()) }
        }
    }

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater)
    }

    override fun errorListener(): LiveData<Boolean> = shareViewModel.errorLiveData
}