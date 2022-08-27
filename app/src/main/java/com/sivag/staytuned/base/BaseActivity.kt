package com.sivag.staytuned.base

import android.content.Context
import android.os.Build.VERSION_CODES.O
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by Siva G Gurusamy on 26/Aug/2022
 * email : sivaguru3161@gmail.com
 */
abstract class BaseActivity : AppCompatActivity() {



    fun showKeyboard() {
        val inputMethodMngr = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodMngr.toggleSoftInput(InputMethodManager.SHOW_FORCED, O)
    }

    fun hideKeyboard() {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        var view: View? = currentFocus
        if (view == null) {
            view = View(this)
        }
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}