package com.example.neocafeteae1prototype.application.registration

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.example.neocafeteae1prototype.application.tools.BaseActivity
import com.example.neocafeteae1prototype.R
import com.example.neocafeteae1prototype.databinding.ActivityNumberRegisterBinding

class NumberRegisterActivity : BaseActivity() {

    private lateinit var binding:ActivityNumberRegisterBinding
    private val intentToMessageActivity by lazy {Intent(this, CheckMessageFromFirebase::class.java)}

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
        insertDataToSharedPreferences("name", binding.name.text.toString())
        intentToMessageActivity.putExtra("numberPhone","+996${binding.numberPhone.text.toString()}")
        startActivity(intentToMessageActivity)
    }


}