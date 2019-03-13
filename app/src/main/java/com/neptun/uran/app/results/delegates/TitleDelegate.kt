package com.neptun.uran.app.results.delegates

import android.view.ViewGroup
import com.neptun.uran.app.R
import com.neptun.uran.app.adapter.DelegateAdapter
import com.neptun.uran.app.models.TitleModel
import com.neptun.uran.app.results.holders.TitleViewHolder
import org.jetbrains.anko.layoutInflater

class TitleDelegate: DelegateAdapter.Delegate<TitleModel, TitleViewHolder>() {

    override fun createViewHolder(parent: ViewGroup): TitleViewHolder {
        val view = parent.context.layoutInflater.inflate(R.layout.title_row, parent, false)
        return TitleViewHolder(view)
    }

    override fun bind(viewHolder: TitleViewHolder, model: TitleModel) {
        viewHolder.bind(model)
    }
}
