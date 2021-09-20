package com.example.neocafeteae1prototype.application.tools

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar

fun Fragment.toast(context: Context, message:String){
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun Fragment.showSnackBar(view: View, message:String, snackbarDuration: Int){
    Snackbar.make(view, message, snackbarDuration)
}


infix fun TextView.text(text : String) {
    this.text= text
}

class basealertdialo<binding : ViewBinding> : DialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }


}
fun View.gone(){
    this.visibility = View.GONE
}

fun String.printNumber(){
    this + "1"
}


