package com.example.neocafeteae1prototype.application.registration

import `in`.aabhasjindal.otptextview.OTPListener
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.neocafeteae1prototype.R
import com.example.neocafeteae1prototype.application.tools.BaseFragment
import com.example.neocafeteae1prototype.application.tools.showSnackBar
import com.example.neocafeteae1prototype.application.tools.showToast
import com.example.neocafeteae1prototype.databinding.FragmentReceiveMessageFirebaseBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit


class ReceiveMessageFirebaseFragment : BaseFragment<FragmentReceiveMessageFirebaseBinding>() {

    private var _binding: FragmentReceiveMessageFirebaseBinding? = null
    private lateinit var phoneNumber: String
    private var id: String = ""
    private val args: ReceiveMessageFirebaseFragmentArgs by navArgs()
    private lateinit var firebaseCallback: PhoneAuthProvider.OnVerificationStateChangedCallbacks


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        phoneNumber = args.phoneNumber
        _binding!!.textView5.text = "Код был отправлен на номер $phoneNumber"

        sendMessage()

        listenOTR()
        firebaseCallback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                signIn(p0)
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                _binding?.let {
                    p0.message?.showSnackBar(
                            it.registrationButton,
                            Snackbar.LENGTH_LONG
                    )
                }
                Log.i("TAG", "OnFailed")
            }

            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                id = p0
                "Сообщение отправлено. Ждите".showToast(requireContext(), Toast.LENGTH_LONG)
            }
        }


        _binding?.appCompatButton?.setOnClickListener {
            _binding!!.registrationButton.isEnabled = false
            _binding!!.registrationButton.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.button_disable_custom_item)
        }

    }

    private fun sendMessage() {
        PhoneAuthOptions.newBuilder()
                .setActivity(requireActivity())
                .setPhoneNumber("+996$phoneNumber")
                .setTimeout(30L, TimeUnit.SECONDS)
                .setCallbacks(firebaseCallback)
                .build()
                .apply {
                    PhoneAuthProvider.verifyPhoneNumber(this)
                    Log.i("TAG", "onSendMessage")
                }
    }

    private fun listenOTR() {
        _binding?.otpTextView?.otpListener = object : OTPListener {
            override fun onInteractionListener() {
                _binding?.appCompatButton?.background = ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.button_disable_custom_item
                )
            }

            override fun onOTPComplete(otp: String) {
                _binding?.appCompatButton?.background = ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.button_enable_custom_style
                )
                _binding?.appCompatButton?.isEnabled = true
                signInWithCredential()
                startTimer()
            }
        }
    }

    private fun signInWithCredential() {
        val phoneAuthCredential = PhoneAuthProvider.getCredential(id, _binding?.otpTextView?.otp!!)
        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        findNavController().navigate(ReceiveMessageFirebaseFragmentDirections.actionReceiveMessageFirebaseFragmentToRegistrationBirthdayFragment())
                    }
                }
    }

    private fun startTimer() {
        object : CountDownTimer(30000, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                _binding?.registrationButton?.text = "Отправить повторно ${millisUntilFinished / 1000}"
            }

            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                _binding?.registrationButton?.text = "Отправить повторно"
                _binding?.registrationButton?.isEnabled = true
                _binding?.registrationButton?.background = ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.button_enable_custom_style
                )
            }
        }.start()
    }

    private fun signIn(phone: PhoneAuthCredential) {
        FirebaseAuth.getInstance().signInWithCredential(phone).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                findNavController().navigate(ReceiveMessageFirebaseFragmentDirections.actionReceiveMessageFirebaseFragmentToRegistrationBirthdayFragment())
            }
        }

    }

    override fun inflateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
    ): FragmentReceiveMessageFirebaseBinding {
        return FragmentReceiveMessageFirebaseBinding.inflate(inflater)
    }
}