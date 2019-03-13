package com.neptun.uran.app.results

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import com.neptun.uran.app.BuildConfig
import com.neptun.uran.app.Constants
import com.neptun.uran.app.R
import com.neptun.uran.app.results.adapter.ResultsAdapterImpl
import com.neptun.uran.app.results.adapter.ResultsBuilder
import com.neptun.uran.app.results.adapter.ResultsBuilderImpl
import kotlinx.android.synthetic.main.activity_result.*

class ResultsActivity: AppCompatActivity(), View.OnClickListener, ResultsView {

    lateinit var adapter: ResultsAdapterImpl
    val builder: ResultsBuilderImpl = ResultsBuilder()

    private val presenter = ResultsPresenter()

    override fun onClick(p0: View?) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        setSupportActionBar(toolbar)
        presenter.setView(this)
        setToolbarIcon()
        initRecycler()
        swipeRefresh.setOnRefreshListener {
            swipeRefresh.isRefreshing = true
            presenter.onRefresh()
        }
        presenter.onCreate()
    }

    private fun setToolbarIcon() {
        if (Constants.FIRST_APP == BuildConfig.APPLICATION_ID) {
            toolbarIcon.setImageResource(R.drawable.ic_neptun)
        } else {
            toolbarIcon.setImageResource(R.drawable.ic_uran)
        }
    }

    private fun initRecycler() {
        recycler.layoutManager = LinearLayoutManager(this)
        builder.setRecycler(recycler)
        builder.setOnClickListener(this)
        adapter = builder.build()
        recycler.adapter = adapter.getRecyclerViewAdapter()
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(menuItem)
    }

    override fun showProgress() {
        progressView.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressView.visibility = View.GONE
    }

    override fun addItems(it: List<Any>) {
        adapter.addItems(it)
    }

    override fun clearAdapter() {
        adapter.clear()
    }

    override fun hideIndicator() {
        swipeRefresh.isRefreshing = false
    }

    override fun showError() {
        AlertDialog.Builder(this)
            .setTitle(R.string.error)
            .setMessage(R.string.error_message)
            .setNegativeButton(R.string.ok) { dialogInterface, i -> }
            .show()
    }
}
