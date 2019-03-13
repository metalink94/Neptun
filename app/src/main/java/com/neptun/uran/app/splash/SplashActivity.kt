package com.neptun.uran.app.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.telephony.TelephonyManager
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.neptun.uran.app.BuildConfig
import com.neptun.uran.app.R
import com.neptun.uran.app.results.ResultsActivity
import com.neptun.uran.app.web.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*
import java.util.*

class SplashActivity: AppCompatActivity(), SpashView {

    val presenter = SplashPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        presenter.setView(this)
        presenter.onCreate()
    }

    override fun setIcon(iconRes: Int) {
        icon.setImageResource(iconRes)
    }

    override fun checkDatabase() {
        val database = FirebaseDatabase.getInstance()
        database.reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                presenter.onDataReceived(p0)
                Log.d("DataBase", "get database ${p0.value}")
            }

            override fun onCancelled(p0: DatabaseError) {
                Log.e("URL", p0.message)
                presenter.onDataError()
                showMainScreen(null)
            }
        })
    }

    override fun checkCountry() {
        try {
            val tm = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            val simCountry = tm.simCountryIso
            if (simCountry != null && simCountry.length == 2) { // SIM country code is available
                presenter.countryISO = simCountry.toLowerCase(Locale.US)
            } else if (tm.phoneType != TelephonyManager.PHONE_TYPE_CDMA) { // device is not 3G (would be unreliable)
                val networkCountry = tm.networkCountryIso
                if (networkCountry != null && networkCountry.length == 2) { // network country code is available
                    presenter.countryISO = networkCountry.toLowerCase(Locale.US)
                }
            }
        } catch (e: Exception) {
            Log.e("Error", e.localizedMessage)
            presenter.countryISO = null
        }
    }

    override fun showResultsScreen() {
        startActivity(Intent(this, ResultsActivity::class.java))
        finish()
    }



    private fun finishApp() {
        finish()
    }

    override fun showMainScreen(url: String?) {
        startActivity(MainActivity.getInstance(this, url ?: BuildConfig.URL))
        finish()
    }
}
