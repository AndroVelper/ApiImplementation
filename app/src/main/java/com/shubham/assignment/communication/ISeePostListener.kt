package com.shubham.assignment.communication

import com.shubham.assignment.data.Post

interface ISeePostListener {
    fun seeAllPostClicked(position :Int , completePostData : MutableList<Post>)
}