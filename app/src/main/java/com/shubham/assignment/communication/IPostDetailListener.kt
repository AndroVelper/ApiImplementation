package com.shubham.assignment.communication

import com.shubham.assignment.data.Post

interface IPostDetailListener {
    fun postIsClicked(data : Post)
}