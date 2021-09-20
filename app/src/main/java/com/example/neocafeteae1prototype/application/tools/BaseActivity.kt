package com.example.neocafeteae1prototype.application.tools

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity : AppCompatActivity(){

    private val editor:SharedPreferences.Editor by lazy {
        getPreferences(Context.MODE_PRIVATE).edit()
    }



    fun showSnackBar(message: String, view: View){
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
    }

    @SuppressLint("CommitPrefEdits")
    fun insertDataToSharedPreferences(key:String, dataToSave:String){

        editor.apply {
            putString(key, dataToSave)
            apply()
        }

    }
}