package com.neptun.uran.app.results

import com.neptun.uran.app.mvp.IView

interface ResultsView: IView {
    fun showProgress()
    fun hideProgress()
    fun addItems(it: List<Any>)
    fun hideIndicator()
    fun clearAdapter()
    fun showError()

}
