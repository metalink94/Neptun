package com.neptun.uran.app.results.holders

import android.support.v7.widget.RecyclerView
import android.view.View
import com.neptun.uran.app.models.TimeModel
import kotlinx.android.synthetic.main.time_row.view.*

class TimeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(model: TimeModel) {
        itemView.time.text = model.date
    }
}
