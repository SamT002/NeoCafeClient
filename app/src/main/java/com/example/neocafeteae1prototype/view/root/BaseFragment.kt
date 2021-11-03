package com.example.neocafeteae1prototype.view.root

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.neocafeteae1prototype.view.tools.logging

abstract class BaseFragment<Binding : ViewBinding> : Fragment() {

    private var _binding: Binding? = null
    val binding get() = _binding!!
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        _binding = inflateView(inflater, container)
        setUpToolbar()
        sharedPreferences = activity?.getPreferences(Context.MODE_PRIVATE)!!
        return binding.root
    }

    abstract fun inflateView(inflater: LayoutInflater, container: ViewGroup?): Binding

    override fun onDestroyView() {
        super.onDestroyView()
        "OnDestroyView".logging()
        _binding = null
    }

    abstract fun setUpToolbar()
}