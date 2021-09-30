package com.example.neocafeteae1prototype.application.bottom_navigation_items.home.HomeFragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.neocafeteae1prototype.application.tools.BaseFragment
import com.example.neocafeteae1prototype.application.tools.Consts
import com.example.neocafeteae1prototype.application.tools.adapters.MainRecyclerAdapter
import com.example.neocafeteae1prototype.application.tools.adapters.ProductRecyclerAdapter
import com.example.neocafeteae1prototype.databinding.FragmentHomeBinding


class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val popularAdapter by lazy { ProductRecyclerAdapter() }
    private val homeViewModel by lazy { ViewModelProvider(this).get(HomeViewModel::class.java) }
    private val mainAdapter by lazy { MainRecyclerAdapter(null) }

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclers()
        getDataFromSharedPreference()

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
            mainAdapter.setList(homeViewModel.menuList)
        }

        binding.popularRecycler.apply {
            adapter = popularAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            popularAdapter.setList(homeViewModel.list)
        }

        binding.notificationIcon.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToNotification())
        }

        binding.searchIcon.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSearchFragment())
        }


    }


}