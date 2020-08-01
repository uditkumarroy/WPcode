package com.task.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.task.R
import com.task.models.Row
import com.task.ui.state.BlogStateEvent
import com.task.utils.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private val viewModel: BlogsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        subscribeObserver()
        viewModel.stateEvent(BlogStateEvent.GetBlogsEvent())

    }

    fun subscribeObserver() {
        viewModel.dataState.observe(this, Observer { dataState ->
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