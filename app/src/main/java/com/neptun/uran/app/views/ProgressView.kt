package com.neptun.uran.app.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.neptun.uran.app.R

class ProgressView
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0):
    RelativeLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(getContext(), R.layout.progress_view, this)
    }
}
