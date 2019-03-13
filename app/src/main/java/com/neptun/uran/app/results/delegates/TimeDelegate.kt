package com.neptun.uran.app.results.delegates

import android.view.ViewGroup
import com.neptun.uran.app.R
import com.neptun.uran.app.adapter.DelegateAdapter
import com.neptun.uran.app.models.TimeModel
import com.neptun.uran.app.results.holders.TimeViewHolder
import org.jetbrains.anko.layoutInflater

class TimeDelegate: DelegateAdapter.Delegate<TimeModel, TimeViewHolder>() {
    override fun createViewHolder(parent: ViewGroup): TimeViewHolder {
        val view = parent.context.layoutInflater.inflate(R.layout.time_row, parent, false)
        return TimeViewHolder(view)
    }

    override fun bind(viewHolder: TimeViewHolder, model: TimeModel) {
        viewHolder.bind(model)
    }
}
