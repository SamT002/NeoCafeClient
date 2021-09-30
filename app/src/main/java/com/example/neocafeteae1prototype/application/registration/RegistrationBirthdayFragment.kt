package com.example.neocafeteae1prototype.application.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.neocafeteae1prototype.application.tools.BaseFragment
import com.example.neocafeteae1prototype.databinding.FragmentRegistrationBirthdayBinding


class RegistrationBirthdayFragment : BaseFragment<FragmentRegistrationBirthdayBinding>() {
    private lateinit var birthdayDate: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.skip.setOnClickListener { nextPage() }
        binding.signIn.setOnClickListener {
            birthdayDate = binding.editText.text.toString()
            nextPage()
        }
    }

    fun nextPage() {
        findNavController().navigate(RegistrationBirthdayFragmentDirections.actionRegistrationBirthdayFragmentToBottomViewFragment3())
    }

    override fun inflateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
    ): FragmentRegistrationBirthdayBinding {
        return FragmentRegistrationBirthdayBinding.inflate(inflater)
    }
}