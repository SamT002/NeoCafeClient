package com.example.neocafeteae1prototype.view.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.neocafeteae1prototype.R
import com.google.firebase.auth.FirebaseAuth

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onResume() {
        super.onResume()
        Handler(Looper.getMainLooper()).postDelayed({
            nextFragment()
        }, 1500)
    }

    private fun nextFragment() {
        if (FirebaseAuth.getInstance().currentUser == null) {
//            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToBottomViewFragment3())
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToSignInOrRegistrationFragment())
        } else {
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToBottomViewFragment3())
        }
    }
}