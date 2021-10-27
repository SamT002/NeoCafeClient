package com.example.neocafeteae1prototype.view.bottom_navigation_items.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.neocafeteae1prototype.data.Consts
import com.example.neocafeteae1prototype.data.models.AllModels
import com.example.neocafeteae1prototype.databinding.FragmentHomeBinding
import com.example.neocafeteae1prototype.view.adapters.MainRecyclerAdapter
import com.example.neocafeteae1prototype.view.adapters.ProductRecyclerAdapter
import com.example.neocafeteae1prototype.view.root.BaseFragment
import com.example.neocafeteae1prototype.view.tools.delegates.RecyclerItemClickListener
import com.example.neocafeteae1prototype.view.tools.delegates.SecondItemClickListener
import com.example.neocafeteae1prototype.view_model.menu_shopping_vm.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(), RecyclerItemClickListener,
    SecondItemClickListener {

    private val shareViewModel: SharedViewModel by activityViewModels()
    private val popularAdapter by lazy { ProductRecyclerAdapter(this) }
    private val mainAdapter by lazy { MainRecyclerAdapter(this) }

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclers()
        getDataFromSharedPreference()

        binding.all.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToPopularFragment())
        }
    }

    override fun setUpToolbar() {
        with(binding){
            notificationIcon.setOnClickListener {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToNotification())
            }
            searchIcon.setOnClickListener {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSearchFragment())
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun getDataFromSharedPreference() {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        val userName = sharedPref.getString(Consts.USER_NAME, "null")
        binding.userName.text = "Привет, $userName"
    }

    private fun setUpRecyclers() {
        binding.menuRecycler.apply {
            adapter = mainAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
        mainAdapter.setList(shareViewModel.menuList)

        binding.popularRecycler.apply {
            adapter = popularAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
        shareViewModel.getList().observe(viewLifecycleOwner, {
            popularAdapter.setList(it as MutableList<AllModels.Popular>)
        })


    }

    override fun itemClicked(item: AllModels?) {
        val category = item as AllModels.Menu
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToMenuFragment(
                category.name
            )
        )
    }

    override fun holderClicked(model: AllModels) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToProductFragment(
                model as AllModels.Popular
            )
        )
    }


}