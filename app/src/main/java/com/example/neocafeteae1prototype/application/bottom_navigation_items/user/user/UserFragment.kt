package com.example.neocafeteae1prototype.application.bottom_navigation_items.user.user

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.neocafeteae1prototype.application.tools.BaseFragment
import com.example.neocafeteae1prototype.application.tools.Consts
import com.example.neocafeteae1prototype.application.tools.alert_dialog.CustomAlertDialog
import com.example.neocafeteae1prototype.databinding.FragmentUserBinding
import com.google.firebase.auth.FirebaseAuth


class UserFragment : BaseFragment<FragmentUserBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        getDataFromSharedPreference(sharedPref)


        binding.shoppingHistory.setOnClickListener {
            findNavController().navigate(UserFragmentDirections.actionUserFragmentToUserShoppingHistory())
        }
        binding.include.notification.setOnClickListener {
            findNavController().navigate(UserFragmentDirections.actionUserFragmentToNotification5())
        }
        binding.nameEditText.addTextChangedListener {
            with(sharedPref.edit()){
                putString(Consts.USER_NAME, it.toString().trim())
                apply()
            }
        }
        binding.exit.setOnClickListener {
            CustomAlertDialog(this::deleteAccount, "Вы точно хотите удалить аккаунт?", "Для обратной регистрации зайдите в приложение")
                .show(childFragmentManager, "TAG")

        }
    }

    private fun getDataFromSharedPreference(sharedPref: SharedPreferences) {
        val userName = sharedPref.getString(Consts.USER_NAME, "null")
        val numberPhone = sharedPref.getString(Consts.USER_NUMBER, "null")
        val userBirthday = sharedPref.getString(Consts.USER_BIRTHDAY, "")
        binding.nameEditText.setText(userName)
        binding.numberPhone.text = numberPhone
        binding.birthdayEditText.text = userBirthday
    }

    private fun deleteAccount(){
        FirebaseAuth.getInstance().signOut()
        activity?.finish()
    }

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?): FragmentUserBinding {
        return FragmentUserBinding.inflate(inflater)
    }

}