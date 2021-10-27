package com.example.neocafeteae1prototype.view.bottom_navigation_items.qr

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.neocafeteae1prototype.databinding.FragmentCheckTableBinding


class CheckTableFragment : Fragment() {

    private var _binding: FragmentCheckTableBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCheckTableBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}