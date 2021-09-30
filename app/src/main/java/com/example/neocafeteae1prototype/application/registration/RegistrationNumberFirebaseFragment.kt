package com.example.neocafeteae1prototype.application.registration

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.neocafeteae1prototype.R
import com.example.neocafeteae1prototype.application.tools.BaseFragment
import com.example.neocafeteae1prototype.application.tools.Consts
import com.example.neocafeteae1prototype.databinding.FragmentRegistrationNumberFirebaseBinding


class RegistrationNumberFirebaseFragment :
        BaseFragment<FragmentRegistrationNumberFirebaseBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.numberPhone.addTextChangedListener {
            if (it?.length == 9 && binding.name.text.isNotEmpty()) {
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

    fun sendMessage() {
        insertDataToSharedPreference(binding.name.text.toString(), "+996${binding.numberPhone.text.toString()}")
        findNavController().navigate(
                RegistrationNumberFirebaseFragmentDirections.actionRegistrationNumberFirebaseFragmentToReceiveMessageFirebaseFragment(
                        binding.numberPhone.text.toString()
                )
        )
    }

    @SuppressLint("CommitPrefEdits")
    private fun insertDataToSharedPreference(name: String, number: String) {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString(Consts.USER_NAME, name)
            putString(Consts.USER_NUMBER, number)
            apply()
        }

    }

    override fun inflateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
    ): FragmentRegistrationNumberFirebaseBinding {
        return FragmentRegistrationNumberFirebaseBinding.inflate(inflater)
    }
}