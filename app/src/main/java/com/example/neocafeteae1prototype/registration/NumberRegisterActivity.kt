package com.example.neocafeteae1prototype.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.example.neocafeteae1prototype.R
import com.example.neocafeteae1prototype.databinding.ActivityNumberRegisterBinding

class NumberRegisterActivity : AppCompatActivity() {

    private lateinit var binding:ActivityNumberRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNumberRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.numberPhone.addTextChangedListener {
            if (it?.length == 9 && binding.name.text.isNotEmpty()) {
                binding.nextButton.apply {
                    background = ContextCompat.getDrawable(this@NumberRegisterActivity, R.drawable.button_enable_custom_style)
                    isEnabled = true
                }
            } else{
            binding.nextButton.apply {
                background = ContextCompat.getDrawable(this@NumberRegisterActivity, R.drawable.button_disable_custom_item)
                isEnabled = false
            }
        }
        }




    }

    fun onSendMessage(view: View) {
        startActivity(Intent(this, CheckMessageFromFirebase::class.java))
    }


}