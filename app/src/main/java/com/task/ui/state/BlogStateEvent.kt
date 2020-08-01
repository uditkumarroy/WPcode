package com.task.ui.state

sealed class BlogStateEvent {

    class GetBlogsEvent : BlogStateEvent()
    class None : BlogStateEvent()
}