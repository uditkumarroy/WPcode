package com.task.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.task.R
import com.task.models.Row
import com.task.ui.state.BlogStateEvent
import com.task.utils.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class BlogFragment constructor(private val someString: Int) : Fragment(R.layout.fragment_blog) {

    private val TAG = "MainActivity"


    private val viewModel: BlogsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObserver()
        viewModel.stateEvent(BlogStateEvent.GetBlogsEvent())
        Log.e(TAG, "Some string:" + someString)
    }

    fun subscribeObserver() {
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            when (dataState) {
                is DataState.Sucess<List<Row>> -> {
                    Log.e(TAG, "" + dataState.data)
                }
                is DataState.Error -> {
                    displayError(dataState.exception.message.toString())
                }
                is DataState.Loading -> {

                }
            }
        })
    }

    private fun displayError(message: String) {
        if (message != null) {
            Log.e(TAG, "" + message)
        } else {
            Log.e(TAG, "Unknown error")
        }
    }

}