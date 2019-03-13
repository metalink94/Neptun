package com.neptun.uran.app.results

import android.os.Handler
import com.neptun.uran.app.models.*
import com.neptun.uran.app.mvp.ViewPresenter
import kotlin.random.Random

class ResultsPresenter : ViewPresenter<ResultsView>() {

    fun onCreate() {
        getView()?.showProgress()
        getView()?.clearAdapter()
        Handler().postDelayed({
            addList()
            getView()?.hideIndicator()
        }, Random.nextLong(1200, 2800))

    }

    private fun addList() {
        val resultsList = mutableListOf<ViewModel>()
        resultsList.add(TitleModel())
        addLastResult(resultsList, "13 March 2019")
        addTwoResult(resultsList)
        addFourResult(resultsList, "10 March 2019")
        addFourResult(resultsList, "8 March 2019")
        addFourResult(resultsList, "5 March 2019")
        addLastResult(resultsList, "3 March 2019")
        getView()?.addItems(resultsList)
        getView()?.hideProgress()
    }

    private fun addFourResult(resultsList: MutableList<ViewModel>, date: String) {
        val random = Random.nextInt(0, 3)
        val random2 = Random.nextInt(0, 5)
        resultsList.add(DividerModel())
        resultsList.add(TimeModel(date))
        resultsList.add(ResultModel("Liverpool", "Barcelona", 0, random, "0/2"))
        resultsList.add(ResultModel("Madrid", "Chelsea", random2 + 1, 2, "3/2"))
        resultsList.add(ResultModel("ЦСКА", "Fulham", 0, random2, "0/3"))
        resultsList.add(ResultModel("Зенит", "Спартак", random, 1, "2/2"))
    }

    private fun addTwoResult(resultsList: MutableList<ViewModel>) {
        val random = Random.nextInt(0, 4)
        resultsList.add(DividerModel())
        resultsList.add(TimeModel("11 March 2019"))
        resultsList.add(ResultModel("Рубин", "Зенит", random, 1, "1/3"))
        resultsList.add(ResultModel("Fulham", "Everton", 0, random + 1, "0/0"))
    }

    private fun addLastResult(resultsList: MutableList<ViewModel>, date: String) {
        val random = Random.nextInt(0, 3)
        val random2 = Random.nextInt(0, 5)
        resultsList.add(DividerModel())
        resultsList.add(TimeModel(date))
        resultsList.add(ResultModel("Real Madrid", "Barcelona", random, 1, "3/2"))
        resultsList.add(ResultModel("Liverpool", "Chelsea", 0, random2, "1/3"))
        resultsList.add(ResultModel("Спартак", "ЦСКА", random + 1, 1, "1/1"))
    }

    fun onRefresh() {
        onCreate()
    }
}
