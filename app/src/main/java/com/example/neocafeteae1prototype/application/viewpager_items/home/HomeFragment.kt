package com.example.neocafeteae1prototype.application.viewpager_items.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.neocafeteae1prototype.R
import com.example.neocafeteae1prototype.application.tools.delegates.RecyclerItemClickListener
import com.example.neocafeteae1prototype.application.tools.adapters.recyclerAdapters.MainRecyclerAdapter
import com.example.neocafeteae1prototype.application.tools.adapters.recyclerAdapters.MenuRecyclerAdapter
import com.example.neocafeteae1prototype.databinding.FragmentHomeBinding
import com.example.neocafeteae1prototype.domain.sealedClasses.AllModels


class HomeFragment : Fragment(), RecyclerItemClickListener {

    private var _binding:FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val menuAdapter by lazy {MenuRecyclerAdapter()}
    private val mainAdapter by lazy {MainRecyclerAdapter(this)}

    private val list = mutableListOf<AllModels>(
        AllModels.Popular("Пончики", "90c", R.drawable.ponchiki, false),
        AllModels.Popular("Апельсиновый сок", "50c", R.drawable.juice, false),
        AllModels.Popular("Чесночный багет с базиликом", "150c", R.drawable.bucket, false)
    )

    private val menuList = mutableListOf<AllModels.Menu>(
        AllModels.Menu("Чай", R.drawable.tea),
        AllModels.Menu("Кофе", R.drawable.coffee),
        AllModels.Menu("Напитки", R.drawable.soda),
        AllModels.Menu("Десерты", R.drawable.desert),
        AllModels.Menu("Выпечка",R.drawable.cake),
    )

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)



        setUpRecyclers()

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun setUpRecyclers() {
        binding.popularRecycler.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            mainAdapter.setList(list)
        }

        binding.menuRecycler.apply{
            adapter = menuAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            menuAdapter.setList(menuList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun itemClicked(item: AllModels) {
        Log.i("TAG", "HomeFragment")
    }
}