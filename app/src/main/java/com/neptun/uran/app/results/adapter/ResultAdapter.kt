package com.neptun.uran.app.results.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.neptun.uran.app.adapter.DelegateAdapter
import com.neptun.uran.app.models.DividerModel
import com.neptun.uran.app.models.ResultModel
import com.neptun.uran.app.models.TimeModel
import com.neptun.uran.app.models.TitleModel
import com.neptun.uran.app.results.delegates.DividerDelegate
import com.neptun.uran.app.results.delegates.ResultDelegate
import com.neptun.uran.app.results.delegates.TimeDelegate
import com.neptun.uran.app.results.delegates.TitleDelegate

class ResultsAdapter(val delegateAdapter: DelegateAdapter) : ResultsAdapterImpl {

    override fun removeItems(items: List<*>) {
        delegateAdapter.removeItems(items)
    }

    override fun addItems(items: List<*>) {
        delegateAdapter.addItems(items)
    }

    override fun clear() {
        delegateAdapter.clearItems()
    }

    override fun removeItem(item: Any) {
        delegateAdapter.removeItem(item)
    }

    override fun addItems(pos: Int, items: List<*>) {
        delegateAdapter.addItems(pos, items)
    }

    override fun addItem(pos: Int, item: Any) {
        delegateAdapter.addItem(pos, item)
    }

    override fun size(): Int {
        return delegateAdapter.itemCount
    }

    override fun getItem(pos: Int): Any {
        return delegateAdapter.getItem(pos)
    }

    override fun getRecyclerViewAdapter(): RecyclerView.Adapter<*> {
        return delegateAdapter
    }
}

class ResultsBuilder : ResultsBuilderImpl {

    var recyclerView: RecyclerView? = null
    val builder = DelegateAdapter.Builder()
    var onClick: (View) -> Unit = {}

    override fun setRecycler(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView
    }

    override fun setOnClickListener(onClickListener: View.OnClickListener) {
        onClick = { onClickListener.onClick(it) }
    }

    override fun build(): ResultsAdapterImpl {
        return ResultsAdapter(createDelegateAdapter())
    }

    private fun createDelegateAdapter(): DelegateAdapter {
        addTitleRow()
        addDividerRow()
        addDateRow()
        addResultRow()
        return builder.build()
    }

    private fun addResultRow() {
        builder.addDelegate(ResultModel::class.java, ResultDelegate())
    }

    private fun addDateRow() {
        builder.addDelegate(TimeModel::class.java, TimeDelegate())
    }

    private fun addDividerRow() {
        builder.addDelegate(DividerModel::class.java, DividerDelegate())
    }

    private fun addTitleRow() {
        builder.addDelegate(TitleModel::class.java, TitleDelegate())
    }
}
