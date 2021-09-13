package com.example.neocafeteae1prototype.registration

import `in`.aabhasjindal.otptextview.OTPListener
import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import androidx.core.content.ContextCompat
import com.example.neocafeteae1prototype.R
import com.example.neocafeteae1prototype.databinding.ActivityCheckMessageFromFirebaseBinding

class CheckMessageFromFirebase : AppCompatActivity() {

    private lateinit var binding:ActivityCheckMessageFromFirebaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckMessageFromFirebaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.otpTextView.otpListener = object : OTPListener{
            override fun onInteractionListener() {
                binding.appCompatButton.background = ContextCompat.getDrawable(this@CheckMessageFromFirebase, R.drawable.button_disable_custom_item)
            }

            override fun onOTPComplete(otp: String) {
                binding.appCompatButton.background = ContextCompat.getDrawable(this@CheckMessageFromFirebase, R.drawable.button_enable_custom_style)
                binding.appCompatButton.isEnabled = true
            }
        }

        binding.appCompatButton.setOnClickListener {
            binding.registrationButton.isEnabled = false
            binding.registrationButton.background = ContextCompat.getDrawable(this@CheckMessageFromFirebase, R.drawable.button_disable_custom_item)
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

    }
}