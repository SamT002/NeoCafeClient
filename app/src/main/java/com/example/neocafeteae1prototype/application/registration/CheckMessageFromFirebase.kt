package com.example.neocafeteae1prototype.application.registration

import `in`.aabhasjindal.otptextview.OTPListener
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.core.content.ContextCompat
import com.example.neocafeteae1prototype.application.tools.BaseActivity
import com.example.neocafeteae1prototype.application.viewPager_root.MainActivity
import com.example.neocafeteae1prototype.R
import com.example.neocafeteae1prototype.databinding.ActivityCheckMessageFromFirebaseBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class CheckMessageFromFirebase : BaseActivity() {

    private lateinit var binding:ActivityCheckMessageFromFirebaseBinding
    private lateinit var phoneNumber:String
    private var id:String = ""
    private lateinit var firebaseCallback:PhoneAuthProvider.OnVerificationStateChangedCallbacks

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckMessageFromFirebaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        phoneNumber = intent.getStringExtra("numberPhone")!!
        binding.textView5.text = "Код был отправлен на номер $phoneNumber"
        listenOTR()

        firebaseCallback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                signIn(p0)
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                showSnackBar(p0.message!!, binding.registrationButton)
            }

            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                id = p0
//                showToast("Сообщение отправлено. Ждите")
            }
        }
        sendMessage()

        binding.appCompatButton.setOnClickListener {
            binding.registrationButton.isEnabled = false
            binding.registrationButton.background = ContextCompat.getDrawable(this@CheckMessageFromFirebase, R.drawable.button_disable_custom_item)
            startTimer()
        }

    }

    private fun sendMessage() {
        PhoneAuthOptions.newBuilder()
            .setActivity(this)
            .setPhoneNumber(phoneNumber)
            .setTimeout(30L, TimeUnit.SECONDS)
            .setCallbacks(firebaseCallback)
            .build()
            .apply {
                PhoneAuthProvider.verifyPhoneNumber(this)
            }
    }

    private fun listenOTR() {
        binding.otpTextView.otpListener = object : OTPListener {
            override fun onInteractionListener() {
                binding.appCompatButton.background = ContextCompat.getDrawable(this@CheckMessageFromFirebase, R.drawable.button_disable_custom_item)
            }

            override fun onOTPComplete(otp: String) {
                binding.appCompatButton.background = ContextCompat.getDrawable(this@CheckMessageFromFirebase, R.drawable.button_enable_custom_style)
                binding.appCompatButton.isEnabled = true
                signInWithCredential()
            }
        }
    }

    private fun signInWithCredential() {
            val phoneAuthCredential = PhoneAuthProvider.getCredential(id, binding.otpTextView.otp!!)
            FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                }
        }

    private fun startTimer() {
        object: CountDownTimer(30000, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                binding.registrationButton.text = "Отправить повторно ${millisUntilFinished / 1000}"
            }

            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                binding.registrationButton.text = "Отправить повторно"
                binding.registrationButton.isEnabled = true
                binding.registrationButton.background = ContextCompat.getDrawable(this@CheckMessageFromFirebase, R.drawable.button_enable_custom_style)
            }
        }.start()
    }

    private fun signIn(phone: PhoneAuthCredential) {
        FirebaseAuth.getInstance().signInWithCredential(phone).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }
}
