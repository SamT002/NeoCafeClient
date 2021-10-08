package com.example.neocafeteae1prototype.application.root

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.neocafeteae1prototype.R
import com.example.neocafeteae1prototype.application.tools.TabNavigationClass
import com.example.neocafeteae1prototype.databinding.FragmentBottomViewBinding


class BottomViewFragment : TabNavigationClass(R.layout.fragment_bottom_view) {

    private var _binding: FragmentBottomViewBinding? = null
    private val binding get() = _binding!!
    private lateinit var selectedTab: Tab

    override val containerId: Int = R.id.bottom_container_view

    override val tabs: List<Tab> = listOf(Tab("first", R.navigation.home_nav_graph), Tab("second", R.navigation.shopping_nav_graph),
            Tab("third", R.navigation.qr_nav_graph), Tab("forth", R.navigation.mapping_nav_graph), Tab("fifth", R.navigation.user_nav_graph))

    override fun tabSelected(tab: Tab) {
        selectedTab = tab
    }


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentBottomViewBinding.inflate(inflater, container, false)

        selectedTab = tabs[0]
        selectTab(tabs[2])

        binding.bottomNavigationView.itemIconTintList = null
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_nav_graph -> {
                    if (selectedTab.name != tabs[0].name) selectTab(tabs[0])
                    true
                }
                R.id.shopping_nav_graph -> {
                    if (selectedTab.name != tabs[1].name) selectTab(tabs[1])
                    true
                }
                R.id.qr_nav_graph -> {
                    if (selectedTab.name != tabs[2].name) selectTab(tabs[2])
                    true
                }
                R.id.mapping_nav_graph -> {
                    if (selectedTab.name != tabs[3].name) selectTab(tabs[3])
                    true
                }
                R.id.user_nav_graph -> {
                    if (selectedTab.name != tabs[4].name) selectTab(tabs[4])
                    true
                }
                else -> false
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}