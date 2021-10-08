package com.example.neocafeteae1prototype.application.tools.alert_dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.example.neocafeteae1prototype.R
import com.example.neocafeteae1prototype.application.tools.notVisible
import com.example.neocafeteae1prototype.application.tools.visible
import com.example.neocafeteae1prototype.databinding.BonusAlertDialogItemBinding

class BonusAlertDialog(val bonus: Int,val function: (String) -> Unit) :
    BaseAlertDialog<BonusAlertDialogItemBinding>() {

    override fun onResume() {
        super.onResume()
        val width = resources.getDimensionPixelSize(R.dimen.alert_dialog_width)
        val height = resources.getDimensionPixelSize(R.dimen.alert_dialog_height)
        dialog?.window?.setLayout(width, height)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bonusEditText.addTextChangedListener {
            if(it.toString() != ""){
                if (it.toString().toInt() > bonus){
                    binding.alertMessag.visible()
                }else {
                    binding.alertMessag.notVisible()
                }
            }

        }

        binding.ready.setOnClickListener {
            function(binding.bonusEditText.text.toString())
        }
    }

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): BonusAlertDialogItemBinding {
        return BonusAlertDialogItemBinding.inflate(inflater)
    }


}