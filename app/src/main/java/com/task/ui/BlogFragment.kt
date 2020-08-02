package com.task.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.task.R
import com.task.models.Row
import com.task.ui.state.BlogStateEvent
import com.task.utils.DataState
import com.task.utils.NetworkHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_blog.*
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class BlogFragment constructor(private val networkHelper: NetworkHelper) :
    Fragment(R.layout.fragment_blog), BlogListAdapter.Interaction {

    private val TAG = "MainActivity"
    private val viewModel: BlogsViewModel by viewModels()
    private lateinit var blogListAdapter: BlogListAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        subscribeObserver()
        checkData()
        swipe_refresh.setOnRefreshListener {
            // activity?.getWindow()?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            checkData()

        }
    }

    private fun View.snack(message: String, duration: Int = Snackbar.LENGTH_LONG) {
        Snackbar.make(this, message, duration).show()
    }

    private fun checkData() {
        if (!networkHelper.isNetworkConnected()) {
            view?.snack(getString(R.string.no_internet))
            swipe_refresh.isRefreshing = false
        }
        viewModel.stateEvent(BlogStateEvent.GetBlogsEvent())
    }

    private fun subscribeObserver() {
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            when (dataState) {
                is DataState.Sucess<List<Row>> -> {
                    displayProgressBar(false)
                    Log.e(TAG, "" + dataState.data)
                    swipe_refresh.isRefreshing = false
                    if (dataState.data != null) {
                        blogListAdapter.submitList(
                            blogList = dataState.data,
                            isQueryExhausted = true
                        )
                    }
                }
                is DataState.Error -> {
                    displayError(dataState.exception.message.toString())
                    displayProgressBar(false)
                    swipe_refresh.isRefreshing = false
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                    swipe_refresh.isRefreshing = false
                }
            }
        })
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this.context)
            blogListAdapter = BlogListAdapter(this@BlogFragment)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val lastPosition = layoutManager.findLastVisibleItemPosition()
                    if (lastPosition == blogListAdapter.itemCount.minus(1)) {
                        Log.d(TAG, "Fragment: attempting to load next page...")
                    }
                }
            })
            adapter = blogListAdapter
        }
    }

    private fun displayError(message: String) {
        if (null != message) {
            view?.snack(message)
        } else {
            view?.snack("Unknown Error")
        }
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        progressBar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

    override fun onItemSelected(position: Int, item: Row) {

    }

}