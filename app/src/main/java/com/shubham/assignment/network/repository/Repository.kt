package com.shubham.assignment.network.repository

import com.shubham.assignment.data.OrderModal
import com.shubham.assignment.network.apiManager.IApiDescriptor

class Repository(private val apiService : IApiDescriptor) {

    private val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiNmU2YTJiMWItYTZjOC00ZmI3LWFiYTktYzYxMjA3OTIxOTAxIiwicGhvbmUiOiI5ODk5MjkyODM4IiwiZXhwIjoxNzMyNzk5NDUyfQ.xOa-R35vv-d5z60bFlz6HEDajGZ_fQXSY0c8CHSrjxE"

    suspend fun getOrderList(data : OrderModal, authorization :String = token) = apiService.getOrderList(data,authorization)
    fun getImage(muxPlaybackId :String) = apiService.getImagePreview(muxPlaybackId)
}