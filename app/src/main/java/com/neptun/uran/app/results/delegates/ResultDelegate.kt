package com.neptun.uran.app.results.delegates

import android.view.ViewGroup
import com.neptun.uran.app.R
import com.neptun.uran.app.adapter.DelegateAdapter
import com.neptun.uran.app.models.ResultModel
import com.neptun.uran.app.results.holders.ResultViewHolder
import org.jetbrains.anko.layoutInflater

class ResultDelegate: DelegateAdapter.Delegate<ResultModel, ResultViewHolder>() {
    override fun createViewHolder(parent: ViewGroup): ResultViewHolder {
        val view = parent.context.layoutInflater.inflate(R.layout.result_row, parent, false)
        return ResultViewHolder(view)
    }

    override fun bind(viewHolder: ResultViewHolder, model: ResultModel) {
        viewHolder.bind(model)
    }
}
