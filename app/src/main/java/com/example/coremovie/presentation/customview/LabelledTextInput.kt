package com.example.coremovie.presentation.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.example.coremovie.R

class LabelledTextInput @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val labelTextView: TextView
    private val inputEditText: EditText

    init {
        // Set orientation to vertical
        orientation = VERTICAL
        // Inflate layout
        LayoutInflater.from(context).inflate(R.layout.view_labelled_text_input, this, true)

        // Initialize TextView and EditText
        labelTextView = findViewById(R.id.labelTextView)
        inputEditText = findViewById(R.id.inputEditText)

        // Custom attributes (optional)
        if (attrs != null) {
            val attributes = context.obtainStyledAttributes(attrs, R.styleable.LabelledTextInput)
            val labelText = attributes.getString(R.styleable.LabelledTextInput_labelText) ?: "Label"
            val hintText = attributes.getString(R.styleable.LabelledTextInput_hintText) ?: "Input here"
            attributes.recycle()

            // Set text and hint
            labelTextView.text = labelText
            inputEditText.hint = hintText
        }
    }

    // Provide getters and setters
    var labelText: String
        get() = labelTextView.text.toString()
        set(value) {
            labelTextView.text = value
        }

    var inputText: String
        get() = inputEditText.text.toString()
        set(value) {
            inputEditText.setText(value)
        }
}
