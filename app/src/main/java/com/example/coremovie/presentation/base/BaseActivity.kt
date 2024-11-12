package com.example.coremovie.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initOnClick()
        collectState()
    }

    abstract fun initOnClick()
    abstract fun collectState()

    fun LinearLayout.isShowing() = this.visibility == View.VISIBLE

    fun hideSoftKeyboard() {
        val view: View? = this.currentFocus

        view?.let {
            val inputMethodManager =
                getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0)
        }
    }

    fun showSoftKeyboard(view: View) {
        val mgr = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        mgr.showSoftInput(view, InputMethodManager.SHOW_FORCED)
    }
}