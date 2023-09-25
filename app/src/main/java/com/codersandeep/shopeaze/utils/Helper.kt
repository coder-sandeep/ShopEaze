package com.codersandeep.shopeaze.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

class Helper {
    companion object{
        fun hideKeyboard(view: View){
            try {
                val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }catch (e: Exception){

            }
        }
    }
}