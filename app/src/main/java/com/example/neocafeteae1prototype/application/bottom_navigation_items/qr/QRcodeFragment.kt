package com.example.neocafeteae1prototype.application.bottom_navigation_items.qr

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ScanMode
import com.example.neocafeteae1prototype.R
import com.example.neocafeteae1prototype.application.tools.BaseFragment
import com.example.neocafeteae1prototype.application.tools.Consts
import com.example.neocafeteae1prototype.databinding.FragmentQrcodeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener


class QRcodeFragment : BaseFragment<FragmentQrcodeBinding>() {

    private lateinit var scanner: CodeScanner

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        scanner = CodeScanner(requireContext(), binding.scannerView)

        binding.include.notification.setOnClickListener {
            findNavController().navigate(QRcodeFragmentDirections.actionQRcodeFragmentToNotification4())
        }

        Dexter.withContext(context) // Dexter библиотека которая помогает работать с permisssions
            .withPermission(Manifest.permission.CAMERA)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                    QRcodeChecker() // Если дано разрешение оно срабатывает
                    scanner.startPreview()
                }

                override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (ContextCompat.checkSelfPermission(
                                requireContext(),
                                Manifest.permission.CAMERA
                            )
                            != PackageManager.PERMISSION_GRANTED // идет двойная проверка я хз на что
                        ) {

                        }
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: PermissionRequest?,
                    p1: PermissionToken?,
                ) {

                }

            }).check()

    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(), arrayOf(Manifest.permission.CAMERA),
            Consts.PERMISSION_REQUEST_CODE
        )
    }

    private fun QRcodeChecker() {

        scanner.apply {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS
            autoFocusMode = AutoFocusMode.CONTINUOUS
            scanMode = ScanMode.SINGLE
            isAutoFocusEnabled = true
            isFlashEnabled = false
        }

        scanner.decodeCallback = DecodeCallback { result ->
            requireActivity().runOnUiThread {

            }
        }


    }

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentQrcodeBinding {
        return FragmentQrcodeBinding.inflate(inflater)
    }
}