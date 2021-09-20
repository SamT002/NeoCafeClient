package com.example.neocafeteae1prototype.application.viewPager_root

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.neocafeteae1prototype.R
import com.example.neocafeteae1prototype.application.tools.adapters.ViewPagerAdapter
import com.example.neocafeteae1prototype.application.registration.NumberRegisterActivity
import com.example.neocafeteae1prototype.application.viewpager_items.branches.BranchFragment
import com.example.neocafeteae1prototype.application.viewpager_items.home.HomeFragment
import com.example.neocafeteae1prototype.application.viewpager_items.qr.QRcodeFragment
import com.example.neocafeteae1prototype.application.viewpager_items.shopping.ShoppingFragment
import com.example.neocafeteae1prototype.application.viewpager_items.user.UserFragment
import com.example.neocafeteae1prototype.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val listFragments = listOf<Fragment>(HomeFragment(), ShoppingFragment(), QRcodeFragment(), BranchFragment(), UserFragment())


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (FirebaseAuth.getInstance().currentUser == null) {
            startActivity(Intent(this, NumberRegisterActivity::class.java))
        }

        setupUI()


    }

    private fun setupUI() {
        binding.viewPager2.isUserInputEnabled = false // disable swiping of ViewPager


        binding.viewPager2.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle, listFragments)

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position -> // set Icon to Tab Layout by position
            when (position) {
                0 -> tab.setIcon(R.drawable.custom_home_item)
                1 -> tab.setIcon(R.drawable.custom_shoppingcart)
                2 -> tab.setIcon(R.drawable.ic_qrcode)
                3 -> tab.setIcon(R.drawable.custom_mapping_item)
                4 -> tab.setIcon(R.drawable.custom_user)
            }
        }.attach()

    }

}