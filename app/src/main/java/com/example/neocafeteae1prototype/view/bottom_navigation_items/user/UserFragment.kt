package com.example.neocafeteae1prototype.view.bottom_navigation_items.user

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.neocafeteae1prototype.data.Consts
import com.example.neocafeteae1prototype.databinding.FragmentUserBinding
import com.example.neocafeteae1prototype.view.root.BaseFragment
import com.example.neocafeteae1prototype.view.tools.alert_dialog.CustomAlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.vicmikhailau.maskededittext.MaskedFormatter
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat
import java.text.NumberFormat

@AndroidEntryPoint
class UserFragment : BaseFragment<FragmentUserBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        getDataFromSharedPreference(sharedPref)
        with(binding){
            shoppingHistory.setOnClickListener { findNavController().navigate(UserFragmentDirections.actionUserFragmentToUserShoppingHistory()) }

            nameEditText.addTextChangedListener {
                with(sharedPref.edit()) {
                    putString(Consts.USER_NAME, it.toString().trim())
                    apply()
                }
                userNameTextView.text = it.toString()

            }
            exit.setOnClickListener {
                CustomAlertDialog(this@UserFragment::deleteAccount, "Вы точно хотите удалить аккаунт?", "Для обратной регистрации зайдите в приложение")
                    .show(childFragmentManager, "TAG")

            }
        }
    }

    private fun getDataFromSharedPreference(sharedPref: SharedPreferences) {
        val userName = sharedPref.getString(Consts.USER_NAME, "null")
        val numberPhone = sharedPref.getString(Consts.USER_NUMBER, "null")
        val userBirthday = sharedPref.getString(Consts.USER_BIRTHDAY, "")
        with(binding){
            userNameTextView.text = userName
            nameEditText.setText(userName)
            numberPhoneTextView.text = MaskedFormatter("###-###-###").formatString(numberPhone!!)
            birthdayEditText.text = userBirthday
        }
    }

    private fun deleteAccount(){
        FirebaseAuth.getInstance().signOut()
        activity?.finish()
    }

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?): FragmentUserBinding {
        return FragmentUserBinding.inflate(inflater)
    }

    override fun setUpToolbar() {
        binding.include.notification.setOnClickListener {
            findNavController().navigate(
                UserFragmentDirections.actionUserFragmentToNotification5()
            )
        }
    }

}