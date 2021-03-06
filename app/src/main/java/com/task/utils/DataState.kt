package com.task.utils

sealed class DataState<out R> {

    data class Sucess<out T>(val data: T) : DataState<T>()
    data class Error(val exception: Exception) : DataState<Nothing>()
    object Loading : DataState<Nothing>()


}