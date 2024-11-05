package com.shubham.assignment.utils

import com.shubham.assignment.data.OrderResponse
import com.shubham.assignment.data.Post
import com.shubham.assignment.data.SinglePostData

abstract class Constants {

    companion object {
        const val debugLogMessage: String = "To be implemented later on "
        const val isFromHomeScreen :Int =1
        const val isFromDetailScreen : Int = 2

        var CompleteDataHolder : MutableList<MutableList<Post>>? = null
        var detailScreenCurrentPost : SinglePostData? = null
    }

}