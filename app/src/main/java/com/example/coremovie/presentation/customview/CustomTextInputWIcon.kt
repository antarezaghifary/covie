package com.example.coremovie.presentation.customview

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.example.coremovie.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class CustomTextInputWIcon @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val tilPassword: TextInputLayout
    private val etPassword: TextInputEditText

    init {
        // Inflate the custom layout
        LayoutInflater.from(context).inflate(R.layout.view_labelled_text_input_w_icon, this, true)

        // Initialize views
        tilPassword = findViewById(R.id.tilPassword)
        etPassword = findViewById(R.id.etPassword)

        // Custom attributes (optional)
        attrs?.let {
            val attributes = context.obtainStyledAttributes(it, R.styleable.CustomPasswordInput)
            val hintText = attributes.getString(R.styleable.CustomPasswordInput_hintTextWIcon)
            val iconRight = attributes.getDrawable(R.styleable.CustomPasswordInput_iconRight)
//            val iconRightMode = attributes.getString(R.styleable.CustomPasswordInput_iconRightMode)
            val iconLeft = attributes.getDrawable(R.styleable.CustomPasswordInput_iconLeft)

            val boxColor =
                attributes.getColor(R.styleable.CustomPasswordInput_boxBackgroundColor, 0)

            tilPassword.hint = hintText ?: "PIN"
            tilPassword.startIconDrawable = iconLeft
            tilPassword.endIconDrawable = iconRight
            tilPassword.endIconMode = TextInputLayout.END_ICON_CUSTOM
//            tilPassword.endIconMode = iconRightMode?.toInt() ?: 0
            tilPassword.boxBackgroundColor = boxColor

            attributes.recycle()
        }
    }

    fun setLeftIcon(icon: Drawable?){
        tilPassword.startIconDrawable = icon
    }

    fun setRightIcon(icon: Drawable?){
        tilPassword.endIconDrawable = icon
    }

    // Optional: Setter methods to configure the view programmatically
    fun setHintText(hint: String) {
        tilPassword.hint = hint
    }

    fun getPassword(): String {
        return etPassword.text?.toString() ?: ""
    }

    fun setPasswordText(password: String) {
        etPassword.setText(password)
    }

    fun setOnPasswordVisibilityToggleClickListener(listener: (View) -> Unit) {
        tilPassword.setEndIconOnClickListener(listener)
    }
}
