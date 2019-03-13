package com.neptun.uran.app.splash

import com.neptun.uran.app.mvp.IView

interface SpashView: IView {
    fun setIcon(iconRes: Int)
    fun checkDatabase()
    fun checkCountry()
    fun showResultsScreen()
    fun showMainScreen(webUrl: String?)

}
