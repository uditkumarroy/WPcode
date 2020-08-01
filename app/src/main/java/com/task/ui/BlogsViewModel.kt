package com.task.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.task.models.Row
import com.task.repository.BlogRepository
import com.task.ui.state.BlogStateEvent
import com.task.utils.DataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


class BlogsViewModel
@ViewModelInject
constructor(
    private val blogRepository: BlogRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<Row>>> = MutableLiveData()
    val dataState: LiveData<DataState<List<Row>>>
        get() = _dataState

    fun stateEvent(blogStateEvent: BlogStateEvent) {
        viewModelScope.launch {
            when (blogStateEvent) {
                is BlogStateEvent.GetBlogsEvent -> {
                    blogRepository.getBlog()
                        .onEach { dataState ->
                            _dataState.value = dataState
                        }.launchIn(viewModelScope)
                }

                is BlogStateEvent.None -> {

                }
            }
        }
    }

}





















