package com.example.neocafeteae1prototype.application.bottom_navigation_items.home.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.neocafeteae1prototype.R
import com.example.neocafeteae1prototype.application.bottom_navigation_items.SharedViewModel
import com.example.neocafeteae1prototype.application.tools.BaseFragment
import com.example.neocafeteae1prototype.application.tools.adapters.MenuRecyclerAdapter
import com.example.neocafeteae1prototype.application.tools.delegates.SecondItemClickListener
import com.example.neocafeteae1prototype.databinding.FragmentMenuBinding
import com.example.neocafeteae1prototype.domain.sealedClasses.AllModels


class MenuFragment : BaseFragment<FragmentMenuBinding>(), SecondItemClickListener {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val viewModel by lazy { ViewModelProvider(this).get(MenuViewModel::class.java) }
    private lateinit var recyclerAdapter: MenuRecyclerAdapter
    private val args: MenuFragmentArgs by navArgs()
    private val mapOfCategory = mutableMapOf<String, Int>(
        "Выпечка" to R.id.bakery, "Кофе" to R.id.coffee, "Чай" to R.id.tea,
        "Напитки" to R.id.drinks, "Десерты" to R.id.desserts
    )

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?): FragmentMenuBinding {
        return FragmentMenuBinding.inflate(inflater)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar()
        recyclerAdapter = MenuRecyclerAdapter(this)
        binding.recycler.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = recyclerAdapter
        }

        val viewId = mapOfCategory[args.category]
        if (viewId != null) {
            binding.chipGroup.check(viewId)
            recyclerSetList(viewId)
        }

        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
            recyclerSetList(checkedId)
        }

    }


    private fun recyclerSetList(checkedId: Int) {
        val checkedText = when (checkedId) {
            R.id.bakery -> "Выпечка"
            R.id.coffee -> "Кофе"
            R.id.tea -> "Чай"
            R.id.drinks -> "Напитки"
            R.id.all -> "Все"
            else -> ""
        }
        sharedViewModel.getList().observe(viewLifecycleOwner, {
            val sorted = sharedViewModel.sort(checkedText, it as MutableList<AllModels.Popular>)
            recyclerAdapter.setList(sorted, checkedText)
        })
    }

    private fun setUpToolbar() {
        val navController = findNavController()
        binding.include.textView.text = resources.getText(R.string.Menu)
        binding.include.backButton.setOnClickListener { navController.navigateUp() }
        binding.include.notification.setOnClickListener {
            navController.navigate(
                MenuFragmentDirections.actionMenuFragmentToNotification()
            )
        }
    }

    override fun holderClicked(model: AllModels) {
        findNavController().navigate(
            MenuFragmentDirections.actionMenuFragmentToProductFragment(
                model as AllModels.Popular
            )
        )
    }

}