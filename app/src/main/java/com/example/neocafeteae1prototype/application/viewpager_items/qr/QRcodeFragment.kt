package com.example.neocafeteae1prototype.application.viewpager_items.qr

import android.Manifest
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ScanMode
import com.example.neocafeteae1prototype.application.tools.Consts
import com.example.neocafeteae1prototype.application.viewpager_items.home.HomeFragment
import com.example.neocafeteae1prototype.databinding.FragmentQrcodeBinding
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener


class QRcodeFragment : Fragment() {

    private var _binding: FragmentQrcodeBinding? = null
    private val binding get() = _binding!!
    private lateinit var scanner: CodeScanner

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentQrcodeBinding.inflate(inflater, container, false)
        scanner = CodeScanner(requireContext(), binding.scannerView)

        Dexter.withContext(context)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(object : PermissionListener {
                    override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                        QRcodeChecker() // Если дано разрешение оно срабатывает
                        scanner.startPreview()
                    }

                    override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
                                    != PackageManager.PERMISSION_GRANTED // идет двойная проверка я хз на что
                            ) {
                                showMessageOKCancel("You need to allow access permissions"
                                ) { dialog, which ->
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        requestPermission()
                                    }
                                }
                            }
                        }
                    }

                    override fun onPermissionRationaleShouldBeShown(
                            p0: PermissionRequest?,
                            p1: PermissionToken?,
                    ) {

                    }

                }).check()

        return binding.root
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
                requireActivity(), arrayOf(Manifest.permission.CAMERA),
                Consts.PERMISSION_REQUEST_CODE
        )
    }

    private fun showMessageOKCancel(message: String, okListener: DialogInterface.OnClickListener) {

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

        scanner.decodeCallback = DecodeCallback {
            requireActivity().runOnUiThread {
                childFragmentManager.beginTransaction().apply {
                    replace((requireView().parent as ViewGroup).id, HomeFragment())
                    setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    addToBackStack(null);
                    commit()
                }
            }
        }


    }
}