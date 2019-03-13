package com.neptun.uran.app.results.delegates

import android.view.ViewGroup
import com.neptun.uran.app.R
import com.neptun.uran.app.adapter.DelegateAdapter
import com.neptun.uran.app.models.DividerModel
import com.neptun.uran.app.results.holders.DividerViewHolder
import org.jetbrains.anko.layoutInflater

class DividerDelegate: DelegateAdapter.Delegate<DividerModel, DividerViewHolder>() {
    override fun createViewHolder(parent: ViewGroup): DividerViewHolder {
        val view = parent.context.layoutInflater.inflate(R.layout.divider_row, parent, false)
        return DividerViewHolder(view)
    }

    override fun bind(viewHolder: DividerViewHolder?, model: DividerModel?) {

    }
}
