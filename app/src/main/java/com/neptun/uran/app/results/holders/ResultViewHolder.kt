package com.neptun.uran.app.results.holders

import android.support.v7.widget.RecyclerView
import android.view.View
import com.neptun.uran.app.models.ResultModel
import kotlinx.android.synthetic.main.result_row.view.*

class ResultViewHolder(view: View): RecyclerView.ViewHolder(view) {

    fun bind(model: ResultModel) {
        itemView.team.text = String.format("${model.firstTeam} - ${model.secondTeam}")
        itemView.score.text = String.format("${model.firstScore} : ${model.secondScore}")
        itemView.coef.text = model.coeff
    }
}
