package com.neptun.uran.app.splash

import com.google.firebase.database.DataSnapshot
import com.neptun.uran.app.BuildConfig
import com.neptun.uran.app.Constants
import com.neptun.uran.app.R
import com.neptun.uran.app.mvp.ViewPresenter

class SplashPresenter: ViewPresenter<SpashView>() {

    var countryISO: String? = null
    var inCorrectCountry = false
    private var list: MutableList<String> = mutableListOf()

    fun onCreate() {
        setIcon()
        getView()?.checkCountry()
        getView()?.checkDatabase()
    }

    private fun setIcon() {
        if (Constants.FIRST_APP == BuildConfig.APPLICATION_ID) {
            getView()?.setIcon(R.drawable.ic_neptun)
        } else {
            getView()?.setIcon(R.drawable.ic_uran)
        }
    }

    fun onDataReceived(p0: DataSnapshot) {
        if (p0.value != null) {
            val stub = p0.child(Constants.STUB).value as Boolean
            val countryDatabaseIso = p0.child(Constants.COUNTRY).value as String?
            stringToList(countryDatabaseIso ?: "")
            inCorrectCountry = isCorrectCountry()
            if (!stub && inCorrectCountry) {
                val webUrl = setUrl(p0)
                getView()?.showMainScreen(webUrl)
            } else {
                getView()?.showResultsScreen()
            }
        }
    }

    private fun stringToList(countryDatabaseIso: String) {
        val countries = countryDatabaseIso
        list = countries.split(",").map { it.trim() }.toMutableList()
    }

    private fun isCorrectCountry(): Boolean {
        var isCorrect = false
        val countryIso = countryISO ?: ""
        for (country in list) {
            if (country.toLowerCase() == countryIso.toLowerCase()) {
                isCorrect = true
                break
            }
        }
        return isCorrect
    }


    private fun setUrl(dataSnapshot: DataSnapshot): String? {
        return when(BuildConfig.APPLICATION_ID) {
            Constants.FIRST_APP -> dataSnapshot.child(Constants.DATABASE_URL).value as String?
            Constants.SECOND_APP -> dataSnapshot.child(Constants.DATABASE_URL_2).value as String?
            else -> null
        }
    }

    fun onDataError() {
        getView()?.showResultsScreen()
    }

}
