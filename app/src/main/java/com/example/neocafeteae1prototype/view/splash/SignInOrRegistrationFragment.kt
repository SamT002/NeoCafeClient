package com.example.neocafeteae1prototype.view.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.neocafeteae1prototype.databinding.FragmentSignInOrRegistrationBinding
import com.example.neocafeteae1prototype.view.root.BaseFragment
import com.example.neocafeteae1prototype.view.tools.logging


class SignInOrRegistrationFragment : BaseFragment<FragmentSignInOrRegistrationBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            signIn.setOnClickListener{
                findNavController().navigate(SignInOrRegistrationFragmentDirections.actionSignInOrRegistrationFragmentToAuthWithNumberFragment())
            }
            register.setOnClickListener{
                findNavController().navigate(SignInOrRegistrationFragmentDirections.actionSignInOrRegistrationFragmentToRegistrationNumberFirebaseFragment())
            }
        }
    }

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?): FragmentSignInOrRegistrationBinding {
        return FragmentSignInOrRegistrationBinding.inflate(inflater)
    }

    override fun setUpToolbar() {
        "SignInOrRegistrationFragment".logging()
    }

}