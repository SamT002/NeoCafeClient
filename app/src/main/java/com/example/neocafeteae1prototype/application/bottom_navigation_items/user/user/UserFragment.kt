package com.example.neocafeteae1prototype.application.bottom_navigation_items.user.user

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.neocafeteae1prototype.application.tools.BaseFragment
import com.example.neocafeteae1prototype.application.tools.Consts
import com.example.neocafeteae1prototype.databinding.FragmentUserBinding


class UserFragment : BaseFragment<FragmentUserBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataFromSharedPreference()

        binding.shoppingHistory.setOnClickListener {
            findNavController().navigate(UserFragmentDirections.actionUserFragmentToUserShoppingHistory())
        }
        binding.notification.setOnClickListener {
            findNavController().navigate(UserFragmentDirections.actionUserFragmentToNotification5())
        }

    }

    private fun getDataFromSharedPreference() {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        val userName = sharedPref.getString(Consts.USER_NAME, "null")
        val numberPhone = sharedPref.getString(Consts.USER_NUMBER, "null")
        val userBirthday = sharedPref.getString(Consts.USER_BIRTHDAY, "")
        binding.nameEditText.setText(userName)
        binding.numberPhone.setText(numberPhone)
        binding.birthdayEditText.setText(userBirthday)
    }

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?): FragmentUserBinding {
        return FragmentUserBinding.inflate(inflater)
    }

}