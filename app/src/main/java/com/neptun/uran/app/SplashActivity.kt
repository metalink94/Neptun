package com.neptun.uran.app

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.neptun.uran.app.results.ResultsActivity
import com.neptun.uran.app.web.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setIcon()
        checkDatabase()
    }

    private fun setIcon() {
        if (Constants.FIRST_APP == BuildConfig.APPLICATION_ID) {
            icon.setImageResource(R.drawable.ic_neptun)
        } else {
            icon.setImageResource(R.drawable.ic_uran)
        }
    }

    private fun checkDatabase() {
        val database = FirebaseDatabase.getInstance()
        database.reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                if (p0.value != null) {
                    val stub = p0.child(Constants.STUB).value as Boolean
                    if (!stub) {
                        val webUrl = setUrl(p0)
                        showMainScreen(webUrl)
                    } else {
                        showResultsScreen()
                    }
                }
                Log.d("DataBase", "get database ${p0.value}")
            }

            override fun onCancelled(p0: DatabaseError) {
                Log.e("URL", p0.message)
                showMainScreen(null)
            }
        })
    }

    private fun showResultsScreen() {
        startActivity(Intent(this, ResultsActivity::class.java))
        finish()
    }

    private fun setUrl(dataSnapshot: DataSnapshot): String? {
        return when(BuildConfig.APPLICATION_ID) {
            Constants.FIRST_APP -> dataSnapshot.child(Constants.DATABASE_URL).value as String?
            Constants.SECOND_APP -> dataSnapshot.child(Constants.DATABASE_URL_2).value as String?
            else -> null
        }
    }

    private fun finishApp() {
        finish()
    }

    private fun showMainScreen(url: String?) {
        startActivity(MainActivity.getInstance(this, url ?: BuildConfig.URL))
        finish()
    }
}
