package com.example.neocafeteae1prototype.application.root

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.neocafeteae1prototype.R
import com.example.neocafeteae1prototype.databinding.ActivityMainBinding
import com.example.neocafeteae1prototype.domain.internet_checker.ConnectionLiveData
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var connectionLiveData: ConnectionLiveData


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        connectionLiveData = ConnectionLiveData(this)

        val snackbar = Snackbar.make(binding.view, "No Internet", Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("Закрыть") {
            snackbar.dismiss()
        }
            .setBackgroundTint(ContextCompat.getColor(this, R.color.red))

        connectionLiveData.observe(this, {
            if (it == false) snackbar.show()
            else snackbar.dismiss()
        })

    }


}