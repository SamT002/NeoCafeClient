package com.example.neocafeteae1prototype.application.registration

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
import com.example.neocafeteae1prototype.databinding.FragmentRegistrationBirthdayBinding
import com.vicmikhailau.maskededittext.MaskedFormatter


class RegistrationBirthdayFragment : BaseFragment<FragmentRegistrationBirthdayBinding>() {
    private lateinit var birthdayDate: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.skip.setOnClickListener { nextPage() }
        binding.signIn.setOnClickListener {
            val formatter = MaskedFormatter("###-###-###").formatString(binding.editText.text.toString())?.unMaskedString
            insertDataToSharedPreference(formatter!!)
            nextPage()
        }

        binding.editText.addTextChangedListener {
            if (it?.length == 10){
                binding.signIn.apply{
                    background = ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.button_enable_custom_style
                    )
                    isEnabled = true
                }
            }else{
                binding.signIn.apply {
                    background = ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.button_disable_custom_item
                    )
                    isEnabled = false
                }
            }
        }
    }

    private fun insertDataToSharedPreference(birthday: String){
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString(Consts.USER_BIRTHDAY, birthday)
            apply()
        }

    }

    private fun nextPage() {
        findNavController().navigate(RegistrationBirthdayFragmentDirections.actionRegistrationBirthdayFragmentToBottomViewFragment3())
    }

    override fun inflateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
    ): FragmentRegistrationBirthdayBinding {
        return FragmentRegistrationBirthdayBinding.inflate(inflater)
    }
}