package com.example.neocafeteae1prototype

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.neocafeteae1prototype.databinding.ActivityNumberRegisterBinding
import com.example.neocafeteae1prototype.registration.CheckMessageFromFirebase
import com.example.neocafeteae1prototype.registration.NumberRegisterActivity

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity(Intent(this, NumberRegisterActivity::class.java))
    }
}