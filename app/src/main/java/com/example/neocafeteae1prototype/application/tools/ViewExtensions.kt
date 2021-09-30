package com.example.neocafeteae1prototype.application.tools

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.neocafeteae1prototype.R
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso


fun String.showToast(context: Context, duration: Int) {
    Toast.makeText(context, this, duration).show()
}

fun String.logging() {
    Log.i("TAG", this)
}

fun String.showSnackBar(view: View, duration: Int) {
    Snackbar.make(view, this, duration).show()
}


infix fun TextView.text(text: String) {
    this.text = text
}

fun ImageView.loadWithPicasso(image: String) {
    Picasso.with(this.context)
        .load(image)
        .into(this)

}


fun TextView.setWhiteColor(context: Context) {
    this.setTextColor(ContextCompat.getColor(context, R.color.white))
}



