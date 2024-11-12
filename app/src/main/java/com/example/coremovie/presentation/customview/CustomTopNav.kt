package com.example.coremovie.presentation.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import com.example.coremovie.R

class CustomTopNav @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    // Define the views
    private val ivBack: ImageView
    private val txtTitle: AppCompatTextView
    private val ivMenu: ImageView

    init {
        // Inflate layout XML
        LayoutInflater.from(context).inflate(R.layout.view_top_navigation, this, true)

        // Find views by ID
        ivBack = findViewById(R.id.ivBack)
        txtTitle = findViewById(R.id.txtTitle)
        ivMenu = findViewById(R.id.ivMenu)

        // Custom attributes (optional)
        attrs?.let {
            val attributes = context.obtainStyledAttributes(it, R.styleable.CustomTopNav)
            val titleText = attributes.getString(R.styleable.CustomTopNav_titleText)
            txtTitle.text = titleText ?: "Default Title"
            attributes.recycle()
        }
    }

    // Optional: Setter for title text
    fun setTitle(title: String) {
        txtTitle.text = title
    }

    // Optional: Set click listeners
    fun setOnBackClickListener(listener: (View) -> Unit) {
        ivBack.setOnClickListener(listener)
    }

    fun setOnMenuClickListener(listener: (View) -> Unit) {
        ivMenu.setOnClickListener(listener)
    }
}
