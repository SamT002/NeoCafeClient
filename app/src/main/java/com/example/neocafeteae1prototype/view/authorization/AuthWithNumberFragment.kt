package com.example.neocafeteae1prototype.view.authorization

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.neocafeteae1prototype.R
import com.example.neocafeteae1prototype.data.Consts
import com.example.neocafeteae1prototype.databinding.FragmentAuthWithNumberBinding
import com.example.neocafeteae1prototype.view.root.BaseFragment
import com.example.neocafeteae1prototype.view.tools.logging


class AuthWithNumberFragment : BaseFragment<FragmentAuthWithNumberBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.numberPhoneTextView.addTextChangedListener {
            if (it?.length == 11) {
                binding.nextButton.apply {
                    background = ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.button_enable_custom_style
                    )
                    isEnabled = true
                }
            } else {
                binding.nextButton.apply {
                    background = ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.button_disable_custom_item
                    )
                    isEnabled = false
                }
            }
        }

        binding.nextButton.setOnClickListener { sendMessage() }

    }

    private fun sendMessage() {
        val phoneNumber = binding.numberPhoneTextView.text.toString()
        insertDataToSharedPreference("+996${phoneNumber}")
        findNavController().navigate(AuthWithNumberFragmentDirections.actionAuthWithNumberFragmentToGetMessageAuthorization("+996${phoneNumber}"))
    }

    private fun insertDataToSharedPreference(number: String) {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString(Consts.USER_NUMBER, number)
            apply()
        }

    }

    override fun setUpToolbar() {
        "AuthWithNumberFragmentToolbar".logging()
    }

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?): FragmentAuthWithNumberBinding {
        return FragmentAuthWithNumberBinding.inflate(inflater)
    }
}