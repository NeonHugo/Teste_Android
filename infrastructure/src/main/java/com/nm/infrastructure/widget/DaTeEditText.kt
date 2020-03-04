package com.nm.infrastructure.widget

import android.app.DatePickerDialog
import android.content.Context
import android.graphics.Rect
import android.text.InputType
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View.OnClickListener
import android.view.View.OnTouchListener
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.AppCompatEditText
import java.text.SimpleDateFormat
import java.util.*

class DaTeEditText : AppCompatEditText {

    constructor(context: Context?) : super(context) {
        initialize(null, 0)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initialize(attrs, 0)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initialize(attrs, defStyleAttr)
    }

    private fun initialize(attrs: AttributeSet?, defStyleAttr: Int) {
        setDefaultValues()
        inputType = InputType.TYPE_NULL
        setOnClickListener(dateListener)
    }

    private fun setDefaultValues() {
        setOnTouchListener(touchPerformClick)
    }

    private var dateListener = OnClickListener {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        val mConteudo = text.toString()
        val mDate = Calendar.getInstance()

        try {
            mDate.time = dateFormat.parse(mConteudo)
        } catch (e: Exception) {
        }

        val mAno = mDate[Calendar.YEAR]
        val mMes = mDate[Calendar.MONTH]
        val mDia = mDate[Calendar.DAY_OF_MONTH]

        val mDatePicker = DatePickerDialog(
            context,
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                val cAux = Calendar.getInstance()
                cAux.set(year, month, dayOfMonth)

                val tAux = dateFormat.format(cAux.time)

                setText(tAux)
            },
            mAno,
            mMes,
            mDia
        )

        mDatePicker.show()
    }

    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)

        if (focused) {
            val imm =
                context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(windowToken, 0)
        }
    }

    private val touchPerformClick = OnTouchListener { v, event ->
        if (MotionEvent.ACTION_UP == event.action) {
            requestFocus()
            performClick()
        }
        true
    }
}